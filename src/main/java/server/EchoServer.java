package server;

import java.io.IOException;

public class EchoServer implements IEchoServer {
    public static IServerSocket serverSocketWrapper;

    public EchoServer(IServerSocket serverSocketWrapper) {
        this.serverSocketWrapper = serverSocketWrapper;
    }

    public Runnable createEchoClientHandler() throws IOException {
        return new EchoClientHandler(serverSocketWrapper.acceptConnection());
    }

    public void serve(String[] args, IServerSocket serverSocketWrapper) throws IOException {
        int portNumber = Integer.parseInt(args[0]);
        serverSocketWrapper.createAndListen(portNumber);
        while (!serverSocketWrapper.isClosed()) {
            Thread thread = new Thread(createEchoClientHandler());
            thread.start();
        }
    }
}

