package server;

public class HeadRequestHandler implements RequestHandler {

    @Override
    public Response handle(Request request) {
        return new ResponseBuilder().build();
    }
}
