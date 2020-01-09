package server;

import HTTPComponents.Method;
import org.junit.Assert;
import org.junit.Test;
import java.util.Map;

import static HTTPComponents.Method.GET;
import static HTTPComponents.StatusLineComponents.CRLF;
import static HTTPComponents.StatusLineComponents.VERSION;

public class RequestTest {

    @Test
    public void getMethodReturnsGETWhenRequestStartsWithGET() {
        Request testRequest = new RequestBuilder("GET /test_path HTTP/1.1").build();
        Method actual = testRequest.getMethod();
        Method expected = GET;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPathReturnsAPathWhenPathIsValid() {
        Request testRequest = new RequestBuilder("GET /test_path HTTP/1.1").build();
        String actual = testRequest.getPath();
        String expected = "/test_path";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getVersionReturnsVersion() {
        Request request = new RequestBuilder("GET /test_path HTTP/1.1").build();
        String actual = request.getVersion();
        String expected = "HTTP/1.1";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getVersionReturnsVersionUsingConstant() {
        Request request = new RequestBuilder("GET /test_path HTTP/1.1").build();
        String actual = request.getVersion();
        String expected = VERSION;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getBodyReturnsABodyWhenThereIsABody() {
        Request testRequest = new RequestBuilder(GET + "/test_path" + VERSION + CRLF + CRLF + "Where is the body?").build();
        String actual = new String(testRequest.getBody());
        String expected = "Where is the body?";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getHeadersReturnsHeadersInAMap() {
        String message = "GET /simple_get HTTP/1.1\n" +
                "Accept-Encoding: gzip,deflate\n" +
                "Accept: */*\n" +
                "User-Agent: Ruby\n" +
                "Connection: close\n" +
                "Host: 127.0.0.1:5000";
        Request request = new RequestBuilder(message).build();
        Map actualHeaders = request.getHeaders();

        Map<String, String> expectedHeaders = Map.of(
                "Accept-Encoding", "gzip,deflate",
                "Accept", "*/*",
                "User-Agent", "Ruby",
                "Connection", "close",
                "Host", "127.0.0.1:5000");

        Assert.assertEquals(expectedHeaders, actualHeaders);
    }

    @Test
    public void canParseIdFromAPathWhenThereASecondSlash() {
        String message = "GET /the_path/the_id HTTP/1.1\n";
        Request request = new RequestBuilder(message).build();
        String expectedId = "the_id";
        String actualId = request.getResourceIdentifier();

        Assert.assertEquals(expectedId, actualId);
    }


}