package server;

import java.io.IOException;

public class MockDispatcher implements IDispatcher {
    private final IServerSocket mockServerSocketWrapper;

    public MockDispatcher(IServerSocket mockServerSocketWrapper) {
        this.mockServerSocketWrapper = mockServerSocketWrapper;
    }

    public Runnable createConnectionHandler() throws IOException {
        return null;
    }

    public void listenAndDispatch(String[] args, IServerSocket mockServerSocketWrapper) throws IOException {
        int portNumber = Integer.parseInt(args[0]);
        mockServerSocketWrapper.createAndListen(portNumber);
    }
}
