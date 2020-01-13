package server;

import application.Router;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.FINE;

public class ConnectionHandler implements Runnable {
    private Socket socket;
    private ServerLogger serverLogger;
    private Router router;

    public ConnectionHandler(Socket socket, Router router, ServerLogger serverLogger) {
        this.socket = socket;
        this.serverLogger = serverLogger;
        this.router = router;
    }

    public void run() {
        try {
            String message = socket.receive();
            if (message != null) {
                Request request = new RequestBuilder(message).build();
                serverLogger.logSomething(FINE, "Built");
                Response response = this.router.route(request);
                serverLogger.logSomething(FINE, "Routed");
                String statusLineAsString = new String (response.getStatusLine());
                serverLogger.logSomething(FINE, "statusline");
                serverLogger.logSomething(INFO, statusLineAsString.trim());
                socket.send(response.getResponseBytes());
            }
        } catch (Exception e) {
            serverLogger.logSomething(FINE, "It's the Connection Handler!");
            serverLogger.logSomething(FINE, e.getMessage());
        } finally {
            serverLogger.logSomething(INFO, "[-] Closing Socket!");
            socket.close();
        }
    }
}
