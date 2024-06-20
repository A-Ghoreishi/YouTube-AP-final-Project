package program.youtube;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {


//needs to be changed
    public static void send_video(String path,String name){

        try{

            File videfile =new File(path) ;
            byte[] videobytes = new byte[(int)videfile.length()];
            //FileInputStream fileInputStream = new FileInputStream(videfile);
            //fileInputStream.read(videobytes);
            //fileInputStream.close();
            Socket socket = new Socket("localhost",4042);


            OutputStream outputStream = socket.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(videfile));

            int count;
            byte[] buffer = new byte[8192]; // 8KB buffer
            while ((count = bis.read(buffer)) > 0) {
                outputStream.write(buffer, 0, count);
                outputStream.flush(); // Flush after each write
            }

            bis.close();
            outputStream.close();
            socket.close();



        }catch (Exception e){
            e.printStackTrace();
        }
    }








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
