package server;

import application.Router;

import java.io.IOException;

public class SocketDispatcher implements Dispatcher {
    private static ServerSocket serverSocket;
    private ServerLogger serverLogger;
    private Router router;

    public SocketDispatcher(ServerSocket serverSocket, Router router, ServerLogger serverLogger) {
        this.serverSocket = serverSocket;
        this.router = router;
        this.serverLogger = serverLogger;
    }

    public Runnable dispatch() throws IOException {
        return new ConnectionHandler(serverSocket.acceptConnection(), router, serverLogger);
    }

    public void listen(int port) throws IOException {
        serverSocket.createAndListen(port);
        while (!serverSocket.isClosed()) {
            Thread thread = new Thread(dispatch());
            thread.start();
        }
    }
}

