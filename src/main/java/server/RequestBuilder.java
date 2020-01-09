package server;

import HTTPComponents.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static HTTPComponents.StatusLineComponents.CRLF;
import static HTTPComponents.StatusLineComponents.VERSION;

public class RequestBuilder {
    private String message;
    public HTTPComponents.Method method;
    public String path;
    public String version;
    public byte[] body;
    public HashMap<String, String> headers;
    public String resourceIdentifier;

    public RequestBuilder(String message) {
        this.message = message;
    }

    public Request build() {
        setMethod();
        setPath();
        setResourceIdentifier();
        setVersion();
        setHeaders();
        setBody();
        return new Request(this);
    }

    private void setMethod() {
        String splitMethod = message.split(" ")[0];
        try {
            this.method = Method.valueOf(splitMethod);
        } catch (Exception e) {
            this.method = Method.INVALID;
        }
    }

    private void setPath() {
        String fullPath = message.split(" ")[1];
         this.path = fullPath.split("(?=/)")[0];
    }


    public void setVersion() {
        if (message.split(" ").length <=2) {
            this.version = message.split(" ")[2];
        } else {
            this.version = VERSION;
        }
    }

    public void setHeaders() {
        String headers = message.split(CRLF)[0];
        this.headers = getHeaderKeyValuePairs(splitRequest(headers));
    }

    public void setBody() {
        String[] splitMessage = message.split(CRLF + CRLF, 2);
        if (splitMessage.length == 1) {
            this.body = "".getBytes();
        } else {
            this.body = splitMessage[1].getBytes();
        }
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

    public void setResourceIdentifier() {
        String[] splitPath = message.split(" ")[1].split("(?=/)");
        if (splitPath.length == 1) {
            this.resourceIdentifier = "index.html";
        } else {
            this.resourceIdentifier = message.split(" ")[1].split("(?=/)")[1].split("/")[1];
        }
    }

}
