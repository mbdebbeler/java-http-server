package application.Handler;

import server.HTTPComponents.StatusCode;
import server.Request;
import server.Response;
import server.ResponseBuilder;

import java.util.HashMap;

public class RedirectRequestHandler implements RequestHandler {
    public Response handle(Request request) {
        return new ResponseBuilder()
                .addRedirect("http://"+ getLocation(request) + "/simple_get")
                .setStatusCode(StatusCode.MOVED_PERMANENTLY)
                .build();
    }

    private String getLocation(Request request) {
        HashMap<String, String> headers = request.getHeaders();
        if (headers.containsKey("Host")) {
            return headers.get("Host");
        }
        return "127.0.0.1:5000";
    }
}
