package server;

import org.junit.Test;
import org.junit.Assert;

public class ResponseBuilderTest {

    @Test
    public void returnsOKIfRequestIsAGet() {
        Request testRequest = new Request("GET something");
        ResponseBuilder responseBuilder = new ResponseBuilder(testRequest);
        StatusCode actual = responseBuilder.buildResponse().getStatusCode();
        StatusCode expected = StatusCode.OK;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returnsOKIfRequestIsAHead() {
        Request testRequest = new Request("HEAD something");
        ResponseBuilder responseBuilder = new ResponseBuilder(testRequest);
        StatusCode actual = responseBuilder.buildResponse().getStatusCode();
        StatusCode expected = StatusCode.OK;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returnsBAD_REQUESTIfMethodDoesNotExist() {
        Request testRequest = new Request("BOO something");
        ResponseBuilder responseBuilder = new ResponseBuilder(testRequest);
        StatusCode actual = responseBuilder.buildResponse().getStatusCode();
        StatusCode expected = StatusCode.BAD_REQUEST;

        Assert.assertEquals(expected, actual);
    }

}