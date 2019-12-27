package server;

import HTTPComponents.StatusCode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ResponseBuilderTest {

    @Test
    public void responseBuilderReturnsADefaultResponse() {
        Response testResponse = new ResponseBuilder().build();
        StatusCode expectedStatusCode = StatusCode.OK;
        String expectedStatusLine = "HTTP/1.1 200 OK\r\n";
        StatusCode actualStatusCode = testResponse.getStatusCode();
        String actualStatusLine = testResponse.getStatusLine();

        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void CanAddAStatusCode() {
        Response testResponse = new ResponseBuilder().addStatusCode(StatusCode.NOT_FOUND).build();
        StatusCode expectedStatusCode = StatusCode.NOT_FOUND;
        String expectedStatusLine = "HTTP/1.1 404 Not Found\r\n";
        StatusCode actualStatusCode = testResponse.getStatusCode();
        String actualStatusLine = testResponse.getStatusLine();

        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void responseBuildsResponseWithReceivedBody() {
        String incomingRequest = "POST /simple_get HTTP/1.1\n" +
                "Content-Length: 32\n" +
                "Content-Type: text/html; charset=UTF-8\n" +
                "\r\n" +
                "some body that could be anything";
        Request request = new Request(incomingRequest);

        Response actualResponse = new ResponseBuilder().addStatusCode(StatusCode.OK).addBody(request.getBody()).build();

        Assert.assertEquals("HTTP/1.1 200 OK\r\nsome body that could be anything", actualResponse.getEntireResponse());
    }

}
