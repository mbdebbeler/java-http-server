package server;

public class MockSocketWrapper implements ISocket {
    private static byte[] sentData;
    private String testMessage;
    public static boolean closeWasCalled = false;

    public MockSocketWrapper() {
        this.testMessage = "test message";
    }
    ;

    public MockSocketWrapper(String testMessage) {
        this.testMessage = testMessage;
    }

    public String receive() {
        return testMessage;
    }

    public void send(byte[] data) {
        sentData = data;
    }

    public String getSentDataAsString() {
        return new String(sentData);
    }

    public byte[] getSentData() {
        return sentData;
    }

    public void close() {
        closeWasCalled = true;
    }

    public boolean getCloseWasCalled() {
        return closeWasCalled;
    }
}
