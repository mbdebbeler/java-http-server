package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements IServerSocket {
    private ServerSocket serverSocket;

    public void createAndListen(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
            String waitingConnection = String.format("[-] Listening for connection on port %s", portNumber);
            System.out.println(waitingConnection);
        } catch (IOException e) {
            String connectionError = String.format("[-] Could not listen on port %s", portNumber);
            System.err.println(connectionError);
            e.printStackTrace();
        }
    }

    public ISocket acceptConnection() {
        try {
            Socket clientSocket = serverSocket.accept();
            System.out.println("[-] Accepted connection");
            return new SocketWrapper(clientSocket);
        } catch (IOException e) {
            String connectionError = "[-] Unable to accept connection from client.";
            System.err.println(connectionError);
            e.printStackTrace();
        }
        return null;
    }

    public boolean isClosed() {
        return serverSocket.isClosed();
    }

}

