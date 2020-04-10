package hr.tel.fer.ilj.lab.lab1.query;

import hr.tel.fer.ilj.lab.lab1.antlr.RequestBaseListener;
import hr.tel.fer.ilj.lab.lab1.antlr.RequestParser;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ErrorNode;

/**
 * This class extracts number of filtered logEntries that need to be returned to client.
 *
 * @author Darko Juraga as part of <a href="https://www.fer.unizg.hr/predmet/ilj_a">Information, Logic and Languages</a> course.
 */
public class NumExtractor extends RequestBaseListener {
    String num;
    String error;

    @Override
    public void exitNum(RequestParser.NumContext ctx) {
        if (!ctx.WS().isEmpty())
            visitErrorNode(null);
        else if (!ctx.getText().contains("\""))
            visitErrorNode(null);
        else {
            int a = ctx.start.getStartIndex() + 1;
            int b = ctx.stop.getStopIndex() - 1;
            if (b < a)
                visitErrorNode(null);

            else {
                Interval interval = new Interval(a, b);
                num = ctx.start.getInputStream().getText(interval);
                if (!num.chars().allMatch(Character::isDigit) && !num.equals("*"))
                    visitErrorNode(null);
            }
        }
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        error = "Query not in format!";
    }

    /**
     * Error getter.
     *
     * @return Error.
     */
    public String getError() {
        return error;
    }

    /**
     * Number of logs getter.
     *
     * @return Number of logs.
     */
    public String getNum() {
        return num;
    }

}
