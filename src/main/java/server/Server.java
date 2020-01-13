package server;

import application.Config;
import application.Router;

import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
        int port = (args.length > 0) ? Integer.parseInt(args[0]) : Config.defaultPort;
        ServerLogger serverLogger = new ServerLogger();
        Router router = Config.router;
        ServerSocket socket = new ServerSocketWrapper(serverLogger);
        SocketDispatcher socketDispatcher = new SocketDispatcher(socket, router, serverLogger);
        socketDispatcher.listen(port);
    }
}
