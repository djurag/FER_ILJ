package hr.tel.fer.ilj.lab.lab1.server;

import hr.tel.fer.ilj.lab.lab1.logging.FileVisitor;
import hr.tel.fer.ilj.lab.lab1.logging.LogEntry;
import hr.tel.fer.ilj.lab.lab1.query.Query;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class implements server which is responsible for handling clients.
 * Arguments important for appropriate server execution are entered as
 * command line arguments in order like this: maxNoOfClients port path.
 * At the same time only maxNoOfClients {@link hr.tel.fer.ilj.lab.lab1.client.Client} can be connected to the server.
 * If there is more than maxNoOfClients {@link hr.tel.fer.ilj.lab.lab1.client.Client} they are waiting in order for
 * any client to be done.
 *
 * @author Darko Juraga as part of <a href="https://www.fer.unizg.hr/predmet/ilj_a">Information, Logic and Languages</a> course.
 */
public class Server {
    /**
     * Main method for starting server.
     *
     * @param args maxNoOfClients, port, path
     * @throws Exception {@link IllegalArgumentException} if wrong number of arguments is entered. {@link IOException} if files are not found or socket can't be set.
     */
    public static void main(String[] args) throws Exception {
        Path path;
        int nOfClients;
        int port;
        if (args.length == 3) {
            nOfClients = Integer.parseInt(args[0]);
            port = Integer.parseInt(args[1]);
            path = Paths.get(args[2]);
        } else {
            throw new IllegalArgumentException("Insert command line arguments in order like this: maxNoOfClients port path");
        }
        System.out.println("Maximum number of clients: " + nOfClients + ", port: " + port + ", path: " + path);
        FileVisitor visitor = new FileVisitor();
        Files.walkFileTree(path, visitor);
        List<LogEntry> allLogs = visitor.getAllLogs();
        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService server = Executors.newFixedThreadPool(nOfClients);
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                Query query = new Query(clientSocket, allLogs);
                server.execute(query);
            } catch (Exception ignore) {
            }
        }

    }
}
