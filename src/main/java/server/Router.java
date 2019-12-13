package server;

import java.util.ArrayList;

public class Router {

    ArrayList<Route> routes;

    public Router(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public Response route(Request request) {
        String requestPath = request.getPath();
        Method requestMethod = request.getMethod();

        for (Route route :
                this.routes) {
            if (requestMethod.equals(Method.OPTIONS) && route.getPath().equals(requestPath)) {
                return buildOptionsResponse(request);
            } else if (requestMethod.equals(Method.HEAD) && route.getPath().equals(requestPath)) {
                return buildHeadResponse(request);
            } else if (route.getMethod().equals(requestMethod) && route.getPath().equals(requestPath)) {
                return route.getRequestHandler().handle(request);
            }
        }

        Response response = new Response(StatusCode.NOT_FOUND);
        return response;
    }

    private Response buildOptionsResponse(Request request) {
        Response response;
        ArrayList<String> allowedMethods = new ArrayList<String>();
        for (Route route : this.routes) {
            if (route.getPath().equals(request.getPath())) {
                allowedMethods.add(route.getMethod().name());
            }
        }
        if (allowedMethods.isEmpty()) {
            return new Response(StatusCode.NOT_FOUND);
        }
        allowedMethods.add(Method.HEAD.name());
        allowedMethods.add(Method.OPTIONS.name());
        String listString = String.join(", ", allowedMethods);
        String allowedResponseLine = "Allow: " + listString;
        response = new Response(StatusCode.OK, allowedResponseLine);
        return response;
    }

    private Response buildHeadResponse(Request request) {
        return new Response(StatusCode.OK);

    }
}
