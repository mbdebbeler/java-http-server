package server;

import HTTPComponents.StatusCode;
import HTTPComponents.StatusLineComponents;

import java.util.ArrayList;

import static HTTPComponents.StatusLineComponents.*;

public class Response {
    public StatusCode statusCode;
    public ArrayList<String> allowedMethods;
    public String body;

    public Response(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public Response(StatusCode statusCode, ArrayList<String> allowedMethods) {
        this.statusCode = statusCode;
        this.allowedMethods = allowedMethods;
    }

    public Response(StatusCode statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public String getStatusLine() {
        return VERSION + SPACE + statusCode.getValueAsString() + SPACE + statusCode.getReason() + CRLF;
    }

    public String getAllowedMethods() {
        return this.allowedMethods != null ? NEWLINE + getAllowedMethodsAsFormattedString(this.allowedMethods) : "";
    }

    public String getBody() {
        return this.body != null ? this.body : "";
    }

    public String getEntireResponse() {
        return VERSION + SPACE + statusCode.getValueAsString() + SPACE + statusCode.getReason() + getAllowedMethods() + CRLF + getBody();
    }

    private String getAllowedMethodsAsFormattedString(ArrayList<String> allowedMethods) {
        String listString = String.join(", ", allowedMethods);
        String allowedMethodsResponseLine = "Allow: " + listString;
        return allowedMethodsResponseLine;
    }
}
