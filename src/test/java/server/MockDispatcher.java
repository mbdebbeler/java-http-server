package server;

import java.io.IOException;

public class MockDispatcher implements Dispatcher {
    private final ServerSocket mockServerSocketWrapper;

    public MockDispatcher(ServerSocket mockServerSocketWrapper) {
        this.mockServerSocketWrapper = mockServerSocketWrapper;
    }

    public Runnable dispatch() throws IOException {
        return null;
    }

    public void listen(int port) throws IOException {
        mockServerSocketWrapper.createAndListen(port);
    }
}
