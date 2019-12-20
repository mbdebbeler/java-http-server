package server;

import HTTPComponents.StatusCode;
import org.junit.Assert;
import org.junit.Test;

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

}
