package server;

import org.junit.Test;
import org.junit.Assert;

public class RouteTest {

    @Test
    public void getMethodReturnsAMethod() {
        Route testRoute = new Route(Method.GET, "/simple_get");
        Method expectedMethod = Method.GET;
        Method actualMethod = testRoute.getMethod();
        Assert.assertEquals(expectedMethod, actualMethod);
    }

    @Test
    public void getPathReturnsAString() {
        Route testRoute = new Route(Method.GET, "/simple_get");
        String expectedPath = "/simple_get";
        String actualPath = testRoute.getPath();
        Assert.assertEquals(expectedPath, actualPath);
    }

}