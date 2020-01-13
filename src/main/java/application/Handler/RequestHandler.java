package application.Handler;

import server.Request;
import server.Response;

public interface RequestHandler {
    Response handle(Request request);
}
