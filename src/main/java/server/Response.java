package server;

public class Response {
    private StatusCode statusCode;

    public Response(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public String getStatusLine() {
        return "HTTP/1.1" + " " + statusCode.getValueAsString() + " " + statusCode.getReason() + "\r\n";
    }
}
