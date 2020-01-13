package server;

import java.io.IOException;

public interface ServerSocket {
    void createAndListen(int portNumber);
    Socket acceptConnection() throws IOException;
    boolean isClosed();
}
