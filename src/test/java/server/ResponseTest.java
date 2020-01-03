package server;

import HTTPComponents.StatusCode;
import org.junit.Assert;
import org.junit.Test;

import static HTTPComponents.StatusLineComponents.CRLF;
import static HTTPComponents.StatusLineComponents.NEWLINE;

public class ResponseTest {

    @Test
    public void getStatusCodeReturnsACode() {
        Response testResponse = new Response(StatusCode.BAD_REQUEST);
        StatusCode actual = testResponse.getStatusCode();
        StatusCode expected = StatusCode.BAD_REQUEST;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStatusLineReturnsProperlyEncodedOKResponse() {
        Response testResponse = new Response(StatusCode.OK);
        String actual = testResponse.getStatusLine();
        String expected = "HTTP/1.1 200 OK" + CRLF;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStatusLineReturnsProperlyEncodedBadRequestResponse() {
        Response testResponse = new Response(StatusCode.BAD_REQUEST);
        String actual = testResponse.getStatusLine();
        String expected = "HTTP/1.1 400 Bad Request" + CRLF;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getEntireResponseDoesNotEncodeASecondLineWhenThereIsNotOne() {
        Response testResponse = new Response(StatusCode.OK);
        String actual = testResponse.getEntireResponse();
        String expected = "HTTP/1.1 200 OK" + CRLF;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getEntireResponseReturnsBodyWhenRequestHasBody() {
        Response testResponse = new Response(StatusCode.OK,"Where is the body?");
        String actual = testResponse.getEntireResponse();
        String expected = "HTTP/1.1 200 OK"
                + NEWLINE
                + "Content-Length: 18"
                + NEWLINE
                + "Content-Type: text/html"
                + CRLF
                + CRLF
                + "Where is the body?";
        Assert.assertEquals(expected, actual);
    }

}