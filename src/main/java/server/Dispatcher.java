package server;

import java.io.IOException;

public interface Dispatcher {
    Runnable dispatch() throws IOException;
    void listen(int port) throws IOException;
}
