package server;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

public class RouterTest {
    private ArrayList<Route> routes;

    @Before
    public void initializeTestRoutes() {
        routes = new ArrayList<Route>();
        Route route1 = new Route(Method.GET, "/test_route", (request) -> {
            return new Response(StatusCode.OK);
        });
        routes.add(route1);
<<<<<<< HEAD

=======
>>>>>>> 7b3769f... Move request handling logic inside route, add routes
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
        String actualStatusLine = testRouter.route(testRequest).getStatusLine();
        String actualResponseAsString = testRouter.route(testRequest).getAllPartsOfResponseAsString();
        StatusCode expectedStatusCode = StatusCode.OK;
        String expectedStatusLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseAsString = "HTTP/1.1 200 OK\nAllow: GET, HEAD, OPTIONS\r\n";
<<<<<<< HEAD

=======
>>>>>>> 7b3769f... Move request handling logic inside route, add routes

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedResponseAsString, actualResponseAsString);
    }

    @Test
    public void returnsNotFoundForAnOptionsRequestWhenPathDoesNotExist() {
        Request testRequest = new Request("OPTIONS /not_found_resources");
        Router testRouter = new Router(routes);

        StatusCode actualStatusCode = testRouter.route(testRequest).getStatusCode();
        String actualStatusLine = testRouter.route(testRequest).getStatusLine();
        StatusCode expectedStatusCode = StatusCode.NOT_FOUND;
        String expectedStatusLine = "HTTP/1.1 404 Not Found\r\n";

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
    }

}
