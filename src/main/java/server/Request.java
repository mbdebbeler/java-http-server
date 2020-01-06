package server;

import HTTPComponents.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static HTTPComponents.StatusLineComponents.CRLF;

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

    public HashMap<String, String> getHeaders() {
        String headers = message.split(CRLF)[0];
        return getHeaderKeyValuePairs(splitRequest(headers));
    }

    public byte[] getBody() {
        try {
            String[] splitMessage = message.split(CRLF + CRLF, 2);
            byte[] body = splitMessage[1].trim().getBytes();
            return body;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "".getBytes();
    }

    public ArrayList<String> splitRequest(String incomingRequest) {
        String[] requestHeaders = incomingRequest.split("\n");
        ArrayList<String> headers = new ArrayList<String>(Arrays.asList(requestHeaders));
        headers.remove(0);
        return headers;
    }

    public HashMap<String, String> getHeaderKeyValuePairs(ArrayList<String> headers) {
        HashMap<String, String> splitHeaders = new HashMap<String, String>();
        for (String header : headers) {
            splitHeaders.put(header.split(": ")[0], header.split(": ")[1]);
        }
        return splitHeaders;
    }

}
