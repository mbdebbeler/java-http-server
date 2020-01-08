package server;

import HTTPComponents.StatusCode;

public class PostRequestHandler implements RequestHandler {
    public Response handle(Request request) {
        write(request.getResourceIdentifier(), request.getBody());
        return new ResponseBuilder()
                .setStatusCode(StatusCode.OK)
                .setBody(request.getBody())
                .build();
    }

    private void write(String resourceIdentifier, byte[] content) {
        new FileResourceHandler().write(resourceIdentifier, content);
    }

}
