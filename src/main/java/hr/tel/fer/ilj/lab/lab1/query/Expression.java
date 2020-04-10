package hr.tel.fer.ilj.lab.lab1.query;

import hr.tel.fer.ilj.lab.lab1.logging.LogEntry;

/**
 * This class implements expression that is used to filter logs.
 *
 * @author Darko Juraga as part of <a href="https://www.fer.unizg.hr/predmet/ilj_a">Information, Logic and Languages</a> course.
 */
public class Expression {
    private String key;
    private Operator operator;
    private String value;

    /**
     * Constructor.
     */
    public Expression() {
    }

    /**
     * Key setter.
     *
     * @param key Key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Operator setter.
     *
     * @param operator Operator.
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * Value setter.
     *
     * @param value Value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Check if entry matches expression depending on operator.
     *
     * @param entry Entry to be checked.
     * @return True if entry matches expression, false if entry don't match expression.
     */
    public boolean checkCondition(LogEntry entry) {
        boolean comparison = compare(entry);
        switch (operator) {
            case EQ:
                return comparison;
            case DIFF:
                return !comparison;
        }
        return false;
    }

    /**
     * Compare log entry value with expression value.
     *
     * @param entry Entry to be checked.
     * @return True if log entry value equals expression value, false otherwise.
     */
    private boolean compare(LogEntry entry) {
        if (key.equals("STATUS")) {
            int intValue = Integer.parseInt(value);
            return (entry.getStatus() == intValue);
        } else if (key.equals("IP")) {
            if (value.contains("XXX")) {
                int noOfGroups = countIPGroups();
                String entryString = getStringFromEntry(entry);
                return checkIfEqual(entryString, noOfGroups);
            } else {
                String entryString = getStringFromEntry(entry);
                assert entryString != null;
                return entryString.equals(value);
            }
        } else {
            String entryString = getStringFromEntry(entry);
            assert entryString != null;
            return entryString.equals(value);
        }
    }

    /**
     * Check if log IP address is in same subnet as IP in expression.
     *
     * @param entryString IP address in log.
     * @param noOfGroups  Number of groups before masked part of IP address.
     * @return True if log IP address is in same subnet as IP in expression, false otherwise.
     */
    private boolean checkIfEqual(String entryString, int noOfGroups) {
        String localValue = value.substring(0, value.indexOf("XXX"));
        int index = 0;
        while (noOfGroups > 0) {
            if (entryString.charAt(index) == '.')
                noOfGroups--;
            index++;
        }
        String localEntryString = entryString.substring(0, index);
        return localEntryString.equals(localValue);
    }

    /**
     * Count group of IP addresses before masked part of IP address.
     *
     * @return Group count.
     */
    private int countIPGroups() {
        int count = 0;
        int index = value.indexOf("XXX");
        for (int i = 0; i < index; i++) {
            if (value.charAt(i) == '.')
                count++;
        }
        return count;
    }

    /**
     * Extract different part of entry depending on expression key.
     *
     * @param entry Log entry
     * @return IP, DateTime, HttpMethod, HttpVersion or Status depending on key
     */
    private String getStringFromEntry(LogEntry entry) {
        switch (key) {
            case "IP":
                return entry.getIp();
            case "DATETIME":
                return entry.getDateTime();
            case "METHOD":
                return entry.getHttpMethod();
            case "VERSION":
                return entry.getHttpVersion();
            case "STATUS":
                return Integer.toString(entry.getStatus());
        }
        return null;
    }
}
