package server;

import java.io.IOException;

public interface IDispatcher {
    Runnable createConnectionHandler() throws IOException;
    void listenAndDispatch(String[] args, IServerSocket serverSocket) throws IOException;
}
