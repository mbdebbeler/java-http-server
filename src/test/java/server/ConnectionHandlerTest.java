package server;

import org.junit.Assert;
import org.junit.Test;

public class ConnectionHandlerTest {

    @Test
    public void itSendsAGoodResponseWhenItGetsAGoodMessage() {
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper("GET /simple_get");
        ServerLogger mockServerLogger = new ServerLogger();
        ConnectionHandler connectionHandler = new ConnectionHandler(mockSocketWrapper, mockServerLogger);
        connectionHandler.run();
        String expectedSentMessage = "HTTP/1.1 200 OK\r\n";
        String actualSentMessage = mockSocketWrapper.getSentData();
        Boolean expectedIsClosed = true;
        Boolean actualIsClosed = mockSocketWrapper.getCloseWasCalled();
        Assert.assertEquals(expectedSentMessage, actualSentMessage);
        Assert.assertEquals(expectedIsClosed, actualIsClosed);
    }

    @Test
    public void itSendsANotFoundResponseWhenItGetsARequestToARouteThatDoesNotExist() {
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper("GET /not_found_resource");
        ServerLogger mockServerLogger = new ServerLogger();
        ConnectionHandler connectionHandler = new ConnectionHandler(mockSocketWrapper, mockServerLogger);
        connectionHandler.run();
        String expectedSentMessage = "HTTP/1.1 404 Not Found\r\n";
        String actualSentMessage = mockSocketWrapper.getSentData();
        Boolean expectedIsClosed = true;
        Boolean actualIsClosed = mockSocketWrapper.getCloseWasCalled();
        Assert.assertEquals(expectedSentMessage, actualSentMessage);
        Assert.assertEquals(expectedIsClosed, actualIsClosed);
    }

    @Test
    public void itSendsAllowedMethodsWhenItGetsAnOptionsRequest() {
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper("OPTIONS /simple_get");
        ServerLogger mockServerLogger = new ServerLogger();
        ConnectionHandler connectionHandler = new ConnectionHandler(mockSocketWrapper, mockServerLogger);
        connectionHandler.run();
<<<<<<< HEAD
        String expectedSentMessage = "HTTP/1.1 200 OK\nAllow: GET, HEAD, OPTIONS\r\n";
=======
        String expectedSentMessage = "HTTP/1.1 200 OK\nAllow: OPTIONS, GET, HEAD\r\n";
>>>>>>> 99b7977... Implement naive OPTIONS method with hardcoding
        String actualSentMessage = mockSocketWrapper.getSentData();
        Boolean expectedIsClosed = true;
        Boolean actualIsClosed = mockSocketWrapper.getCloseWasCalled();
        Assert.assertEquals(expectedSentMessage, actualSentMessage);
        Assert.assertEquals(expectedIsClosed, actualIsClosed);
    }

}