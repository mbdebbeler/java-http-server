package server;

public class Router {

    public Router() {

    }

    public Response route(Request request) {
        String path = request.getPath();
        switch (path) {
            case "/simple_get":
            case "/get_with_body":
            case "/simple_head":
                RequestHandler handler = new RequestHandler(request);
                Response handledResponse = handler.buildResponse();
                return handledResponse;
            default:
                Response response = new Response(StatusCode.NOT_FOUND);
                return response;
        }

    }
}
