package server;

import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
        args = new String[] {"5000"};
        IServerSocket socketWrapper = new ServerSocketWrapper();
        EchoServer echoServer = new EchoServer(socketWrapper);
        echoServer.serve(args, socketWrapper);
    }
}
