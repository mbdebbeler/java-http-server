package server;

import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
        int port = (args.length > 0) ? Integer.parseInt(args[0]) : Config.defaultPort;
        ServerLogger serverLogger = new ServerLogger();
        RouteFactory routeFactory = new SpinachTestsRouteFactory();
        Router router = new Router(routeFactory.makeRoutes());
        ServerSocket socket = new ServerSocketWrapper(serverLogger);
        SocketDispatcher socketDispatcher = new SocketDispatcher(socket, router, serverLogger);
        socketDispatcher.listen(port);
    }
}
