package server;

import server.HTTPComponents.Method;

import java.util.HashMap;

public class Request {
    private Method method;
    private String path;
    private HashMap<String, String> headers;
    private String version;
    private byte[] body;
    private String resourceIdentifier;

    public Request(RequestBuilder requestBuilder) {
        this.method = requestBuilder.method;
        this.path = requestBuilder.path;
        this.version = requestBuilder.version;
        this.headers = requestBuilder.headers;
        this.body = requestBuilder.body;
        this.resourceIdentifier = requestBuilder.resourceIdentifier;
    }

    public Method getMethod() {
        return this.method;
    }

    public String getPath() {
        return this.path;
    }

    public String getVersion() {
        return this.version;
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public byte[] getBody() {
        return this.body;
    }
    public String getResourceIdentifier() {
        return this.resourceIdentifier;
    }

}
