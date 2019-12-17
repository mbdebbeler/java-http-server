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
        return new ArrayList<Route>() {{
            add(new Route(Method.GET, "/simple_get", (request) -> {
                return new Response(StatusCode.OK);
            }));
            add(new Route(Method.GET, "/get_with_body", (request) -> {
                return new Response(StatusCode.OK);
            }));
            add(new Route(Method.GET, "/method_options", (request) -> {
                return new Response(StatusCode.OK);
            }));
            add(new Route(Method.GET, "/method_options2", (request) -> {
                return new Response(StatusCode.OK);
            }));
            add(new Route(Method.PUT, "/method_options2", (request) -> {
                return new Response(StatusCode.OK);
            }));
            add(new Route(Method.POST, "/method_options2", (request) -> {
                return new Response(StatusCode.OK);
            }));
        }};
    }

    public void run() {
        try {
            String message = socket.receive();
            if (message != null) {
                Request request = new Request(message);
                Router router = new Router(makeRoutes());
                Response response = router.route(request);
                String responseAsString = response.getAllPartsOfResponseAsString();
                serverLogger.logSomething(INFO, responseAsString.trim());
                socket.send(responseAsString);
            }
        } catch (Exception e) {
            serverLogger.logSomething(FINE, e.getMessage());
        } finally {
            serverLogger.logSomething(INFO, "[-] Closing Socket!");
            socket.close();
        }
    }
}
