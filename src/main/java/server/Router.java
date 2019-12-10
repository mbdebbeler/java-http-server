package server;

public class Router {

    public Router() {

    }

    public Response route(Request request) {
        Response response = new Response(StatusCode.NOT_FOUND);
        return response;
    }
}
