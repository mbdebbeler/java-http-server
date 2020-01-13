package application;

import server.HTTPComponents.Method;
import server.HTTPComponents.StatusCode;
import application.Handler.DefaultRequestHandler;
import application.Handler.NotAllowedHandler;
import application.Handler.OptionsRequestHandler;
import server.Request;
import server.Response;
import server.ResponseBuilder;
import server.Route;

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
                return new OptionsRequestHandler(this.routes).handle(request);
            } else if (requestMethod.equals(Method.HEAD) && route.getPath().equals(requestPath)) {
                return new DefaultRequestHandler().handle(request);
            } else if (route.getMethod().equals(requestMethod) && route.getPath().equals(requestPath)) {
                return route.getRequestHandler().handle(request);
            }
        }

        for (Route route :
                this.routes) {
            if (route.getPath().equals(requestPath)) {
                return new NotAllowedHandler(this.routes).handle(request);
            }
        }

        return new ResponseBuilder().setStatusCode(StatusCode.NOT_FOUND).build();
    }

}
