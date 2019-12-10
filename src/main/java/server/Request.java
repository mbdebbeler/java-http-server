package server;

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

}
