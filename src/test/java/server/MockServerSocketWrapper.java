package server;

public class MockServerSocketWrapper implements IServerSocket {

    public static boolean isListening = false;
    public static boolean isClosed = false;

    public MockServerSocketWrapper(int portNumber) {
    }

    public void createAndListen(int portNumber) {
        isListening = true;
    }

    public boolean getIsListening() {
        return isListening;
    }

    public MockSocketWrapper acceptConnection() {
        return new MockSocketWrapper();
    }

    public boolean isClosed() {
        return isClosed;
    }

}
