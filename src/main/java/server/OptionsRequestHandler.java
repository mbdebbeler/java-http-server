package server;

import HTTPComponents.Method;
import HTTPComponents.StatusCode;

import java.util.ArrayList;

public class OptionsRequestHandler implements RequestHandler {
    private ArrayList<Route> routes;
    private String key;
    private String value;

    public OptionsRequestHandler(ArrayList<Route> routes){
        this.routes = routes;
    };

    public Response handle(Request request) {
        key = "Allow";
        value = encodeAsString(buildAllowedMethods(request));
        return new ResponseBuilder().addHeader(key, value).setStatusCode(StatusCode.OK).build();
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

    private String encodeAsString(ArrayList<String> allowedMethods) {
        return String.join(", ", allowedMethods);
    }
}
