package server;

import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
        args = new String[] {"5000"};
        IServerSocket socket = new ServerSocketWrapper();
        Dispatcher dispatcher = new Dispatcher(socket);
        dispatcher.listenAndDispatch(args, socket);
    }
}
