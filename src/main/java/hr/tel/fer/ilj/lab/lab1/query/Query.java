package hr.tel.fer.ilj.lab.lab1.query;

import hr.tel.fer.ilj.lab.lab1.antlr.RequestLexer;
import hr.tel.fer.ilj.lab.lab1.antlr.RequestParser;
import hr.tel.fer.ilj.lab.lab1.logging.LogEntry;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * This class is main class for execution of log filtering.
 * After start the client is asked to enter query. Class execute everything connected with
 * log filtering and return as many filtered logs as client requested.
 *
 * @author Darko Juraga as part of <a href="https://www.fer.unizg.hr/predmet/ilj_a">Information, Logic and Languages</a> course.
 */
public class Query implements Runnable {

    Socket socket;
    List<LogEntry> allLogs;

    /**
     * Constructor.
     *
     * @param socket  Client socket.
     * @param allLogs List of all logs from files.
     */
    public Query(Socket socket, List<LogEntry> allLogs) {
        this.socket = socket;
        this.allLogs = allLogs;
    }

    @Override
    public void run() {
        try {
            PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream.println(greetClient());
            String query = inputStream.readLine();
            List<LogEntry> logs = processInput(query);
            String num = parseNum(query);
            if (num.equals("*")) {
                processOutput(logs, outputStream, -1);
            } else if (num.startsWith("0")) {
                processOutput(logs, outputStream, 0);
            } else {
                processOutput(logs, outputStream, Integer.parseInt(num));
            }


        } catch (IOException ignored) {
        } finally {
            try {
                closeConnection();
            } catch (IOException ignore) {
            }
        }
    }

    /**
     * Close socket with client and disconnect him from server.
     *
     * @throws IOException If connection is closed before printing.
     */
    private void closeConnection() throws IOException {
        PrintWriter outStream = new PrintWriter(socket.getOutputStream(), true);
        outStream.println("Goodbye client!");
        socket.close();

    }

    /**
     * Create parsing tree from grammar.
     *
     * @param query Query to be parsed.
     * @return Parser tree for query.
     */
    private ParseTree createTree(String query) {
        CharStream input = CharStreams.fromString(query);
        RequestLexer lexer = new RequestLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RequestParser parser = new RequestParser(tokens);
        return parser.request();
    }

    /**
     * Parse number of logs that client wants.
     *
     * @param query Query.
     * @return Number of logs.
     * @throws IOException Exception
     */
    private String parseNum(String query) throws IOException {
        ParseTree tree = createTree(query);
        ParseTreeWalker walker = new ParseTreeWalker();
        NumExtractor listener = new NumExtractor();
        walker.walk(listener, tree);
        if (listener.getError() != null) {
            new RequestException(listener.getError(), socket);
            closeConnection();
        }
        return listener.getNum();
    }

    /**
     * Response to client.
     *
     * @param logs         Filtered logs.
     * @param outputStream Stream where logs are sent.
     * @param num          -1 if all logs should be printed,
     */
    private void processOutput(List<LogEntry> logs, PrintWriter outputStream, int num) {
        if (logs.isEmpty()) {
            outputStream.println("No logs for entered filter!");
            return;
        }
        if (num == -1) {
            logs.forEach(log -> outputStream.println(log.toString()));
        } else if (num == 0) {
            outputStream.println("You have entered 0. Filtered logs are not being displayed.");
        } else {
            if (num > logs.size())
                num = logs.size();
            for (int i = 0; i < num; i++) {
                outputStream.println(logs.get(i).toString());
            }
        }
    }

    /**
     * Filter logs.
     *
     * @param query Query from client.
     * @return Filtered logs.
     * @throws IOException Exception
     */
    private List<LogEntry> processInput(String query) throws IOException {
        List<Expression> expressions = parseExpressions(query);
        return filterLogs(expressions);
    }

    /**
     * Calls log filter.
     *
     * @param expressions Expressions used for filter.
     * @return Filtered logs.
     */
    private List<LogEntry> filterLogs(List<Expression> expressions) {
        LogEntryFilter filter = new LogEntryFilter();
        expressions.forEach(filter::add);
        return filter.filter(allLogs);
    }

    /**
     * Parse expressions from query.
     *
     * @param query Query from client.
     * @return Expressions.
     * @throws IOException Exception
     */
    private List<Expression> parseExpressions(String query) throws IOException {
        ParseTree tree = createTree(query);
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionExtractor listener = new ExpressionExtractor();
        walker.walk(listener, tree);

        if (listener.getError() != null) {
            new RequestException(listener.getError(), socket);
            closeConnection();
        }
        return listener.getExpressions();
    }

    /**
     * Say hallo to connected client.
     *
     * @return Hello to client.
     */
    private String greetClient() {
        return "Connection established!\nEnter your query: ";
    }

}
