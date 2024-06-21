package program.youtube;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4042); // Port number

            System.out.println("Server started. Listening for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept incoming connection
                System.out.println("Client connected: ");

                // Create a new thread to handle the client
                Thread clientThread = new Thread(new clientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
