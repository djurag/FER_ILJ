package hr.tel.fer.ilj.lab.lab1.logging;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create LogEntry from parsed line depending on regex.
 *
 * @author Darko Juraga as part of <a href="https://www.fer.unizg.hr/predmet/ilj_a">Information, Logic and Languages</a> course.
 */
public class LogParser {

    private static final String lineRegex = "^([\\d.]+) \\[([\\w:/]+)\\] (.*?) (.*?) (.*?) (\\d{3}) \\\"(.+?)\\\"";
    private static final Pattern pattern = Pattern.compile(lineRegex);

    /**
     * Create {@link Matcher} and fill LogEntry with all data from line.
     *
     * @param line Line to be logged.
     * @return LogEntry
     */
    public Optional<LogEntry> parse(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            if (matcher.groupCount() == 7) {
                LogEntry logEntry = new LogEntry();
                logEntry.setIp(matcher.group(1));
                logEntry.setDateTime(matcher.group(2));
                logEntry.setHttpMethod(matcher.group(3));
                logEntry.setPath(matcher.group(4));
                logEntry.setHttpVersion(matcher.group(5));
                logEntry.setStatus(Integer.parseInt(matcher.group(6)));
                logEntry.setClientID(matcher.group(7));
                return Optional.of(logEntry);
            }
        }
        return Optional.empty();
    }

}
