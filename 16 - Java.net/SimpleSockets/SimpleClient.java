package SimpleSockets;

// SimpleClient.java: A simple client program.
import java.net.*;
import java.io.*;

public class SimpleClient {
    public static void main(String args[]) throws IOException {

        // Open your connection to a server, at port 1254
        Socket socket = new Socket("localhost", 1254);

        // Get an input file handle from the socket and read the input
        InputStream socketInput = socket.getInputStream();
        DataInputStream dis = new DataInputStream(socketInput);
        String str = new String (dis.readUTF());
        System.out.println(str);

        // When done, just close the connection and exit
        dis.close();
        socketInput.close();
        socket.close();
    }
}