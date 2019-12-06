package server;

import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
        args = new String[] {"5000"};
        ServerLogger serverLogger = new ServerLogger();
        IServerSocket socket = new ServerSocketWrapper(serverLogger);
        Dispatcher dispatcher = new Dispatcher(socket, serverLogger);
        dispatcher.listenAndDispatch(args, socket);
    }
}
