package server;

import HTTPComponents.Method;

public class Request {
    private String message;

    public Request(String message) {
        this.message = message;
    }

    public Method getMethod() {
        String method = message.split(" ")[0];
        try {
            return Method.valueOf(method);
        } catch (Exception e) {
            return Method.INVALID;
        }
    }

    public String getPath() {
        return message.split(" ")[1];
    }

    public String getVersion() {
        return message.split(" ")[2];
    }

    public String getBody() {
        return message.split(" ")[2];
    }

}
