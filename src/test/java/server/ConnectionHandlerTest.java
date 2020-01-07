package server;

import HTTPComponents.Method;
import HTTPComponents.StatusCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static HTTPComponents.StatusLineComponents.CRLF;
import static HTTPComponents.StatusLineComponents.NEWLINE;

public class ConnectionHandlerTest {
    Router mockRouter;

    @Before
    public void initializeTestRouterWithRoutes() {
        ArrayList<Route> routes = new ArrayList<Route>();
        Route route1 = new Route(Method.GET, "/test_route", (request) -> {
            return new ResponseBuilder()
                    .setStatusCode(StatusCode.OK)
                    .build();
        });
        Route route2 = new Route(Method.GET, "/test_route_with_body", (request) -> {
            return new ResponseBuilder()
                    .setStatusCode(StatusCode.OK)
                    .build();
        });
        Route route3 = new Route(Method.POST, "/echo_body", (request) -> {
            return new ResponseBuilder()
                    .setStatusCode(StatusCode.OK)
                    .setBody(request.getBody())
                    .build();
        });
        Route route4 = new Route(Method.GET, "/test_redirect", (request) -> {
            return new ResponseBuilder()
                    .addRedirect("http://127.0.0.1:5000/test_simple_get")
                    .setStatusCode(StatusCode.MOVED_PERMANENTLY)
                    .build();
        });
        routes.add(route1);
        routes.add(route2);
        routes.add(route3);
        routes.add(route4);
        mockRouter = new Router(routes);
    }

    @Test
    public void itSendsAGoodResponseWhenItGetsAGoodMessage() {
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper("GET /test_route HTTP/1.1");
        ServerLogger mockServerLogger = new ServerLogger();
        ConnectionHandler connectionHandler = new ConnectionHandler(mockSocketWrapper, mockRouter, mockServerLogger);
        connectionHandler.run();
        String expectedSentMessage = "HTTP/1.1 200 OK" + CRLF + CRLF;
        String actualSentMessage = mockSocketWrapper.getSentDataAsString();
        Boolean expectedIsClosed = true;
        Boolean actualIsClosed = mockSocketWrapper.getCloseWasCalled();
        Assert.assertEquals(expectedSentMessage, actualSentMessage);
        Assert.assertEquals(expectedIsClosed, actualIsClosed);
    }

    @Test
    public void itSendsANotFoundResponseWhenItGetsARequestToARouteThatDoesNotExist() {
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper("GET /not_found_resource HTTP/1.1");
        ServerLogger mockServerLogger = new ServerLogger();
        ConnectionHandler connectionHandler = new ConnectionHandler(mockSocketWrapper, mockRouter, mockServerLogger);
        connectionHandler.run();
        String expectedSentMessage = "HTTP/1.1 404 Not Found" + CRLF + CRLF;
        String actualSentMessage = mockSocketWrapper.getSentDataAsString();
        Boolean expectedIsClosed = true;
        Boolean actualIsClosed = mockSocketWrapper.getCloseWasCalled();
        Assert.assertEquals(expectedSentMessage, actualSentMessage);
        Assert.assertEquals(expectedIsClosed, actualIsClosed);
    }

    @Test
    public void itCanAcceptARequestWithBody() {
        String testRequest = "GET /test_route_with_body HTTP/1.1" + NEWLINE +
                "Content-Length: 9" + NEWLINE +
                "Content-Type: application/x-www-form-urlencoded" + NEWLINE +
                CRLF + CRLF +
                "some body";
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper(testRequest);
        ServerLogger mockServerLogger = new ServerLogger();
        ConnectionHandler connectionHandler = new ConnectionHandler(mockSocketWrapper, mockRouter, mockServerLogger);
        connectionHandler.run();
        String expectedSentMessage = "HTTP/1.1 200 OK" + CRLF + CRLF;

        String actualSentMessage = mockSocketWrapper.getSentDataAsString();
        Boolean expectedIsClosed = true;
        Boolean actualIsClosed = mockSocketWrapper.getCloseWasCalled();
        Assert.assertEquals(expectedSentMessage, actualSentMessage);
        Assert.assertEquals(expectedIsClosed, actualIsClosed);
    }

    @Test
    public void itAcceptsPOSTRequestsWithHeadersAndEchoesTheBody() {
        String testRequest = "POST /echo_body HTTP/1.1" + NEWLINE +
                "Content-Length: 32" + NEWLINE +
                "Content-Type: application/x-www-form-urlencoded" + NEWLINE +
                CRLF + CRLF +
                "Where is the body?";
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper(testRequest);
        ServerLogger mockServerLogger = new ServerLogger();
        ConnectionHandler connectionHandler = new ConnectionHandler(mockSocketWrapper, mockRouter, mockServerLogger);
        connectionHandler.run();
        String expectedSentMessage = "HTTP/1.1 200 OK"
                + CRLF
                + CRLF
                + "Where is the body?";

        String actualSentMessage = mockSocketWrapper.getSentDataAsString();
        Boolean expectedIsClosed = true;
        Boolean actualIsClosed = mockSocketWrapper.getCloseWasCalled();

        Assert.assertEquals(expectedSentMessage, actualSentMessage);
        Assert.assertEquals(expectedIsClosed, actualIsClosed);
    }

    @Test
    public void itRedirectsRequestsAndIncludesNewLocationInHeader() {
        String testRequest = "GET /test_redirect HTTP/1.1" + NEWLINE +
                "Content-Length: 21" + NEWLINE +
                "Content-Type: application/x-www-form-urlencoded" + NEWLINE +
                CRLF;
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper(testRequest);
        ServerLogger mockServerLogger = new ServerLogger();
        ConnectionHandler connectionHandler = new ConnectionHandler(mockSocketWrapper, mockRouter, mockServerLogger);
        connectionHandler.run();
        String expectedSentMessage = "HTTP/1.1 301 Moved Permanently"
                + CRLF
                + "Location: http://127.0.0.1:5000/test_simple_get"
                + CRLF
                + CRLF;
        String actualSentMessage = mockSocketWrapper.getSentDataAsString();
        Boolean expectedIsClosed = true;
        Boolean actualIsClosed = mockSocketWrapper.getCloseWasCalled();

        Assert.assertEquals(expectedSentMessage, actualSentMessage);
        Assert.assertEquals(expectedIsClosed, actualIsClosed);
    }

}