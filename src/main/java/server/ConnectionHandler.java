package server;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.FINE;

public class ConnectionHandler implements Runnable {
    public ISocket socket;
    private ServerLogger serverLogger = new ServerLogger();

    public ConnectionHandler(ISocket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            String message = socket.receive();
            if (message != null) {
                Request request = new Request(message);
                RequestHandler handler = new RequestHandler(request);
                Response response = handler.buildResponse();
                String statusLine = response.getStatusLine();
                System.out.println("RESPONSE: " + statusLine);
                serverLogger.logSomething(INFO, statusLine);
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
