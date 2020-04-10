package hr.tel.fer.ilj.lab.lab1.query;

import hr.tel.fer.ilj.lab.lab1.logging.LogEntry;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Filter all LogEntries.
 *
 * @author Darko Juraga as part of <a href="https://www.fer.unizg.hr/predmet/ilj_a">Information, Logic and Languages</a> course.
 */
public class LogEntryFilter {
    private List<Expression> expressions;

    /**
     * Constructor.
     */
    public LogEntryFilter() {
        expressions = new LinkedList<>();
    }

    /**
     * Add expression to list of expressions.
     *
     * @param e Expression.
     */
    public void add(Expression e) {
        expressions.add(e);
    }

    /**
     * Filter all LogEntries in dependency of list of expressions.
     *
     * @param allLogs List of allLogs.
     * @return List of logs that match expressions.
     */
    public List<LogEntry> filter(List<LogEntry> allLogs) {
        return allLogs.stream().filter(log -> expressions.stream().mapToInt(expr -> expr.checkCondition(log) ? 1 : 0).sum() == expressions.size()).collect(Collectors.toList());
    }
}
