package server;

public class ResponseBuilder {

    private Response response;

    public ResponseBuilder() {
        response = new Response(StatusCode.OK);
    }

    public Response build() {
        return response;
    }

    public ResponseBuilder AddStatusCode(StatusCode statusCode) {
        this.response.statusCode = statusCode;
        return this;
    }

//    We're going to want a hash map for headers - needs to have a default empty one, and then be able to add KV pairs
//    We're also going to need to add a body, and we need body-length, and a content-type - we're going to set the matching headers for all those things

}
