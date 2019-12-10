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
    private ServerLogger serverLogger = new ServerLogger();

    public SocketWrapper(Socket socket) throws IOException {
        Socket clientSocket = socket;
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        output = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public String receive() {
        try {
            return input.readLine();
        } catch (IOException e) {
            String inputError = "[-] Input not received.";
            System.err.println(inputError);
            serverLogger.logSomething(FINE, e.getMessage());
        }
        return null;
    }

    public void send(String data) {
        output.println(data);
    }

    public void close() {
        try {
            output.close();
            input.close();
        } catch (IOException e) {
            String shutdownError = "[-] Shutdown error.";
            System.err.println(shutdownError);
            serverLogger.logSomething(FINE, e.getMessage());
        }
    }
}
