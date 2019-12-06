package server;

import java.io.IOException;

public class Dispatcher implements IDispatcher {
    public static IServerSocket serverSocket;
    public ServerLogger serverLogger;

    public Dispatcher(IServerSocket serverSocket, ServerLogger serverLogger) {
        this.serverSocket = serverSocket;
        this.serverLogger = serverLogger;
    }

    public Runnable createConnectionHandler() throws IOException {
        return new ConnectionHandler(serverSocket.acceptConnection(), serverLogger);
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

