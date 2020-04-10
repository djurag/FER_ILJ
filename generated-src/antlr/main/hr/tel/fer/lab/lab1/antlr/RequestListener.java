// Generated from /Users/darkojuraga/Documents/FER/ILJ/FER_ILJ/src/main/antlr/hr.tel.fer.ilj.lab.lab1.antlr/Request.g4 by ANTLR 4.8

package hr.tel.fer.ilj.lab.lab1.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RequestParser}.
 */
public interface RequestListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RequestParser#request}.
	 * @param ctx the parse tree
	 */
	void enterRequest(RequestParser.RequestContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequestParser#request}.
	 * @param ctx the parse tree
	 */
	void exitRequest(RequestParser.RequestContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequestParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(RequestParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequestParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(RequestParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequestParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(RequestParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequestParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(RequestParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequestParser#num}.
	 * @param ctx the parse tree
	 */
	void enterNum(RequestParser.NumContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequestParser#num}.
	 * @param ctx the parse tree
	 */
	void exitNum(RequestParser.NumContext ctx);
}