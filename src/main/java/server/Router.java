package server;

import java.util.ArrayList;

public class Router {

    Route route;

    public Router(ArrayList<Route> routes) {
    }

    public Response route(Request request) {
        String requestPath = request.getPath();
        Method requestMethod = request.getMethod();

        if (requestMethod.equals(Method.OPTIONS)) {
            ResponseBuilder builder = new ResponseBuilder(request);
            Response response = builder.buildResponse();
            return response;
        } else if (route.getMethod().equals(requestMethod) && route.getPath().equals(requestPath)) {
            ResponseBuilder builder = new ResponseBuilder(request);
            Response response = builder.buildResponse();
            return response;
        } else {
            Response response = new Response(StatusCode.NOT_FOUND);
            return response;
        }


    }
}
