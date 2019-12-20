package server;

import HTTPComponents.StatusCode;

import java.util.ArrayList;

public class ResponseBuilder {

    private Response response;

    public ResponseBuilder() {
        response = new Response(StatusCode.OK);
    }

    public Response build() {
        return response;
    }

    public ResponseBuilder addStatusCode(StatusCode statusCode) {
        this.response.statusCode = statusCode;
        return this;
    }

    public ResponseBuilder addAllowedMethods(ArrayList<String> allowedMethods) {
        this.response.allowedMethods = allowedMethods;
        return this;
    }

}
