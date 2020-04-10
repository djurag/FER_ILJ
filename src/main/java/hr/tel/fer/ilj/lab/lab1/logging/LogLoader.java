package hr.tel.fer.ilj.lab.lab1.logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Read all lines in one file and returns list of all logs.
 *
 * @author Darko Juraga as part of <a href="https://www.fer.unizg.hr/predmet/ilj_a">Information, Logic and Languages</a> course.
 */
public class LogLoader {

    /**
     * Load all lines from file to list of LogEntries.
     *
     * @param reader {@link java.io.FileReader} for current file.
     * @return Logs from file.
     */
    public static List<LogEntry> load(Reader reader) {
        int row = 0;
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<LogEntry> logs = new LinkedList<>();
        try {
            String line = bufferedReader.readLine();
            row++;
            LogParser parser = new LogParser();
            LogEntry entry;
            while (line != null) {
                Optional<LogEntry> optional = parser.parse(line);
                if (optional.isPresent()) {
                    entry = optional.get();
                    logs.add(entry);
                }
                line = bufferedReader.readLine();
                row++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read log file in row " + row + ".", e);
        }
        return logs;
    }
}
