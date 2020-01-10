package server;

import server.HTTPComponents.Method;
import application.Handler.RequestHandler;

public class Route {

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
