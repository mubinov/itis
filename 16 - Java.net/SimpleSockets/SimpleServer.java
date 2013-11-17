package SimpleSockets;

import java.net.*;
import java.io.*;

public class SimpleServer {

    public static void main(String args[]) throws IOException {

        // Register service on port 1254
        ServerSocket serverSocket = new ServerSocket(1254);
        Socket socket = serverSocket.accept(); // Wait and accept a connection

        // Get a communication stream associated with the socket
        OutputStream socketOut = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream (socketOut);

        // Send a string!
        dos.writeUTF("Hi there");

        // Close the connection, but not the server socket
        dos.close();
        socketOut.close();
        socket.close();
    }
}