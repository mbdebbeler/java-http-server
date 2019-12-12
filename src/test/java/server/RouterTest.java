package server;

import org.junit.Test;
import org.junit.Assert;

public class RouterTest {
    @Test
    public void returnsNotFoundWhenPathDoesNotExist() {
        Request testRequest = new Request("GET /not_found_resource");
        Router testRouter = new Router();
        StatusCode actual = testRouter.route(testRequest).getStatusCode();
        StatusCode expected = StatusCode.NOT_FOUND;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void doesNotReturnNotFoundWhenPathDoesExist() {
        Request testRequest = new Request("GET /simple_get");
        Router testRouter = new Router();
        StatusCode actual = testRouter.route(testRequest).getStatusCode();
        StatusCode expected = StatusCode.OK;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returnsOKAndAllowedMethodsForAnOptionsRequestWhenPathExists() {
        Request testRequest = new Request("OPTIONS /simple_get");
        Router testRouter = new Router();
        StatusCode actualStatusCode = testRouter.route(testRequest).getStatusCode();
        String actualStatusLine = testRouter.route(testRequest).getStatusLine();
        String actualResponseAsString = testRouter.route(testRequest).getAllPartsOfResponseAsString();
        StatusCode expectedStatusCode = StatusCode.OK;
        String expectedStatusLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseAsString= "HTTP/1.1 200 OK\nAllow: OPTIONS, GET, HEAD\r\n";

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedResponseAsString, actualResponseAsString);
    }


}
