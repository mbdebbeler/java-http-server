package server;

import server.HTTPComponents.StatusCode;
import application.Handler.RequestHandler;

public class MockDeleteRequestHandler implements RequestHandler {

    public Response handle(Request request) {
        return new ResponseBuilder().setStatusCode(StatusCode.NO_CONTENT).build();
    }
}
