package server;

import HTTPComponents.StatusCode;

public class HeadRequestHandler implements RequestHandler {

    @Override
    public Response handle(Request request) {
        return new ResponseBuilder().setStatusCode(StatusCode.OK).build();
    }
}
