package server;

import HTTPComponents.StatusCode;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

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

    public ResponseBuilder addBody(String body) {
        this.response.body = body;
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
        this.response.body = data;
        return this;
    }


    public ResponseBuilder addImageBodyFromFile(String filename) {
        String encodedFile;
        try {
            URL fileLocation = Server.class.getResource(filename);
            File fileToRead = new File(fileLocation.getPath());
            encodedFile = encodeFileToBase64Binary(fileToRead);

        } catch (Exception e) {
            e.printStackTrace();
            return this;
        }
        this.response.body = encodedFile;
        return this;
    }


    private static String encodeFileToBase64Binary(File file){
        String encodedFile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedFile = Base64.getEncoder().encodeToString(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedFile;
    }

    public ResponseBuilder addHeaders(String string) {
        this.response.headers = string;
        return this;
    }

    public ResponseBuilder addRedirect(String redirectedLocation) {
        addHeaders("Location: " + redirectedLocation);
        return this;
    }

}
