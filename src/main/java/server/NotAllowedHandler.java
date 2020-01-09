package server;

import HTTPComponents.Method;
import HTTPComponents.StatusCode;

import java.util.ArrayList;

public class NotAllowedHandler implements RequestHandler {
    ArrayList<Route> routes;

    public NotAllowedHandler(ArrayList<Route> routes){
        this.routes = routes;
    };

    public Response handle(Request request) {
        ArrayList<String> allowedMethods = buildAllowedMethods(request);
        String allowedMethodsAsString = String.join(", ", allowedMethods);
        return new ResponseBuilder().addHeader("Allow", allowedMethodsAsString).setStatusCode(StatusCode.NOT_ALLOWED).build();
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

}
