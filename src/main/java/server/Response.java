package server;

import server.HTTPComponents.StatusCode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static server.HTTPComponents.StatusLineComponents.*;

public class Response {
    private StatusCode statusCode;
    private byte[] body;
    private HashMap<String, String> headers;

    public Response(ResponseBuilder responseBuilder) {
        this.statusCode = responseBuilder.statusCode;
        this.body = responseBuilder.body;
        this.headers = responseBuilder.headers;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public byte[] getStatusLine() {
        return (VERSION + SPACE + statusCode.getValueAsString() + SPACE + statusCode.getReason() + CRLF).getBytes();
    }

    private byte[] getHeaders() {
        return (formatHeaders(this.headers) + CRLF).getBytes();
    }

    private String formatHeaders(HashMap<String, String> headers) {
        Set<Map.Entry<String, String>> headersSet = headers.entrySet();
        String formattedHeader = "";
        for (Map.Entry entry : headersSet) {
            Object header = entry.getKey();
            Object value = entry.getValue();
            formattedHeader += header.toString() + ": " + value.toString() + CRLF;
        }
        return formattedHeader;
    }

    public byte[] getBody() {
        return this.body == null ? "".getBytes() : this.body;
    }

    public byte[] getResponseBytes() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write(this.getStatusLine());
            outputStream.write(this.getHeaders() == null ? "".getBytes() : this.getHeaders());
            outputStream.write(this.getBody() == null ? "".getBytes() : this.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

}
