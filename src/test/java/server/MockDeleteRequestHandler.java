package server;

import HTTPComponents.StatusCode;

public class MockDeleteRequestHandler implements RequestHandler {

    public Response handle(Request request) {
        return new ResponseBuilder().setStatusCode(StatusCode.NO_CONTENT).build();
    }
}
