package server;

import HTTPComponents.Method;
import org.junit.Assert;
import org.junit.Test;

import static HTTPComponents.StatusLineComponents.VERSION;

public class RequestTest {

    @Test
    public void getMethodReturnsGETWhenRequestStartsWithGET() {
        Request testRequest = new Request("GET /test_path");
        Method actual = testRequest.getMethod();
        Method expected = Method.GET;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMethodReturnsINVALIDWhenRequestDoesNotStartWithGET() {
        Request testRequest = new Request("BOO /test_path");
        Method actual = testRequest.getMethod();
        Method expected = Method.INVALID;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPathReturnsAPathWhenPathIsValid() {
        Request testRequest = new Request("GET /test_path");
        String actual = testRequest.getPath();
        String expected = "/test_path";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getVersionReturnsVersion() {
        Request request = new Request("GET /test_path HTTP/1.1");

        Assert.assertEquals("HTTP/1.1", request.getVersion());
    }

    @Test
    public void getVersionReturnsVersionUsingConstant() {
        Request request = new Request("GET /test_path HTTP/1.1");

        Assert.assertEquals(VERSION, request.getVersion());
    }


    @Test
    public void getBodyReturnsABodyWhenThereIsABody() {
        Request testRequest = new Request("GET /test_path HTTP1.1 Where is the body?");
        String actual = testRequest.getBody();
        String expected = "Where is the body?";
        Assert.assertEquals(expected, actual);
    }




}