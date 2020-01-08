package server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MockResourceHandler implements ResourceHandler {
    public void write(String resourceIdentifier, byte[] content) {
        try {
            writeContentToFile(resourceIdentifier, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(String filename) {
        return true;
    }

    public byte[] read(String filename) {
        Path fullPath = Paths.get(new File("src/test/resources/post-test.jpeg").getAbsolutePath());
        try {
            return Files.readAllBytes(fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "".getBytes();
    }

    public String[] directoryContent() {
        return new String[0];
    }

    private void writeContentToFile(String resourceIdentifier, byte[] content) throws IOException {
        File resource = new File("src/test/resources/" + resourceIdentifier);
        FileOutputStream fileOutputStream = new FileOutputStream(resource, true);
        fileOutputStream.write(content);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
