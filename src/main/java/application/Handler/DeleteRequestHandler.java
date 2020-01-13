package application.Handler;

import server.HTTPComponents.StatusCode;
import application.Config;
import server.*;

public class DeleteRequestHandler implements RequestHandler {

    public Response handle(Request request) {
        if (delete(request.getResourceIdentifier())) {
            return new ResponseBuilder()
                    .setStatusCode(StatusCode.NO_CONTENT)
                    .build();
        }
        return new ResponseBuilder()
                .setStatusCode(StatusCode.NOT_ALLOWED)
                .build();
    }

    private boolean delete(String resourceIdentifier) {
        return new FileResourceHandler(Config.rootResourcePath).delete(resourceIdentifier);
    }

}