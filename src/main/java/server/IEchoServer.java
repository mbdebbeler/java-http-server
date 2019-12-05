package server;

import java.io.IOException;

public interface IEchoServer {
    Runnable createEchoClientHandler() throws IOException;
    void serve(String[] args, IServerSocket serverSocketWrapper) throws IOException;
}
