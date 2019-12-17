package server;

import org.junit.Test;
import org.junit.Assert;

public class RequestHandlerTest {

    @Test
    public void returnsOKIfRequestIsAGet() {
        Request testRequest = new Request("GET something");
        RequestHandler requestHandler = new RequestHandler(testRequest);
        StatusCode actual = requestHandler.buildResponse().getStatusCode();
        StatusCode expected = StatusCode.OK;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returnsOKIfRequestIsAHead() {
        Request testRequest = new Request("HEAD something");
        RequestHandler requestHandler = new RequestHandler(testRequest);
        StatusCode actual = requestHandler.buildResponse().getStatusCode();
        StatusCode expected = StatusCode.OK;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returnsBAD_REQUESTIfMethodDoesNotExist() {
        Request testRequest = new Request("BOO something");
        RequestHandler requestHandler = new RequestHandler(testRequest);
        StatusCode actual = requestHandler.buildResponse().getStatusCode();
        StatusCode expected = StatusCode.BAD_REQUEST;

        Assert.assertEquals(expected, actual);
    }

}