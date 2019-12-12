package server;

public class Route {

    Method method;
    String path;

    public Route(Method method, String path) {
        this.method = method;
        this.path = path;
    }

    public Method getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }


}
