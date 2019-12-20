package server;

import HTTPComponents.StatusCode;
import HTTPComponents.StatusLineComponents;

import java.util.ArrayList;

import static HTTPComponents.StatusLineComponents.*;

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
        return VERSION + SPACE + statusCode.getValueAsString() + SPACE + statusCode.getReason() + CRLF;
    }

    public String getAllowedMethods() {
        return this.allowedMethods != null ? NEWLINE + getAllowedMethodsAsFormattedString(this.allowedMethods) : "";
    }

    public String getAllPartsOfResponseAsString() {
        return VERSION + SPACE + statusCode.getValueAsString() + SPACE + statusCode.getReason() + getAllowedMethods() + CRLF;
    }

    private String getAllowedMethodsAsFormattedString(ArrayList<String> allowedMethods) {
        String listString = String.join(", ", allowedMethods);
        String allowedMethodsResponseLine = "Allow: " + listString;
        return allowedMethodsResponseLine;
    }
}
