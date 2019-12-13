package server;

import org.junit.Test;
import org.junit.Assert;

public class RouteTest {

    @Test
    public void getMethodReturnsAMethod() {
<<<<<<< HEAD
<<<<<<< HEAD
        Route testRoute = new Route(Method.GET, "/test_route", (request) -> {
            return new Response(StatusCode.OK);
        });
=======
        Route testRoute = new Route(Method.GET, "/simple_get");
>>>>>>> 99b7977... Implement naive OPTIONS method with hardcoding
=======
        Route testRoute = new Route(Method.GET, "/test_route", (request) -> {
            return new Response(StatusCode.OK);
        });
>>>>>>> 7b3769f... Move request handling logic inside route, add routes
        Method expectedMethod = Method.GET;
        Method actualMethod = testRoute.getMethod();
        Assert.assertEquals(expectedMethod, actualMethod);
    }

    @Test
    public void getPathReturnsAString() {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 7b3769f... Move request handling logic inside route, add routes
        Route testRoute = new Route(Method.GET, "/test_route", (request) -> {
            return new Response(StatusCode.OK);
        });
        String expectedPath = "/test_route";
<<<<<<< HEAD
=======
        Route testRoute = new Route(Method.GET, "/simple_get");
        String expectedPath = "/simple_get";
>>>>>>> 99b7977... Implement naive OPTIONS method with hardcoding
=======
>>>>>>> 7b3769f... Move request handling logic inside route, add routes
        String actualPath = testRoute.getPath();
        Assert.assertEquals(expectedPath, actualPath);
    }

}