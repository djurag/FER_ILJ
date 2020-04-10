package hr.tel.fer.ilj.lab.lab1.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class implements TCP client. Client requests query from user and sends it to the server to complete the query.
 * Arguments important for appropriate client execution are entered as
 * command line arguments in order like this: IP port. For IP try localhost.
 *
 * @author Darko Juraga as part of <a href="https://www.fer.unizg.hr/predmet/ilj_a">Information, Logic and Languages</a> course.
 */
public class Client {
    /**
     * Main method for starting client.
     *
     * @param args IP, port
     * @throws Exception {@link IllegalArgumentException} if wrong number of arguments is entered. Multiple other {@link Exception} depending on execution of other methods.
     */
    public static void main(String[] args) throws Exception {

        String ip;
        int port;
        if (args.length == 2) {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        } else {
            throw new IllegalArgumentException("Input command line arguments in order like this: IP port (try with localhost for IP)");
        }

        Socket socket = new Socket(ip, port);
        PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(inputStream.readLine());
        System.out.println(inputStream.readLine());
        String userInput = userReader.readLine();
        outputStream.println(userInput);
        String inFromServer;
        while ((inFromServer = inputStream.readLine()) != null) {
            System.out.println(inFromServer);
        }

        userReader.close();
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
