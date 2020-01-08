package server;

import HTTPComponents.StatusCode;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetResourceHandler implements RequestHandler {

    public Response handle(Request request) {
        if (Server.class.getResource("src/main/resources" + request.getResourceIdentifier()) != null) {
            return new ResponseBuilder()
                    .setStatusCode(StatusCode.OK)
                    .addHeader("Content-Type", "image/jpg")
                    .setBody(fetchResource(request.getResourceIdentifier()))
                    .build();
        } else {
            return new ResponseBuilder()
                    .setStatusCode(StatusCode.NO_CONTENT)
                    .build();
        }
    }

    public byte[] fetchResource(String resourceIdentifier) {
        byte[] fileContents;
        try {
            URL fileLocation = Server.class.getResource("src/main/resources/" + resourceIdentifier);
            fileContents = Files.readAllBytes(Paths.get(fileLocation.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return fileContents;
    }
}

