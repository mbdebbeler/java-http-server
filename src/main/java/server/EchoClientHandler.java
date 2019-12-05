package server;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.FINE;

public class EchoClientHandler implements Runnable {
    public ISocket socketWrapper;
    private ServerLogger serverLogger = new ServerLogger();

    public EchoClientHandler(ISocket socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    public void run() {
        try {
            String clientMessage;
            while ((clientMessage = socketWrapper.receive()) != null) {
                if (clientMessage.toLowerCase().trim().equals("exit")) {
                    break;
                } else {
                    String printMe = String.format("[-] Client says: %s", clientMessage);
                    System.out.println(printMe);
                    serverLogger.logSomething(INFO, printMe);
                    socketWrapper.send(clientMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            serverLogger.logSomething(FINE, e.getMessage());
        } finally {
            System.out.println("[-] Closing Socket!");
            serverLogger.logSomething(INFO, "[-] Closing Socket!");
            socketWrapper.close();
        }
    }
}
