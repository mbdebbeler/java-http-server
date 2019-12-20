package server;

import HTTPComponents.Method;

import java.util.ArrayList;

public class RouteFactory implements IRouteFactory {

    public ArrayList<Route> makeRoutes() {
        return new ArrayList<Route>() {{
            add(new Route(Method.GET, "/simple_get", (request) -> {
                return new ResponseBuilder().build();
            }));
            add(new Route(Method.HEAD, "/get_with_body", (request) -> {
                return new ResponseBuilder().build();
            }));
            add(new Route(Method.GET, "/method_options", (request) -> {
                return new ResponseBuilder().build();
            }));
            add(new Route(Method.GET, "/method_options2", (request) -> {
                return new ResponseBuilder().build();
            }));
            add(new Route(Method.PUT, "/method_options2", (request) -> {
                return new ResponseBuilder().build();
            }));
            add(new Route(Method.POST, "/method_options2", (request) -> {
                return new ResponseBuilder().build();
            }));
            add(new Route(Method.POST, "/echo_body", (request) -> {
                return new ResponseBuilder().build();
            }));
        }};
    }
}
