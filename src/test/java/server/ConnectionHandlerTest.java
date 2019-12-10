package server;

import org.junit.Assert;
import org.junit.Test;

public class ConnectionHandlerTest {

    @Test
    public void itSendsAGoodResponseWhenItGetsAGoodMessage() {
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper("GET something");
        ConnectionHandler connectionHandler = new ConnectionHandler(mockSocketWrapper);
        connectionHandler.run();
        String expectedSentMessage = "HTTP/1.1 200 OK\r\n";
        String actualSentMessage = mockSocketWrapper.getSentData();
        Boolean expectedIsClosed = true;
        Boolean actualIsClosed = mockSocketWrapper.getCloseWasCalled();
        Assert.assertEquals(expectedSentMessage, actualSentMessage);
        Assert.assertEquals(expectedIsClosed, actualIsClosed);
    }

}