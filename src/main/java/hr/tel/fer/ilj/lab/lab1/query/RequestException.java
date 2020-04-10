package hr.tel.fer.ilj.lab.lab1.query;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Exception.
 *
 * @author Darko Juraga as part of <a href="https://www.fer.unizg.hr/predmet/ilj_a">Information, Logic and Languages</a> course.
 */
public class RequestException {

    /**
     * Constructor.
     *
     * @param e      Error.
     * @param socket Client socket.
     */
    public RequestException(String e, Socket socket) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(e);
        } catch (IOException ignore) {
        }
    }
}
