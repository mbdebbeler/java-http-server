package server;

public class MockSocketWrapper implements ISocket {
    public static String sentData;
    public static boolean closeWasCalled = false;

    public String receive() {
        String test = "test message";
        return test;
    }

    public void send(String data) {
        sentData = data.toUpperCase();
    }

    public String getSentData() {
        return sentData;
    }

    public void close() {
        closeWasCalled = true;
    }

    public boolean getCloseWasCalled() {
        return closeWasCalled;
    }
}
