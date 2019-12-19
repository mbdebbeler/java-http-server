package server;

import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
        args = new String[] {"5000"};
        ServerLogger serverLogger = new ServerLogger();
        IRouteFactory routeFactory = new RouteFactory();
        Router router = new Router(routeFactory.makeRoutes());
        IServerSocket socket = new ServerSocketWrapper(serverLogger);
        Dispatcher dispatcher = new Dispatcher(socket, router, serverLogger);
        dispatcher.listenAndDispatch(args, socket);
    }
}
