package server;

import HTTPComponents.StatusCode;
import org.junit.Assert;
import org.junit.Test;

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
        String expected = "HTTP/1.1 200 OK\r\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStatusLineReturnsProperlyEncodedBadRequestResponse() {
        Response testResponse = new Response(StatusCode.BAD_REQUEST);
        String actual = testResponse.getStatusLine();
        String expected = "HTTP/1.1 400 Bad Request\r\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllPartsAsStringDoesNotEncodeASecondLineWhenThereIsNotOne() {
        Response testResponse = new Response(StatusCode.OK);
        String actual = testResponse.getAllPartsOfResponseAsString();
        String expected = "HTTP/1.1 200 OK\r\n";
        Assert.assertEquals(expected, actual);
    }


}