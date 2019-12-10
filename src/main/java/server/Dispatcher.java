package server;

import java.io.IOException;

public class Dispatcher implements IDispatcher {
    public static IServerSocket serverSocket;

    public Dispatcher(IServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Runnable createConnectionHandler() throws IOException {
        return new ConnectionHandler(serverSocket.acceptConnection());
    }

    public void listenAndDispatch(String[] args, IServerSocket serverSocket) throws IOException {
        int portNumber = Integer.parseInt(args[0]);
        serverSocket.createAndListen(portNumber);
        while (!serverSocket.isClosed()) {
            Thread thread = new Thread(createConnectionHandler());
            thread.start();
        }
    }
}

