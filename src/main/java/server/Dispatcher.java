package server;

import java.io.IOException;

public class Dispatcher implements IDispatcher {
    private static IServerSocket serverSocket;
    private ServerLogger serverLogger;
    private Router router;

    public Dispatcher(IServerSocket serverSocket, Router router, ServerLogger serverLogger) {
        this.serverSocket = serverSocket;
        this.router = router;
        this.serverLogger = serverLogger;
    }

    public Runnable createConnectionHandler() throws IOException {
        return new ConnectionHandler(serverSocket.acceptConnection(), router, serverLogger);
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

