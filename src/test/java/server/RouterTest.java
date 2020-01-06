package server;

import HTTPComponents.Method;
import HTTPComponents.StatusCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

import static HTTPComponents.StatusLineComponents.CRLF;

public class RouterTest {
    private ArrayList<Route> routes;

    @Before
    public void initializeTestRoutes() {
        routes = new ArrayList<Route>();
        Route route1 = new Route(Method.GET, "/test_route", (request) -> {
            return new ResponseBuilder()
                    .addStatusCode(StatusCode.OK)
                    .build();
        });
        Route route2 = new Route(Method.HEAD, "/test_route2", (request) -> {
            return new ResponseBuilder()
                    .addStatusCode(StatusCode.OK)
                    .build();
        });
        Route route3 = new Route(Method.POST, "/test_route3", (request) -> {
            return new ResponseBuilder()
                    .addStatusCode(StatusCode.OK)
                    .addBody(request.getBody())
                    .build();
        });
        Route route4 = new Route(Method.GET, "/test_redirect_route", (request) -> {
            return new ResponseBuilder()
                    .addRedirect("http://127.0.0.1:5000/simple_get")
                    .addStatusCode(StatusCode.MOVED_PERMANENTLY)
                    .build();
        });
        routes.add(route1);
        routes.add(route2);
        routes.add(route3);
        routes.add(route4);
    }

    @Test
    public void returnsNotFoundWhenPathDoesNotExist() {
        Request testRequest = new Request("GET /not_found_resource");
        Router testRouter = new Router(routes);
        StatusCode actual = testRouter.route(testRequest).getStatusCode();
        StatusCode expected = StatusCode.NOT_FOUND;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void doesNotReturnNotFoundWhenPathDoesExist() {
        Request testRequest = new Request("GET /test_route");
        Router testRouter = new Router(routes);
        StatusCode actual = testRouter.route(testRequest).getStatusCode();
        StatusCode expected = StatusCode.OK;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returnsOKAndAllowedMethodsForAnOptionsRequestWhenPathExists() {
        Request testRequest = new Request("OPTIONS /test_route");
        Router testRouter = new Router(routes);
        StatusCode actualStatusCode = testRouter.route(testRequest).getStatusCode();
        String actualStatusLine = new String(testRouter.route(testRequest).getStatusLine());
        String actualResponse = new String(testRouter.route(testRequest).getResponseBytes());
        StatusCode expectedStatusCode = StatusCode.OK;
        String expectedStatusLine = "HTTP/1.1 200 OK" + CRLF;
        String expectedResponse = "HTTP/1.1 200 OK" + CRLF + "Allow: GET, HEAD, OPTIONS" + CRLF + CRLF;

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void returnsNotFoundForAnOptionsRequestWhenPathDoesNotExist() {
        Request testRequest = new Request("OPTIONS /not_found_resources");
        Router testRouter = new Router(routes);

        StatusCode actualStatusCode = testRouter.route(testRequest).getStatusCode();
        String actualStatusLine = new String(testRouter.route(testRequest).getStatusLine());
        StatusCode expectedStatusCode = StatusCode.NOT_FOUND;
        String expectedStatusLine = "HTTP/1.1 404 Not Found" + CRLF;

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
    }


    @Test
    public void returnsNotAllowedForARequestWhenPathExistsButMethodDoesNot() {
        Request testRequest = new Request("GET /test_route2");
        Router testRouter = new Router(routes);
        StatusCode actualStatusCode = testRouter.route(testRequest).getStatusCode();
        String actualStatusLine = new String(testRouter.route(testRequest).getStatusLine());
        String actualResponse = new String(testRouter.route(testRequest).getResponseBytes());
        StatusCode expectedStatusCode = StatusCode.NOT_ALLOWED;
        String expectedStatusLine = ("HTTP/1.1 405 Method Not Allowed" + CRLF);
        String expectedResponse = ("HTTP/1.1 405 Method Not Allowed" + CRLF + "Allow: HEAD, OPTIONS" + CRLF + CRLF);

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void returnsAResponseWithBodyToAPOSTRequestWithBody() {
        Request testRequest = new Request("POST /test_route3 HTTP/1.1" + CRLF + CRLF + "Where is the body?");
        Router testRouter = new Router(routes);
        StatusCode actualStatusCode = testRouter.route(testRequest).getStatusCode();
        String actualStatusLine = new String(testRouter.route(testRequest).getStatusLine());
        String actualResponse = new String(testRouter.route(testRequest).getResponseBytes());
        StatusCode expectedStatusCode = StatusCode.OK;
        String expectedStatusLine = ("HTTP/1.1 200 OK" + CRLF);
        String expectedResponse = ("HTTP/1.1 200 OK"
                + CRLF
                + CRLF
                + "Where is the body?");

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedResponse, actualResponse);

    }

    @Test
    public void redirectsAnInvalidPathRequest() {
        Request testRequest = new Request("GET /test_redirect_route HTTP/1.1");
        Router testRouter = new Router(routes);
        StatusCode actualStatusCode = testRouter.route(testRequest).getStatusCode();
        String actualStatusLine = new String(testRouter.route(testRequest).getStatusLine());
        String actualResponse = new String(testRouter.route(testRequest).getResponseBytes());
        StatusCode expectedStatusCode = StatusCode.MOVED_PERMANENTLY;
        String expectedStatusLine = "HTTP/1.1 301 Moved Permanently" + CRLF;
        String expectedResponse = "HTTP/1.1 301 Moved Permanently" + CRLF + "Location: http://127.0.0.1:5000/simple_get" + CRLF + CRLF;

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

}
