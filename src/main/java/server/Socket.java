package server;

public interface Socket {
    String receive();
    void send(byte[] data);
    void close();
}
