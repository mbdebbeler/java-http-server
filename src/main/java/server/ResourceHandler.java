package server;

public interface ResourceHandler {
    void write(String filename, byte[] content);

    boolean delete(String filename);

    byte[] read(String filename);

    String[] directoryContent();
}
