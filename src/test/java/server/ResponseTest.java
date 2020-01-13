package server;

import server.HTTPComponents.StatusCode;
import org.junit.Assert;
import org.junit.Test;


public class ResponseTest {

    @Test
    public void responseAddsAStatusCode() {
        Response testResponse = new ResponseBuilder()
                .setStatusCode(StatusCode.OK)
                .build();
        StatusCode actualStatusCode = testResponse.getStatusCode();
        StatusCode expectedStatusCode = StatusCode.OK;
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void addsAnEmptyBody() {
        Response testResponse = new ResponseBuilder()
                .setStatusCode(StatusCode.OK)
                .build();
        String actualBody = new String(testResponse.getBody());
        String expectedBody = new String("".getBytes());
        Assert.assertEquals(expectedBody, actualBody);
    }

    @Test
    public void addsABody() {
        Response testResponse = new ResponseBuilder()
                .setStatusCode(StatusCode.OK)
                .setBody("test body".getBytes())
                .build();
        String actualBody = new String(testResponse.getBody());
        String expectedBody = "test body";
        Assert.assertEquals(expectedBody, actualBody);
    }



    @Test
    public void addsHeaders() {
        Response testResponse = new ResponseBuilder()
                .setStatusCode(StatusCode.OK)
                .addHeader("Allow", "GET")
                .setBody("test body".getBytes())
                .build();
        String actualResponse = new String(testResponse.getResponseBytes());
        String expectedResponse = "HTTP/1.1 200 OK\r\nAllow: GET\r\n\r\ntest body";
        Assert.assertEquals(expectedResponse, actualResponse);
    }
}
