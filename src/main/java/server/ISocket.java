package server;

public interface ISocket {
    String receive();
    void send(byte[] data);
    void close();
}
