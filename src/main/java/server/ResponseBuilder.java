package server;

public class ResponseBuilder {
    private Request request;

    public ResponseBuilder(Request request) {
        this.request = request;
    }

    public Response buildResponse() {
        Method requestMethod = request.getMethod();
        switch (requestMethod) {
            case GET:
            case HEAD:
                return new Response(StatusCode.OK);
            case OPTIONS:
                return new Response(StatusCode.OK, "Allow: OPTIONS, GET, HEAD");
            default:
                return new Response(StatusCode.BAD_REQUEST);
        }
    }


}
