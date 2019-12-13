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
            default:
                return new Response(StatusCode.BAD_REQUEST);
        }
    }


}
