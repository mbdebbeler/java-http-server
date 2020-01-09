package server;

import HTTPComponents.Method;
import HTTPComponents.StatusCode;

import java.util.ArrayList;

public class RouteFactory implements IRouteFactory {

    public ArrayList<Route> makeRoutes() {
        return new ArrayList<Route>() {{
            add(new Route(Method.GET, "/simple_get", new DefaultRequestHandler()));
            add(new Route(Method.HEAD, "/get_with_body", new DefaultRequestHandler()));
            add(new Route(Method.GET, "/method_options",  new DefaultRequestHandler()));
            add(new Route(Method.GET, "/method_options2",  new DefaultRequestHandler()));
            add(new Route(Method.PUT, "/method_options2",  new DefaultRequestHandler()));
            add(new Route(Method.POST, "/method_options2",  new DefaultRequestHandler()));
            add(new Route(Method.POST, "/echo_body", (request) -> {
                return new ResponseBuilder()
                        .setStatusCode(StatusCode.OK)
                        .setBody(request.getBody())
                        .build();
            }));
            add(new Route(Method.GET, "/redirect", (request) -> {
                return new ResponseBuilder()
                        .addRedirect("http://127.0.0.1:5000/simple_get")
                        .setStatusCode(StatusCode.MOVED_PERMANENTLY)
                        .build();
            }));
            add(new Route(Method.GET, "/echo_file_contents", (request) -> {
                return new ResponseBuilder()
                        .setTextBodyFromFile("../test.txt")
                        .setStatusCode(StatusCode.OK)
                        .build();
            }));
            add(new Route(Method.GET, "/echo_image_contents", (request) -> {
                return new ResponseBuilder()
                        .setImageBodyFromFile("../big-test.jpg")
                        .setStatusCode(StatusCode.OK)
                        .build();
            }));
            add(new Route(Method.GET, "/image_get", (request) -> {
                return new ResponseBuilder()
                        .addHeader("Content-Type", "image/jpg")
                        .setImageBodyFromFile("../big-test.jpg")
                        .setStatusCode(StatusCode.OK)
                        .build();
            }));
            add(new Route(Method.GET, "/images", new GetResourceHandler()));
            add(new Route(Method.DELETE, "/images", new DeleteRequestHandler()));
        }};
    }
}
