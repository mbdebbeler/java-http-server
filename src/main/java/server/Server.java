package server;

import java.io.IOException;
import java.util.logging.Level;

public class Server {

    public static void main(String[] args) throws IOException {
        args = new String[] {"5000"};
        IServerSocket socketWrapper = new ServerSocketWrapper();
        EchoServer echoServer = new EchoServer(socketWrapper);
        echoServer.serve(args, socketWrapper);
        MyLogger logger = new MyLogger();
        logger.init("Logs");
        logger.logSomething(Level.INFO, "My log has something to tell you.");
    }
}
