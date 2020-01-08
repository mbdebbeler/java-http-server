package server;

import HTTPComponents.StatusCode;

public class GetResourceHandler implements RequestHandler {

    public Response handle(Request request) {
        if (request.getResourceIdentifier() != "index.html") {
            return new ResponseBuilder()
                    .setStatusCode(StatusCode.OK)
                    .addHeader("Content-Type", "image/jpg")
                    .setBody(fetchResource(request.getResourceIdentifier()))
                    .build();
        } else {
            return new ResponseBuilder()
                    .setStatusCode(StatusCode.OK)
                    .addHeader("Content-Type", "text/html")
                    .setBody(fetchDirectory())
                    .build();
        }
    }

    public byte[] fetchResource(String resourceIdentifier) {
        return new FileResourceHandler().read(resourceIdentifier);
    }

    public byte[] fetchDirectory() {
        return new FileResourceHandler().getDelimitedContentsOfDirectory().getBytes();
    }
}
