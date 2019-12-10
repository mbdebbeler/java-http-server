package server;

public class RequestHandler {
    private Request request;

    public RequestHandler(Request request) {
        this.request = request;
    }

    public Response buildResponse() {
        Method requestMethod = request.getMethod();
        switch (requestMethod) {
            case GET:
                return new Response(StatusCode.OK);
            case HEAD:
                return new Response(StatusCode.OK);
            default:
                return new Response(StatusCode.BAD_REQUEST);
        }
    }


}
