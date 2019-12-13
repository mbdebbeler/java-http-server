package server;

import java.util.ArrayList;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.FINE;

public class ConnectionHandler implements Runnable {
    public ISocket socket;
    public ServerLogger serverLogger;

    public ConnectionHandler(ISocket socket, ServerLogger serverLogger) {
        this.socket = socket;
        this.serverLogger = serverLogger;
    }

    public ArrayList<Route> makeRoutes() {
        ArrayList<Route> routes = new ArrayList<Route>();
        Route route = new Route(Method.GET, "/simple_get");
        routes.add(route);
        return null;
    }

    public void run() {
        try {
            String message = socket.receive();
            if (message != null) {
                Request request = new Request(message);
                Router router = new Router(makeRoutes());
                Response response = router.route(request);
                String statusLine = response.getAllPartsOfResponseAsString();
                System.out.println("RESPONSE: " + statusLine);
                serverLogger.logSomething(INFO, statusLine.trim());
                socket.send(statusLine);
            }
        } catch (Exception e) {
            serverLogger.logSomething(FINE, e.getMessage());
        } finally {
            System.out.println("[-] Closing Socket!");
            serverLogger.logSomething(INFO, "[-] Closing Socket!");
            socket.close();
        }
    }
}
