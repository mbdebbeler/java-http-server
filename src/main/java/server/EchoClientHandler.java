package server;

public class EchoClientHandler implements Runnable {
    public ISocket socketWrapper;

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
                    socketWrapper.send(clientMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("[-] Closing Socket!");
            socketWrapper.close();
        }
    }
}
