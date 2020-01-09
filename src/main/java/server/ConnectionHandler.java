package server;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.FINE;

public class ConnectionHandler implements Runnable {
    private ISocket socket;
    private ServerLogger serverLogger;
    private Router router;

    public ConnectionHandler(ISocket socket, Router router, ServerLogger serverLogger) {
        this.socket = socket;
        this.serverLogger = serverLogger;
        this.router = router;
    }

    public void run() {
        try {
            String message = socket.receive();
            if (message != null) {
                Request request = new RequestBuilder(message).build();
                Response response = this.router.route(request);
                String statusLineAsString = response.getStatusLine().toString();
                serverLogger.logSomething(INFO, statusLineAsString.trim());
                socket.send(response.getResponseBytes());
            }
        } catch (Exception e) {
            serverLogger.logSomething(FINE, e.getMessage());
        } finally {
            serverLogger.logSomething(INFO, "[-] Closing Socket!");
            socket.close();
        }
    }
}
