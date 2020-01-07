package server;

import HTTPComponents.StatusCode;

public class DefaultRequestHandler implements RequestHandler {
    public Response handle(Request request) {
        return new ResponseBuilder()
                .setStatusCode(StatusCode.OK)
                .build();
    }
}
