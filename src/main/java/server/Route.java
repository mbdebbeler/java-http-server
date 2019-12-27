package server;

import HTTPComponents.Method;

public class Route {

    interface RequestHandler {
        Response handle(Request request);
    }

    private Method method;
    private String path;
    private RequestHandler requestHandler;

    public Route(Method method, String path, RequestHandler requestHandler) {
        this.method = method;
        this.path = path;
        this.requestHandler = requestHandler;
    }

    public Method getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public RequestHandler getRequestHandler() {
        return requestHandler;
    }
}
