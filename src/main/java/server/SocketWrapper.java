package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.util.logging.Level.FINE;

public class SocketWrapper implements ISocket {
    private BufferedReader input;
    private PrintWriter output;

    public SocketWrapper(Socket socket) {
        try {
            this.input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            this.output = new PrintWriter(socket.getOutputStream(), true);
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

    public void send(String data) {
        output.print(data);
    }

    public void close() {
        try {
            output.close();
            input.close();
        } catch (IOException e) {
            String shutdownError = "[-] Shutdown error.";
            System.err.println(shutdownError);
        }
    }
}
