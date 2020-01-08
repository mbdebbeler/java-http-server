package server;

import HTTPComponents.StatusCode;

public class DeleteRequestHandler implements RequestHandler {

    public Response handle(Request request) {
        if (delete(request.getResourceIdentifier())) {
            return new ResponseBuilder()
                    .setStatusCode(StatusCode.NO_CONTENT)
                    .build();
        } else {
            return new ResponseBuilder()
                    .setStatusCode(StatusCode.NOT_ALLOWED)
                    .build();
        }
    }

    private boolean delete(String resourceIdentifier) {
        return new FileResourceHandler().delete(resourceIdentifier);
    }

}