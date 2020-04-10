// Generated from /Users/darkojuraga/Documents/FER/ILJ/FER_ILJ/src/main/antlr/hr.tel.fer.ilj.lab.lab1.antlr/Request.g4 by ANTLR 4.8

package hr.tel.fer.ilj.lab.lab1.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RequestParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RequestVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RequestParser#request}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequest(RequestParser.RequestContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequestParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(RequestParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequestParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(RequestParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequestParser#num}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNum(RequestParser.NumContext ctx);
}