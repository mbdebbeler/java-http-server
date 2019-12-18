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

        for (Route route :
                this.routes) {
            if (route.getPath().equals(requestPath)) {
                return buildNotAllowedResponse(request);
            }
        }

        return new ResponseBuilder().addStatusCode(StatusCode.NOT_FOUND).build();
    }

    private Response buildOptionsResponse(Request request) {
        String allowedMethodsResponseLine = encodeAllowedMethodsToLine(buildAllowedMethods(request));
        return new ResponseBuilder().addAllowedMethods(allowedMethodsResponseLine).addStatusCode(StatusCode.OK).build();
    }

    private Response buildHeadResponse(Request request) {
        return new ResponseBuilder().build();
    }

    private Response buildNotAllowedResponse(Request request) {
        String allowedMethodsResponseLine = encodeAllowedMethodsToLine(buildAllowedMethods(request));
        return new ResponseBuilder().addAllowedMethods(allowedMethodsResponseLine).addStatusCode(StatusCode.NOT_ALLOWED).build();
    }

    private ArrayList<String> buildAllowedMethods(Request request) {
        ArrayList<String> allowedMethods = new ArrayList<String>();
        for (Route route : this.routes) {
            if (route.getPath().equals(request.getPath())) {
                allowedMethods.add(route.getMethod().name());
            }
        }
        if (!allowedMethods.contains(Method.HEAD.name())) {
            allowedMethods.add(Method.HEAD.name());
        }
        if (!allowedMethods.contains(Method.OPTIONS.name())) {
            allowedMethods.add(Method.OPTIONS.name());
        }
        return allowedMethods;
    }

    private String encodeAllowedMethodsToLine(ArrayList<String> allowedMethods) {
        String listString = String.join(", ", allowedMethods);
        String allowedMethodsResponseLine = "Allow: " + listString;
        return allowedMethodsResponseLine;
    }

}
