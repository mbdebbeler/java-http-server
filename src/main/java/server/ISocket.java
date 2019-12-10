package server;

public interface ISocket {
    String receive();
    void send(String data);
    void close();
}
