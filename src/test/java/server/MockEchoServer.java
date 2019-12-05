package server;

import java.io.IOException;

public class MockEchoServer implements IEchoServer {
    private final IServerSocket mockServerSocketWrapper;

    public MockEchoServer(IServerSocket mockServerSocketWrapper) {
        this.mockServerSocketWrapper = mockServerSocketWrapper;
    }

    public Runnable createEchoClientHandler() throws IOException {
        return null;
    }

    public void serve(String[] args, IServerSocket mockServerSocketWrapper) throws IOException {
        int portNumber = Integer.parseInt(args[0]);
        mockServerSocketWrapper.createAndListen(portNumber);
    }
}
