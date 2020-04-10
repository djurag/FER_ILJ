package hr.tel.fer.ilj.lab.lab1.query;

/**
 * Operators that are used in expressions.
 *
 * @author Darko Juraga as part of <a href="https://www.fer.unizg.hr/predmet/ilj_a">Information, Logic and Languages</a> course.
 */
public enum Operator {
    /**
     * Different from.
     */
    DIFF,
    /**
     * Equal to.
     */
    EQ;

    /**
     * Return operator value.
     *
     * @param op Operator from expression.
     * @return Operator
     */
    public static Operator parse(String op) {
        if (op.equals("!=")) return DIFF;
        else return EQ;
    }
}
