// Generated from /Users/darkojuraga/Documents/FER/ILJ/FER_ILJ/src/main/antlr/hr.tel.fer.ilj.lab.lab1.antlr/Request.g4 by ANTLR 4.8

package hr.tel.fer.ilj.lab.lab1.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RequestLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, KEY=3, OP=4, ESC=5, STRING=6, WS=7;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "KEY", "OP", "ESC", "STRING", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'FILTER'", "'RETURN'", null, null, "'\\\"'", null, "' '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "KEY", "OP", "ESC", "STRING", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public RequestLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Request.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\tS\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\5\4=\n\4\3\5\3\5\3\5\3\5\5\5C\n\5\3\6\3\6\3\6\3\7\3\7\3\7\7\7"+
		"K\n\7\f\7\16\7N\13\7\3\7\3\7\3\b\3\b\3L\2\t\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\3\2\2\2Y\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\3\21\3\2\2\2\5\30\3\2\2\2\7<\3\2\2\2\tB\3"+
		"\2\2\2\13D\3\2\2\2\rG\3\2\2\2\17Q\3\2\2\2\21\22\7H\2\2\22\23\7K\2\2\23"+
		"\24\7N\2\2\24\25\7V\2\2\25\26\7G\2\2\26\27\7T\2\2\27\4\3\2\2\2\30\31\7"+
		"T\2\2\31\32\7G\2\2\32\33\7V\2\2\33\34\7W\2\2\34\35\7T\2\2\35\36\7P\2\2"+
		"\36\6\3\2\2\2\37 \7K\2\2 =\7R\2\2!\"\7F\2\2\"#\7C\2\2#$\7V\2\2$%\7G\2"+
		"\2%&\7V\2\2&\'\7K\2\2\'(\7O\2\2(=\7G\2\2)*\7O\2\2*+\7G\2\2+,\7V\2\2,-"+
		"\7J\2\2-.\7Q\2\2.=\7F\2\2/\60\7X\2\2\60\61\7G\2\2\61\62\7T\2\2\62\63\7"+
		"U\2\2\63\64\7K\2\2\64\65\7Q\2\2\65=\7P\2\2\66\67\7U\2\2\678\7V\2\289\7"+
		"C\2\29:\7V\2\2:;\7W\2\2;=\7U\2\2<\37\3\2\2\2<!\3\2\2\2<)\3\2\2\2</\3\2"+
		"\2\2<\66\3\2\2\2=\b\3\2\2\2>?\7#\2\2?C\7?\2\2@A\7?\2\2AC\7?\2\2B>\3\2"+
		"\2\2B@\3\2\2\2C\n\3\2\2\2DE\7^\2\2EF\7$\2\2F\f\3\2\2\2GL\7$\2\2HK\5\13"+
		"\6\2IK\13\2\2\2JH\3\2\2\2JI\3\2\2\2KN\3\2\2\2LM\3\2\2\2LJ\3\2\2\2MO\3"+
		"\2\2\2NL\3\2\2\2OP\7$\2\2P\16\3\2\2\2QR\7\"\2\2R\20\3\2\2\2\7\2<BJL\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}