package server;

public class MockSocketWrapper implements ISocket {
    public static String sentData;
    private String testMessage;
    public static boolean closeWasCalled = false;

    public MockSocketWrapper(){
        this.testMessage = "test message";
    };

    public MockSocketWrapper(String testMessage) {
        this.testMessage = testMessage;
    }

    public String receive() {
        return testMessage;
    }

    public void send(String data) {
        sentData = data;
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
