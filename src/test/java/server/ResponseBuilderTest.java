package server;

import HTTPComponents.StatusCode;
import org.junit.Assert;
import org.junit.Test;

import static HTTPComponents.StatusLineComponents.CRLF;
import static HTTPComponents.StatusLineComponents.NEWLINE;

public class ResponseBuilderTest {

    @Test
    public void responseBuilderReturnsADefaultResponse() {
        Response testResponse = new ResponseBuilder().setStatusCode(StatusCode.OK).build();
        StatusCode expectedStatusCode = StatusCode.OK;
        String expectedStatusLine = "HTTP/1.1 200 OK" + CRLF;
        StatusCode actualStatusCode = testResponse.getStatusCode();
        String actualStatusLine = new String(testResponse.getStatusLine());

        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void canAddAStatusCode() {
        Response testResponse = new ResponseBuilder().setStatusCode(StatusCode.NOT_FOUND).build();
        StatusCode expectedStatusCode = StatusCode.NOT_FOUND;
        String expectedStatusLine = "HTTP/1.1 404 Not Found" + CRLF;
        StatusCode actualStatusCode = testResponse.getStatusCode();
        String actualStatusLine = new String(testResponse.getStatusLine());

        Assert.assertEquals(expectedStatusLine, actualStatusLine);
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }


    @Test
    public void responseBuildsEchoResponseWithReceivedBody() {
        String incomingRequest = "POST /simple_get HTTP/1.1" + NEWLINE +
                "Content-Length: 32" + NEWLINE +
                "Content-Type: text/html; charset=UTF-8" + NEWLINE +
                CRLF + CRLF +
                "some body that could be anything";
        Request request = new RequestBuilder(incomingRequest).build();

        String actualResponse = new String(new ResponseBuilder().setStatusCode(StatusCode.OK).setBody(request.getBody()).build().getResponseBytes());
        String expectedResponse = "HTTP/1.1 200 OK"
                + CRLF
                + CRLF
                + "some body that could be anything";

        Assert.assertEquals(expectedResponse, actualResponse);
    }

}
