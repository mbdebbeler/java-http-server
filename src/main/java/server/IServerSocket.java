package server;

import java.io.IOException;

public interface IServerSocket {
    void createAndListen(int portNumber);
    ISocket acceptConnection() throws IOException;
    boolean isClosed();
}
