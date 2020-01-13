package application.Handler;

import server.HTTPComponents.StatusCode;
import application.Config;
import server.Request;
import server.Response;
import server.ResponseBuilder;

public class PostRequestHandler implements RequestHandler {
    public Response handle(Request request) {
        write(request.getResourceIdentifier(), request.getBody());
        return new ResponseBuilder()
                .setStatusCode(StatusCode.OK)
                .setBody(request.getBody())
                .build();
    }

    private void write(String resourceIdentifier, byte[] content) {
        new FileResourceHandler(Config.rootResourcePath).write(resourceIdentifier, content);
    }

}
