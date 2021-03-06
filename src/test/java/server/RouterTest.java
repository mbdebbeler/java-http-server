package server;

import server.HTTPComponents.Method;
import server.HTTPComponents.StatusCode;
import application.Handler.DefaultRequestHandler;
import application.Handler.GetResourceHandler;
import application.Handler.PostRequestHandler;
import application.Router;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

import static server.HTTPComponents.StatusLineComponents.*;

public class RouterTest {
    private ArrayList<Route> routes;

    @Before
    public void initializeTestRoutes() {
        routes = new ArrayList<Route>();
        Route route1 = new Route(Method.GET, "/test_route", new DefaultRequestHandler());
        Route route2 = new Route(Method.HEAD, "/test_route2", new DefaultRequestHandler());
        Route route3 = new Route(Method.POST, "/test_route3", new PostRequestHandler());
        Route route4 = new Route(Method.GET, "/test_redirect_route", (request) -> {
            return new ResponseBuilder()
                    .addRedirect("http://127.0.0.1:5000/simple_get")
                    .setStatusCode(StatusCode.MOVED_PERMANENTLY)
                    .build();
        });
        Route route5 = new Route(Method.DELETE, "/test_images", new MockDeleteRequestHandler());
        Route route6 = new Route(Method.POST, "/test_images", new PostRequestHandler());
        Route route7 = new Route(Method.GET, "/test_images", new GetResourceHandler());
        routes.add(route1);
        routes.add(route2);
        routes.add(route3);
        routes.add(route4);
        routes.add(route5);
        routes.add(route6);
        routes.add(route7);
    }

    @Test
    public void returnsNotFoundWhenPathDoesNotExist() {
        Request testRequest = new RequestBuilder("GET /not_found_resource HTTP/1.1").build();
        Router testRouter = new Router(routes);
        StatusCode actual = testRouter.route(testRequest).getStatusCode();
        StatusCode expected = StatusCode.NOT_FOUND;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void doesNotReturnNotFoundWhenPathDoesExist() {
        Request testRequest = new RequestBuilder("GET /test_route HTTP/1.1").build();
        Router testRouter = new Router(routes);
        StatusCode actual = testRouter.route(testRequest).getStatusCode();
        StatusCode expected = StatusCode.OK;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returnsOKAndAllowedMethodsForAnOptionsRequestWhenPathExists() {
        Request testRequest = new RequestBuilder("OPTIONS /test_route HTTP/1.1").build();
        Router testRouter = new Router(routes);
        Response testResponse = testRouter.route(testRequest);
        StatusCode actualStatusCode = testResponse.getStatusCode();
        String actualStatusLine = new String(testResponse.getStatusLine());
        String actualResponse = new String(testResponse.getResponseBytes());
        StatusCode expectedStatusCode = StatusCode.OK;
        String expectedStatusLine = "HTTP/1.1 200 OK" + CRLF;
        String expectedResponse = "HTTP/1.1 200 OK" + CRLF + "Allow: GET, HEAD, OPTIONS" + CRLF + CRLF;

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void returnsNotFoundForAnOptionsRequestWhenPathDoesNotExist() {
        Request testRequest = new RequestBuilder("OPTIONS /not_found_resources HTTP/1.1").build();
        Router testRouter = new Router(routes);

        Response testResponse = testRouter.route(testRequest);
        StatusCode actualStatusCode = testResponse.getStatusCode();
        String actualStatusLine = new String(testResponse.getStatusLine());
        StatusCode expectedStatusCode = StatusCode.NOT_FOUND;
        String expectedStatusLine = "HTTP/1.1 404 Not Found" + CRLF;

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
    }


    @Test
    public void returnsNotAllowedForARequestWhenPathExistsButMethodDoesNot() {
        Request testRequest = new RequestBuilder("GET /test_route2 HTTP/1.1").build();
        Router testRouter = new Router(routes);
        Response testResponse = testRouter.route(testRequest);
        StatusCode actualStatusCode = testResponse.getStatusCode();
        String actualStatusLine = new String(testResponse.getStatusLine());
        String actualResponse = new String(testResponse.getResponseBytes());
        StatusCode expectedStatusCode = StatusCode.NOT_ALLOWED;
        String expectedStatusLine = ("HTTP/1.1 405 Method Not Allowed" + CRLF);
        String expectedResponse = ("HTTP/1.1 405 Method Not Allowed" + CRLF + "Allow: HEAD, OPTIONS" + CRLF + CRLF);

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void returnsAResponseWithBodyToAPOSTRequestWithBody() {
        Request testRequest = new RequestBuilder("POST /test_route3/test.txt HTTP/1.1" + CRLF + CRLF + "Where is the body?").build();
        Router testRouter = new Router(routes);
        Response testResponse = testRouter.route(testRequest);
        StatusCode actualStatusCode = testResponse.getStatusCode();
        String actualStatusLine = new String(testResponse.getStatusLine());
        String actualResponse = new String(testResponse.getResponseBytes());
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
        Request testRequest = new RequestBuilder("GET /test_redirect_route HTTP/1.1").build();
        Router testRouter = new Router(routes);
        Response testResponse = testRouter.route(testRequest);
        StatusCode actualStatusCode = testResponse.getStatusCode();
        String actualStatusLine = new String(testResponse.getStatusLine());
        String actualResponse = new String(testResponse.getResponseBytes());
        StatusCode expectedStatusCode = StatusCode.MOVED_PERMANENTLY;
        String expectedStatusLine = "HTTP/1.1 301 Moved Permanently" + CRLF;
        String expectedResponse = "HTTP/1.1 301 Moved Permanently" + CRLF + "Location: http://127.0.0.1:5000/simple_get" + CRLF + CRLF;

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void postsAResourceInADirectory() {
        String imageBody = new String(new MockResourceHandler().read("post-test-2.jpeg"));
        Request testRequest = new RequestBuilder(Method.POST + SPACE + "/test_images/post-test.jpeg" + SPACE + VERSION + CRLF + CRLF + imageBody).build();
        Router testRouter = new Router(routes);
        Response testResponse = testRouter.route(testRequest);
        StatusCode actualStatusCode = testResponse.getStatusCode();
        String actualStatusLine = new String(testResponse.getStatusLine());
        String actualResponse = new String(testResponse.getResponseBytes());
        StatusCode expectedStatusCode = StatusCode.OK;
        String expectedStatusLine = "HTTP/1.1 200 OK" + CRLF;
        String expectedResponse = "HTTP/1.1 200 OK" + CRLF + CRLF + imageBody;

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void deletesAResourceInADirectory() {
        Request testRequest = new RequestBuilder("DELETE /test_images/delete-test.jpg HTTP/1.1").build();
        Router testRouter = new Router(routes);
        Response testResponse = testRouter.route(testRequest);
        StatusCode actualStatusCode = testResponse.getStatusCode();
        String actualStatusLine = new String(testResponse.getStatusLine());
        String actualResponse = new String(testResponse.getResponseBytes());
        StatusCode expectedStatusCode = StatusCode.NO_CONTENT;
        String expectedStatusLine = "HTTP/1.1 204 No Content" + CRLF;
        String expectedResponse = "HTTP/1.1 204 No Content" + CRLF + CRLF;

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getsAnIndexWhenThereIsNoResourceIdentifier() {
        Request testRequest = new RequestBuilder("GET /test_images HTTP/1.1").build();
        Router testRouter = new Router(routes);
        Response testResponse = testRouter.route(testRequest);
        StatusCode actualStatusCode = testResponse.getStatusCode();
        String actualStatusLine = new String(testResponse.getStatusLine());
        String actualResponse = new String(testResponse.getResponseBytes());
        StatusCode expectedStatusCode = StatusCode.OK;
        String expectedStatusLine = "HTTP/1.1 200 OK" + CRLF;
        String expectedResponse = "HTTP/1.1 200 OK"
                + CRLF + "Content-Type: text/html"
                + CRLF
                + CRLF
                + "<h1>Available Images:</h1>"
                + CRLF
                + "<ul>"
                + CRLF
                + "<li><a href=/images/big-test.jpg>big-test.jpg</a></li>"
                + CRLF
                + "<li><a href=/images/delete_test.jpg>delete_test.jpg</a></li>"
                + CRLF
                + "<li><a href=/images/index.html>index.html</a></li>"
                + CRLF
                + "<li><a href=/images/new_test.jpeg>new_test.jpeg</a></li>"
                + CRLF
                + "<li><a href=/images/post-test-2.jpeg>post-test-2.jpeg</a></li>"
                + CRLF
                + "<li><a href=/images/post-test.jpeg>post-test.jpeg</a></li>"
                + CRLF
                + "<li><a href=/images/small-test.jpeg>small-test.jpeg</a></li>"
                + CRLF
                + "<li><a href=/images/test.html>test.html</a></li>"
                + CRLF
                + "<li><a href=/images/test.txt>test.txt</a></li>"
                + CRLF
                + "</ul>";

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedResponse, actualResponse);
    }
}
