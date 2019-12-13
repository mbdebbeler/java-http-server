package server;

import org.junit.Test;
import org.junit.Assert;

public class RouteTest {

    @Test
    public void getMethodReturnsAMethod() {
        Route testRoute = new Route(Method.GET, "/test_route", (request) -> {
            return new Response(StatusCode.OK);
        });
        Method expectedMethod = Method.GET;
        Method actualMethod = testRoute.getMethod();
        Assert.assertEquals(expectedMethod, actualMethod);
    }

    @Test
    public void getPathReturnsAString() {
        Route testRoute = new Route(Method.GET, "/test_route", (request) -> {
            return new Response(StatusCode.OK);
        });
        String expectedPath = "/test_route";
        String actualPath = testRoute.getPath();
        Assert.assertEquals(expectedPath, actualPath);
    }

}