package server;

import HTTPComponents.StatusCode;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

public class ResponseBuilder {
    public StatusCode statusCode;
    public byte[] body;
    public HashMap<String, String> headers = new HashMap<>();

    public Response build() {
        return new Response(this);
    }

    public ResponseBuilder addStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ResponseBuilder addBody(byte[] body) {
        this.body = body;
        return this;
    }

    public ResponseBuilder addHeader(String header, String value) {
        this.headers.put(header, value);
        return this;
    }

    public ResponseBuilder addTextBodyFromFile(String filename) {
        String data = null;
        try {
            URL fileLocation = Server.class.getResource(filename);
            File myObj = new File(fileLocation.getPath());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.body = data.getBytes();
        return this;
    }


    public ResponseBuilder addImageBodyFromFile(String filename) {
        byte[] fileContents;
        try {
            URL fileLocation = Server.class.getResource(filename);
            fileContents = Files.readAllBytes(Paths.get(fileLocation.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
            return this;
        }
        this.body = fileContents;
        return this;
    }

    public ResponseBuilder addRedirect(String redirectedLocation) {
        this.addHeader("Location", redirectedLocation);
        return this;
    }

}
