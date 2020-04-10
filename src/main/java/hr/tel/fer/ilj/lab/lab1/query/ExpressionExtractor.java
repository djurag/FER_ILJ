package hr.tel.fer.ilj.lab.lab1.query;

import hr.tel.fer.ilj.lab.lab1.antlr.RequestBaseListener;
import hr.tel.fer.ilj.lab.lab1.antlr.RequestParser;
import org.antlr.v4.runtime.tree.ErrorNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class extracts all expressions from query.
 *
 * @author Darko Juraga as part of <a href="https://www.fer.unizg.hr/predmet/ilj_a">Information, Logic and Languages</a> course.
 */
public class ExpressionExtractor extends RequestBaseListener {
    private List<Expression> expressions = new LinkedList<>();
    private String error;
    private final List<String> months = new ArrayList<String>() {
        {
            add("Jan");
            add("Feb");
            add("Mar");
            add("Apr");
            add("May");
            add("Jun");
            add("Jul");
            add("Aug");
            add("Sep");
            add("Oct");
            add("Nov");
            add("Dec");
        }
    };


    @Override
    public void exitExpr(RequestParser.ExprContext ctx) {
        Expression expr = new Expression();
        if (!ctx.WS().isEmpty())
            visitErrorNode(null);
        else if (!ctx.value().getText().contains("\""))
            visitErrorNode(null);
        else {
            String value = ctx.value().getText().replace("\"", "");
            if (ctx.KEY().getText().equals("STATUS")) {
                if (value.length() != 3)
                    visitErrorNode(null);
                else if (!value.chars().allMatch(Character::isDigit))
                    visitErrorNode(null);
            } else if (ctx.KEY().getText().equals("DATETIME")) {
                if (!value.matches("^(\\d){2}\\/(\\w){3}\\/\\d{4}(:\\d{2}){3}"))
                    visitErrorNode(null);
                else if (months.stream().noneMatch(value::contains))
                    visitErrorNode(null);
            } else if (ctx.KEY().getText().equals("IP")) {
                if(value.contains("X") && !value.contains("XXX")){
                    visitErrorNode(null);
                }
            }
            expr.setKey(ctx.KEY().getText());
            expr.setValue(value);
            expr.setOperator(Operator.parse(ctx.OP().getText()));
            expressions.add(expr);
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
     * All expressions getter.
     *
     * @return Expressions.
     */
    public List<Expression> getExpressions() {
        return expressions;
    }
}
