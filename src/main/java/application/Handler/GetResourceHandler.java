package application.Handler;

import server.HTTPComponents.StatusCode;
import application.Config;
import server.Request;
import server.Response;
import server.ResponseBuilder;
import server.Server;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetResourceHandler implements RequestHandler {

    public Response handle(Request request) {
        if (request.getResourceIdentifier() == "index.html") {
            return new ResponseBuilder()
                    .setStatusCode(StatusCode.OK)
                    .addHeader("Content-Type", "text/html")
                    .setBody(fetchDirectory())
                    .build();
        }
        if (Files.notExists(Paths.get(Config.rootResourcePath + request.getResourceIdentifier()))) {
            return new ResponseBuilder()
                    .setStatusCode(StatusCode.NO_CONTENT)
                    .build();
        }
        return new ResponseBuilder()
                .setStatusCode(StatusCode.OK)
                .addHeader("Content-Type", "image/jpg")
                .setBody(fetchResource(request.getResourceIdentifier()))
                .build();
    }

    public byte[] fetchResource(String resourceIdentifier) {
        return new FileResourceHandler(Config.rootResourcePath).read(resourceIdentifier);
    }

    public byte[] fetchDirectory() {
        return new FileResourceHandler(Config.rootResourcePath).directoryContent().getBytes();
    }


}

