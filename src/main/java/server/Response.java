package server;

import HTTPComponents.StatusCode;
import HTTPComponents.StatusLineComponents;

import java.util.ArrayList;

import static HTTPComponents.StatusLineComponents.*;

public class Response {
    public StatusCode statusCode;
    public ArrayList<String> allowedMethods;
    public String body;
    public String headers;

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
        this.headers = "";
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public String getStatusLine() {
        return VERSION + SPACE + statusCode.getValueAsString() + SPACE + statusCode.getReason() + CRLF;
    }

    public String getAllowedMethods() {
        return this.allowedMethods != null ? getAllowedMethodsAsFormattedString(this.allowedMethods) : "";
    }

    public String getBody() {
        return this.body != null ? CRLF + this.body : "";
    }

    public String getHeaders() {
        if (this.body == null && this.headers == null) {
            return "";
        } else if (this.body != null) {
            String length = String.valueOf(this.body.length());
            this.headers = "Content-Length: " + length + NEWLINE;
            this.headers += "Content-Type: text/html";
        }
        return NEWLINE + this.headers;
    }

    public String getEntireResponse() {
        return VERSION +
                SPACE +
                statusCode.getValueAsString() +
                SPACE +
                statusCode.getReason() +
                getAllowedMethods() +
                getHeaders() +
                CRLF +
                getBody();
    }

    private String getAllowedMethodsAsFormattedString(ArrayList<String> allowedMethods) {
        String listString = String.join(", ", allowedMethods);
        String allowedMethodsResponseLine = NEWLINE + "Allow: " + listString;
        return allowedMethodsResponseLine;
    }
}
