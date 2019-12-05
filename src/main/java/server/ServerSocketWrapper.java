package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.util.logging.Level.FINE;
import static java.util.logging.Level.INFO;

public class ServerSocketWrapper implements IServerSocket {
    private ServerSocket serverSocket;
    private ServerLogger serverLogger = new ServerLogger();

    public void createAndListen(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
            String waitingConnection = String.format("[-] Listening for connection on port %s", portNumber);
            serverLogger.logSomething(INFO, waitingConnection);
            System.out.println(waitingConnection);
        } catch (IOException e) {
            String connectionError = String.format("[-] Could not listen on port %s", portNumber);
            serverLogger.logSomething(FINE, e.getMessage());
            System.err.println(connectionError);
        }
    }

    public ISocket acceptConnection() {
        try {
            Socket clientSocket = serverSocket.accept();
            String acceptedConnection = "[-] Accepted connection";
            System.out.println(acceptedConnection);
            serverLogger.logSomething(INFO, acceptedConnection);
            return new SocketWrapper(clientSocket);
        } catch (IOException e) {
            String connectionError = "[-] Unable to accept connection from client.";
            System.err.println(connectionError);
            serverLogger.logSomething(FINE, e.getMessage());
        }
        return null;
    }

    public boolean isClosed() {
        return serverSocket.isClosed();
    }

}

