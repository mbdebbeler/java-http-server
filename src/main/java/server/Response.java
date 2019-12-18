package server;

public class Response {
    public StatusCode statusCode;
    public String allowedMethods;

    public Response(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public Response(StatusCode statusCode, String allowedMethods) {
        this.statusCode = statusCode;
        this.allowedMethods = allowedMethods;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public String getStatusLine() {
        return "HTTP/1.1" + " " + statusCode.getValueAsString() + " " + statusCode.getReason() + "\r\n";
    }

    public String getAllowedMethods() {
        return this.allowedMethods != null ? "\n" + this.allowedMethods : "";
    }

    public String getAllPartsOfResponseAsString() {
        return "HTTP/1.1" + " " + statusCode.getValueAsString() + " " + statusCode.getReason() + getAllowedMethods() + "\r\n";
    }
}
