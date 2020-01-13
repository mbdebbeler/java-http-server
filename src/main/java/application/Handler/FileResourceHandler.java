package application.Handler;

import application.Config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static server.HTTPComponents.StatusLineComponents.CRLF;

public class FileResourceHandler implements ResourceHandler {
    private String rootFilePath;

    public FileResourceHandler(String rootFilePath) {
        this.rootFilePath = rootFilePath;
    }

    public void write(String resourceIdentifier, byte[] content) {
        try {
            writeContentToFile(resourceIdentifier, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(String resourceIdentifier) {
        File resource = new File(Config.rootResourcePath + resourceIdentifier);
        return resource.delete();
    }

    public byte[] read(String resourceIdentifier) {
        Path fullPath = Paths.get(String.valueOf(this.getClass().getResource(resourceIdentifier)));
        if (Files.exists(fullPath)) {
            try {
                return Files.readAllBytes(fullPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return noResourceContentAvailable();
    }

    private byte[] noResourceContentAvailable() {
        return new byte[0];
    }

    public String directoryContent() {
        File rootDirectory = new File(this.rootFilePath);
        String[] files = rootDirectory.list();
        Arrays.sort(files);
        return getDelimitedContentsOfDirectory(files);
    }

    private void writeContentToFile(String resourceIdentifier, byte[] content) throws IOException {
        File resource = new File(Config.rootResourcePath + resourceIdentifier);
        FileOutputStream fileOutputStream = new FileOutputStream(resource, true);
        fileOutputStream.write(content);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private String getDelimitedContentsOfDirectory(String[] filenames) {
        return delimitedValues(Arrays.asList(transformToLinks(filenames)), CRLF);
    }

    private String[] transformToLinks(String[] filenames) {
        return asLinks(filenames).toArray(new String[filenames.length]);
    }

    private List<String> asLinks(String[] filenames) {
        List<String> links = new ArrayList<>();
        for (String filename : filenames) {
            links.add(String.format("<li><a href=/images/%s>%s</a></li>", filename, filename));
        }
        wrapInUnorderedList(links);
        links.add(0, makeDirectoryHeader());
        return links;
    }

    private String delimitedValues(List<String> values, String delimiter) {
        String delimiterSeparatedValues = "";
        String firstValue;
        if (values.size() > 0) {
            firstValue = values.get(0);
            for (int i = 1; i < values.size(); i++) {
                delimiterSeparatedValues += delimiter + values.get(i);
            }
            return firstValue + delimiterSeparatedValues;
        }
        return delimiterSeparatedValues;
    }

    private String makeDirectoryHeader() {
        return new String("<h1>Available Images:</h1>");
    }

    private List<String> wrapInUnorderedList(List<String> links) {
        links.add(0, "<ul>");
        links.add(links.size(), "</ul>");
        return links;
    }

}
