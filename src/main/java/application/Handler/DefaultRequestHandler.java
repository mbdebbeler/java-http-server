package application.Handler;

import server.HTTPComponents.StatusCode;
import server.Request;
import server.Response;
import server.ResponseBuilder;

public class DefaultRequestHandler implements RequestHandler {
    public Response handle(Request request) {
        return new ResponseBuilder()
                .setStatusCode(StatusCode.OK)
                .build();
    }
}
