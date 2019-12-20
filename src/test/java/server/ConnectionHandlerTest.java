package server;

import HTTPComponents.Method;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ConnectionHandlerTest {
    Router mockRouter;

    @Before
    public void initializeTestRouterWithRoutes() {
        ArrayList<Route> routes = new ArrayList<Route>();
        Route route1 = new Route(Method.GET, "/test_route", (request) -> {
            return new ResponseBuilder().build();
        });
        Route route2 = new Route(Method.GET, "/test_route_with_body", (request) -> {
            return new ResponseBuilder().build();
        });
        routes.add(route1);
        routes.add(route2);
        mockRouter = new Router(routes);
    }

    @Test
    public void itSendsAGoodResponseWhenItGetsAGoodMessage() {
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper("GET /test_route");
        ServerLogger mockServerLogger = new ServerLogger();
        ConnectionHandler connectionHandler = new ConnectionHandler(mockSocketWrapper, mockRouter, mockServerLogger);
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
        ConnectionHandler connectionHandler = new ConnectionHandler(mockSocketWrapper, mockRouter, mockServerLogger);
        connectionHandler.run();
        String expectedSentMessage = "HTTP/1.1 404 Not Found\r\n";
        String actualSentMessage = mockSocketWrapper.getSentData();
        Boolean expectedIsClosed = true;
        Boolean actualIsClosed = mockSocketWrapper.getCloseWasCalled();
        Assert.assertEquals(expectedSentMessage, actualSentMessage);
        Assert.assertEquals(expectedIsClosed, actualIsClosed);
    }

    @Test
    public void itSendsAResponseWithBodyWhenItGetsARequestWithBody() {
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper("GET /test_route HTTP/1.1" + "\r\n" + "Where is the body?");
        ServerLogger mockServerLogger = new ServerLogger();
        ConnectionHandler connectionHandler = new ConnectionHandler(mockSocketWrapper, mockRouter, mockServerLogger);
        connectionHandler.run();
        String expectedSentMessage = "HTTP/1.1 200 OK\r\nWhere is the body?\r\n";

        String actualSentMessage = mockSocketWrapper.getSentData();
        Boolean expectedIsClosed = true;
        Boolean actualIsClosed = mockSocketWrapper.getCloseWasCalled();
        Assert.assertEquals(expectedSentMessage, actualSentMessage);
        Assert.assertEquals(expectedIsClosed, actualIsClosed);
    }

}