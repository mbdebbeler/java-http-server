package server;

import java.io.IOException;

import static java.util.logging.Level.FINE;
import static java.util.logging.Level.INFO;

public class ServerSocketWrapper implements ServerSocket {
    private java.net.ServerSocket serverSocket;
    public ServerLogger serverLogger;

    public ServerSocketWrapper(ServerLogger serverLogger) {
        this.serverLogger = serverLogger;
    }

    public void createAndListen(int portNumber) {
        try {
            serverSocket = new java.net.ServerSocket(portNumber);
            String waitingConnection = String.format("[-] Listening for connection on port %s", portNumber);
            serverLogger.logSomething(INFO, waitingConnection);
        } catch (IOException e) {
            String connectionError = String.format("[-] Could not listen on port %s", portNumber);
            serverLogger.logSomething(FINE, e.getMessage());
            System.err.println(connectionError);
        }
    }

    public Socket acceptConnection() {
        try {
            java.net.Socket clientSocket = serverSocket.accept();
            String acceptedConnection = "[-] Accepted connection";
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

