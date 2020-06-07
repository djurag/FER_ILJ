package hr.tel.fer.ilj.lab.lab2.xmlFilter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Class implements XML filter based on user input.
 *
 * @author Darko Juraga
 */
public class XMLFilter {

    private static String path;

    /**
     * Main method.
     *
     * @param args Commandline arguments.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Input path to XML file:");
//        path=sc.nextLine();
        path = "documents/lab2/file.xml";
        File file = new File(path);
        if (checkFile(file)) {
            System.out.println("File on " + file.getAbsolutePath() + " is selected.");
            while (true) {
                String inputFilter = getFilter(sc);
                String[] split = getSplits(inputFilter);
                if (split == null)
                    continue;
                parse(split);
            }
        }

    }

    /**
     * Method used for parsing XML file.
     *
     * @param input filter parameters
     */
    private static void parse(String[] input) {
        String command = input[0];
        String tag;
        int num = 1;
        boolean findAll = false;
        boolean haveOne = false;
        if (input[2].equals("*"))
            findAll = true;
        else if (Integer.parseInt(input[2]) > 0)
            num = Integer.parseInt(input[2]);
        else {
            System.out.println("Number is invalid! Must be grater than 0!");
            return;
        }
        if (command.equals("ATTRIBUTE"))
            tag = input[1];
        else
            tag = input[1].substring(1, input[1].length() - 1);

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(path));
            if (command.equals("ATTRIBUTE")) {
                while (eventReader.hasNext()) {
                    XMLEvent event = eventReader.nextEvent();
                    if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                        StartElement startElement = event.asStartElement();
                        Iterator<Attribute> attributeIterator = startElement.getAttributes();
                        while (attributeIterator.hasNext() && num > 0) {
                            String attribute = attributeIterator.next().toString();
                            String firstAttribute = attribute.substring(0, attribute.indexOf("="));
                            if (firstAttribute.equals(tag)) {
                                haveOne = true;
                                System.out.println(attribute);
                                if (!findAll)
                                    num--;
                            }

                        }
                    }
                }
                if (!haveOne)
                    System.out.println("Didn't found attribute " + tag);
            } else if (command.equals("TEXT") || command.equals("ELEMENT")) {
                while (eventReader.hasNext() && num > 0) {
                    XMLEvent event = eventReader.nextEvent();
                    if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                        StartElement startElement = event.asStartElement();
                        String name = startElement.getName().getLocalPart();
                        if (name.equals(tag)) {
                            while (true) {
                                if (command.equals("TEXT"))
                                    System.out.println(eventReader.nextEvent());
                                else if (command.equals("ELEMENT"))
                                    System.out.println(event);
                                if (event.getEventType() == XMLStreamConstants.END_ELEMENT) {
                                    EndElement element = event.asEndElement();
                                    if (element.getName().getLocalPart().equalsIgnoreCase(tag)) {
                                        haveOne = true;
                                        if (!findAll)
                                            num--;
                                        break;
                                    }
                                }
                                event = eventReader.nextEvent();
                            }
                        }
                    }
                }
                if (!haveOne)
                    System.out.println("Didn't found element with " + tag);
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if selected file is suitable for parsing.
     *
     * @param file File to be checked.
     * @return True if file is ok, false if not.
     */
    private static boolean checkFile(File file) {
        if (!file.isFile()) {
            System.out.println("File doesn't exist.");
            return false;
        } else if (!file.getName().toLowerCase().endsWith(".xml")) {
            System.out.println("Selected type of file is not XML");
            return false;
        }
        return true;
    }

    /**
     * Check if filter is entered in appropriate way.
     * Only three parameters are allowed in filter.
     *
     * @param inputFilter Entered filter.
     * @return Filter splitted into parameters. If something is wrong than null is returned.
     */
    private static String[] getSplits(String inputFilter) {
        String[] strings;
        try {
            strings = inputFilter.split(" ");
            if (strings.length == 3)
                return strings;
            else {
                System.out.println("Invalid input!");
                return null;
            }
        } catch (NullPointerException ignore) {
            return null;
        }
    }

    /**
     * Listens to user input for filter and checks if filter
     * is acceptable. Filter must start with ELEMENT, TEXT or ATTRIBUTE.
     * EXIT is command used to terminate application.
     *
     * @param sc Scanner
     * @return Filter if it is valid, null if input is not valid
     */
    private static String getFilter(Scanner sc) {
        System.out.println("Input filter");
        String[] keys = new String[]{"ELEMENT", "TEXT", "ATTRIBUTE"};
        List<String> valid = Arrays.asList(keys);
        String filter = sc.nextLine();
        if (filter.equals("EXIT")) {
            System.out.println("Exiting application...");
            sc.close();
            System.exit(0);
        } else {
            if (valid.contains(filter.split(" ")[0])) {
                return filter;
            } else {
                System.out.println("Invalid input!");
                return null;
            }

        }
        return null;

    }

}
