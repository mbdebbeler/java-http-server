package server;

import HTTPComponents.StatusCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetResourceHandler implements RequestHandler {

    public Response handle(Request request) {
        if (fetchResource(request.getResourceIdentifier()) != "".getBytes()) {
            return new ResponseBuilder()
                    .setStatusCode(StatusCode.OK)
                    .addHeader("Content-Type", "image/jpg")
                    .setBody(fetchResource(request.getResourceIdentifier()))
                    .build();
        } else {
            return new ResponseBuilder()
                    .setStatusCode(StatusCode.NOT_FOUND)
                    .build();
        }
    }

    public byte[] fetchResource(String resourceIdentifier) {
        return new FileResourceHandler().read(resourceIdentifier);
    }
}

