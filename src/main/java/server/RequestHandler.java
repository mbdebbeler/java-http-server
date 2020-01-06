package server;

public interface RequestHandler {
    Response handle(Request request);
}
