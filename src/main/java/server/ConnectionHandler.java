package server;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.FINE;

public class ConnectionHandler implements Runnable {
    public ISocket socketWrapper;
    private ServerLogger serverLogger = new ServerLogger();

    public ConnectionHandler(ISocket socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    public void run() {
        try {
            String message = socketWrapper.receive();
            if (message != null) {
                Request request = new Request(message);
                RequestHandler handler = new RequestHandler(request);
                Response response = handler.buildResponse();
                String statusLine = response.getStatusLine();
                System.out.println("RESPONSE: " + statusLine);
                serverLogger.logSomething(INFO, statusLine);
                socketWrapper.send(statusLine);
            }
        } catch (Exception e) {
            serverLogger.logSomething(FINE, e.getMessage());
        } finally {
            System.out.println("[-] Closing Socket!");
            serverLogger.logSomething(INFO, "[-] Closing Socket!");
            socketWrapper.close();
        }
    }
}
