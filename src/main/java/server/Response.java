package server;

import java.util.ArrayList;

public class Response {
    public StatusCode statusCode;
    public ArrayList<String> allowedMethods;

    public Response(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public Response(StatusCode statusCode, ArrayList<String> allowedMethods) {
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
        return this.allowedMethods != null ? "\n" + getAllowedMethodsAsFormattedString(this.allowedMethods) : "";
    }

    public String getAllPartsOfResponseAsString() {
        return "HTTP/1.1" + " " + statusCode.getValueAsString() + " " + statusCode.getReason() + getAllowedMethods() + "\r\n";
    }

    private String getAllowedMethodsAsFormattedString(ArrayList<String> allowedMethods) {
        String listString = String.join(", ", allowedMethods);
        String allowedMethodsResponseLine = "Allow: " + listString;
        return allowedMethodsResponseLine;
    }
}
