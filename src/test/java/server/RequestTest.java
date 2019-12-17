package server;

import org.junit.Assert;
import org.junit.Test;

public class RequestTest {

    @Test
    public void getMethodReturnsGETWhenRequestStartsWithGET() {
        Request testRequest = new Request("GET something");
        Method actual = testRequest.getMethod();
        Method expected = Method.GET;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMethodReturnsINVALIDWhenRequestDoesNotStartWithGET() {
        Request testRequest = new Request("BOO something");
        Method actual = testRequest.getMethod();
        Method expected = Method.INVALID;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPathReturnsAPathWhenPathIsValid() {
        Request testRequest = new Request("GET /simple_get");
        String actual = testRequest.getPath();
        String expected = "/simple_get";
        Assert.assertEquals(expected, actual);
    }
}