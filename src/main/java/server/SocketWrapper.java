package server;

import java.io.*;
import java.net.Socket;

import static java.util.logging.Level.FINE;

public class SocketWrapper implements ISocket {
    private BufferedReader input;
    private DataOutputStream output;

    public SocketWrapper(Socket socket) {
        try {
            this.input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            this.output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receive() {
        char[] dataBuffer = new char[100];
        try {
            String incomingData = "";
            while(input.ready()) {
                int value = input.read(dataBuffer);
                incomingData += new String(dataBuffer,0, value);
            }
            return incomingData;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public void send(byte[] data) {
        try {
            output.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            output.flush();
            output.close();
            input.close();
        } catch (IOException e) {
            String shutdownError = "[-] Shutdown error.";
            System.err.println(shutdownError);
        }
    }
}
