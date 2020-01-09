package server;

import HTTPComponents.Method;

import java.util.ArrayList;

public class SpinachTestsRouteFactory implements RouteFactory {

    public ArrayList<Route> makeRoutes() {
        return new ArrayList<Route>() {{
            add(new Route(Method.GET, "/simple_get", new DefaultRequestHandler()));
            add(new Route(Method.HEAD, "/get_with_body", new DefaultRequestHandler()));
            add(new Route(Method.GET, "/method_options",  new DefaultRequestHandler()));
            add(new Route(Method.GET, "/method_options2",  new DefaultRequestHandler()));
            add(new Route(Method.PUT, "/method_options2",  new DefaultRequestHandler()));
            add(new Route(Method.POST, "/method_options2",  new DefaultRequestHandler()));
            add(new Route(Method.POST, "/echo_body", new PostRequestHandler()));
            add(new Route(Method.GET, "/redirect", new RedirectRequestHandler()));
            add(new Route(Method.GET, "/images", new GetResourceHandler()));
            add(new Route(Method.DELETE, "/images", new DeleteRequestHandler()));
        }};
    }
}
