package hr.tel.fer.ilj.dz.dz1.htmlregex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Filter HTML tags and content depending on user entry.
 *
 * @author Darko Juraga as part of <a href="https://www.fer.unizg.hr/predmet/ilj_a">Information, Logic and Languages</a> course.
 */
public class htmlRegex {

    /**
     * Main method
     *
     * @param args path to file
     * @throws FileNotFoundException if file is not found
     */
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Path path = getPath(args);
        System.out.println("Putanja: " + path);
        List<Operation> operations = new ArrayList<>();
        addOperations(operations);
        String html = getHtml(path);
        html = html.replaceAll("\t", "").replaceAll("\n", "");
        listeningOperations(scanner, html, operations);
        scanner.close();
    }

    /**
     * Path input and check if file exists for entered destination
     *
     * @param args contains path
     * @return Path
     * @throws FileNotFoundException if file is not found
     */
    private static Path getPath(String[] args) throws FileNotFoundException {
        Path path;
        if (args.length != 0) {
            path = Paths.get(args[0]);
        } else throw new IllegalArgumentException("Nije unesena putanja");
        if (Files.exists(path)) return path;
        else
            throw new FileNotFoundException("File not found on " + path);

    }


    /**
     * Listening user input for commands
     *
     * @param scanner    Scanner
     * @param html       Read html file
     * @param operations List of operations
     */
    private static void listeningOperations(Scanner scanner, String html, List<Operation> operations) {
        while (true) {
            System.out.println("Unesi naredbu: ");
            String op = scanner.nextLine();
            if (op.equals("EXIT")) break; //exit
            else if (op.equals("ALL")) findMatch(html, Pattern.compile(html), 0); //all
            else if (op.equals("HELP")) printHelp(operations); //help
            else if (op.startsWith("REGEX ID")) { //regex id
                op = op.replaceAll("[^\\d+]", "");
                System.out.println(operations.get(Integer.parseInt(op) - 1));
            } else if (op.contains("<") && op.contains(">")) { //all <tag>
                String tag = op.substring(op.indexOf("<") + 1, op.indexOf(">"));
                Pattern regex = Pattern.compile(operations.get(1).getRegex().toString().replaceAll("tag", tag));
                if (op.startsWith("ALL")) {
                    findMatch(html, regex, 0);
                } else if (op.startsWith("ONLY")) {
                    op = op.replaceAll("[^\\d+]", "");
                    findMatch(html, regex, Integer.parseInt(op));
                }
            } else if (op.contains("email")) { //mail
                if (op.startsWith("ALL"))
                    findMatch(html, operations.get(2).getRegex(), 0);
                else if (op.startsWith("ONLY")) {
                    op = op.replaceAll("[^\\d+]", "");
                    findMatch(html, operations.get(7).getRegex(), Integer.parseInt(op));
                }
            } else if (op.contains("IP")) { //ip
                if (op.startsWith("ALL"))
                    findMatch(html, operations.get(3).getRegex(), 0);
                else if (op.startsWith("ONLY")) {
                    op = op.replaceAll("[^\\d+]", "");
                    findMatch(html, operations.get(8).getRegex(), Integer.parseInt(op));
                }
            } else if (op.contains("date")) { //date
                if (op.startsWith("ALL"))
                    findMatch(html, operations.get(4).getRegex(), 0);
                else if (op.startsWith("ONLY")) {
                    op = op.replaceAll("[^\\d+]", "");
                    findMatch(html, operations.get(9).getRegex(), Integer.parseInt(op));
                }
            } else if (op.contains("tel")) {
                if (op.startsWith("ALL"))
                    findMatch(html, operations.get(5).getRegex(), 0);
                else if (op.startsWith("ONLY")) {
                    op = op.replaceAll("[^\\d+]", "");
                    findMatch(html, operations.get(10).getRegex(), Integer.parseInt(op));
                }
            }

        }

    }

    /**
     * Search file to find given pattern
     *
     * @param html    File
     * @param count   Number of values to be printed. If operation is ALL than number is 0
     * @param pattern Pattern to be found
     */

    private static void findMatch(String html, Pattern pattern, int count) {
        Matcher matcher;
        matcher = pattern.matcher(html);
        if (count == 0) {
            while (matcher.find())
                System.out.println(matcher.group(0));
        } else {
            int counter = 0;
            while (matcher.find() && counter < count) {
                System.out.println(matcher.group(0));
                counter++;
                if (counter == count) break;
            }
        }
    }

    /**
     * Printing if help is called
     *
     * @param operations list of operations
     */
    private static void printHelp(List<Operation> operations) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID - Naredba \t\t - Opis\n\n");
        for (Operation op : operations) {
            if (op.id < 10) {
                sb.append(op.id).append("  - ");
            } else sb.append(op.id).append(" - ");
            if (op.name.equals("ALL") || op.name.equals("HELP") || op.name.equals("EXIT")) {
                sb.append(op.name).append(" \t\t\t - ").append(op.description).append("\n");
            } else if (op.name.startsWith("ONLY")) {
                sb.append(op.name).append(" - ").append(op.description).append("\n");
            } else sb.append(op.name).append(" \t\t - ").append(op.description).append("\n");
        }
        System.out.println(sb.toString());
    }

    /**
     * Inserts operations in list of operations with their ID, name, description and regex
     *
     * @param operations list of operations
     */
    private static void addOperations(List<Operation> operations) {
        operations.add(new Operation(1, "ALL", "Vraca cijelu HTML datoteku", null));
        operations.add(new Operation(2, "ALL <tag>", "Vraca sadrzaj svih zadanih tagova. " +
                "Npr. ALL <b> vratit ce sadrzaj svih <b> tagova. Korisnik sam upisuje tag koji je potrebno vratiti.",
                Pattern.compile("((<tag>)|(<tag.+?>)).*?<\\/tag>")));
        operations.add(new Operation(3, "ALL email", "Vraca popis svih email adresa u formatu: username@host.domain ",
                Pattern.compile("\\b[0-9a-z.\\_%+-]+@[0-9a-z.-]+\\.[a-z]{2,4}\\b", Pattern.CASE_INSENSITIVE)));
        operations.add(new Operation(4, "ALL IP", "Vraca popis svih IPv4 adresa u formatu: x.x.x.x ",
                Pattern.compile("(\\b(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){3}(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\b)")));
        operations.add(new Operation(5, "ALL date", "Vraca popis svih datuma u formatu: dd/mm/yyyy ",
                Pattern.compile("(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02]))|((0[1-9]|1\\d|2[0-8])\\/02)|((0[1-9]|[12]\\d|30)\\/(0[469]|11)))\\/[12](\\d{3})")));
        operations.add(new Operation(6, "ALL tel", "Vraca popis svih telefonskih brojeva u formatu: 385 34 123 4567 ",
                Pattern.compile("((\\d){3}[\\-\\s])?(\\d){2}[\\-\\s](\\d){3}[\\-\\s](\\d){4}")));
        operations.add(new Operation(7, "ONLY <tag> broj", "Vraca prvih broj sadrzaja odredenog taga <tag>. Npr. " +
                "ONLY <b> 5 ce vratiti sadrzaj prvih 5 (ili manje ukoliko ih nema 5) <b> tagova.",
                Pattern.compile("((<tag>)|(<tag.+?>)).*?<\\/tag>")));
        operations.add(new Operation(8, "ONLY email broj", "Vraca prvih broj email adresa. Npr. " +
                "ONLY email 5 ce vratiti prvih 5 (ili manje ukoliko ih nema 5) email adresa koje se pojavljuju.",
                Pattern.compile("\\b[0-9a-z.\\_%+-]+@[0-9a-z.-]+\\.[a-z]{2,4}\\b", Pattern.CASE_INSENSITIVE)));
        operations.add(new Operation(9, "ONLY IP broj", "Vraca prvih broj IPv4 adresa. Npr. " +
                "ONLY IP 5 ce vratiti prvih 5 (ili manje ukoliko ih nema 5) IPv4 adresa koje se pojavljuju.",
                Pattern.compile("(\\b(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){3}(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\b)")));
        operations.add(new Operation(10, "ONLY date broj", "Vraca prvih broj datuma. Npr. ONLY " +
                "date 5 ce vratiti prvih 5 (ili manje ukoliko ih nema 5) datuma koji se pojavljuju.",
                Pattern.compile("(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02]))|((0[1-9]|1\\d|2[0-8])\\/02)|((0[1-9]|[12]\\d|30)\\/(0[469]|11)))\\/[12](\\d{3})")));
        operations.add(new Operation(11, "ONLY tel broj", "Vraca prvih broj telefonskih brojeva. " +
                "Npr. ONLY tel 5 ce vratiti prvih 5 (ili manje ukoliko ih nema 5) telefonskih brojeva koji se pojavljuju.",
                Pattern.compile("((\\d){3}[\\-\\s])?(\\d){2}[\\-\\s](\\d){3}[\\-\\s](\\d){4}")));
        operations.add(new Operation(12, "HELP", "Vraca popis opcija koje su implementirane, " +
                "u formatu slicnom ovoj tablici. Svaka opcija treba imati svoj ID opcije.", null));
        operations.add(new Operation(13, "REGEX ID broj", "Vraca izgled regularnog izraza " +
                "za opciju ciji je ID jednak zadanom argumentu broj. Npr. Ako opcija ALL IP ima ID = 5, REGEX ID 5 ce vratiti izgled regularnog izraza za opciju ALL IP.", null));
        operations.add(new Operation(14, "EXIT", "Prekida rad programa", null));

    }

    /**
     * Method opens file and reads all lines to string
     *
     * @param path file location
     * @return html file
     */
    private static String getHtml(Path path) {
        List<String> file = null;
        StringBuilder sb = new StringBuilder();
        try {
            file = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert file != null;
        for (String str : file) {
            sb.append(str);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Class defines operation with all its parameters (ID, name, description and regex)
     */
    private static class Operation {
        final int id;
        final String name;
        final String description;
        final Pattern regex;

        /**
         * Public constructor
         *
         * @param id          ID
         * @param name        Name
         * @param description Description
         * @param regex       Regex
         */
        public Operation(int id, String name, String description, Pattern regex) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.regex = regex;
        }

        /**
         * Getter
         *
         * @return operation regex
         */
        public Pattern getRegex() {
            return regex;
        }

        @Override
        public String toString() {
            if (regex == null)
                return name + " - Ne sadrzi regex";
            else
                return name + " - regex: " + regex;
        }
    }

}

