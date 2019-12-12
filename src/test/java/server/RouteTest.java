package server;

import org.junit.Test;
import org.junit.Assert;

public class RouteTest {

    @Test
    public void getMethodReturnsAMethod() {
<<<<<<< HEAD
        Route testRoute = new Route(Method.GET, "/test_route", (request) -> {
            return new Response(StatusCode.OK);
        });
=======
        Route testRoute = new Route(Method.GET, "/simple_get");
>>>>>>> 99b7977... Implement naive OPTIONS method with hardcoding
        Method expectedMethod = Method.GET;
        Method actualMethod = testRoute.getMethod();
        Assert.assertEquals(expectedMethod, actualMethod);
    }

    @Test
    public void getPathReturnsAString() {
<<<<<<< HEAD
        Route testRoute = new Route(Method.GET, "/test_route", (request) -> {
            return new Response(StatusCode.OK);
        });
        String expectedPath = "/test_route";
=======
        Route testRoute = new Route(Method.GET, "/simple_get");
        String expectedPath = "/simple_get";
>>>>>>> 99b7977... Implement naive OPTIONS method with hardcoding
        String actualPath = testRoute.getPath();
        Assert.assertEquals(expectedPath, actualPath);
    }

}