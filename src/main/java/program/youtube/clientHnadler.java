package program.youtube;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class clientHandler implements Runnable {
    private final Socket clientSocket;

    public clientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public static void get_file(Socket socket,String name){
        try {


            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int byteread;
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\final_project\\src\\main\\resources\\videos"+name+".mkv");

            while ((byteread = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer,0,byteread);
            }
            fileOutputStream.close();
            inputStream.close();






        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //there is a problem with getting video from server


    public static void server_video_sender(Socket clientSocket) throws IOException {
        FileInputStream videoFile = new FileInputStream("C:\\Users\\Sepanta\\Downloads\\@movieo_bot.Black.Bullet.E02.720p.BluRay.@movieo_bot.mkv");

        while (true) {
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            out.writeUTF("msg");

            OutputStream outputStream = clientSocket.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = videoFile.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            videoFile.close();
            clientSocket.close();
        }


    }


    @Override
    public void run() {
        try {
            // Get input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read client input
            String clientInput;

            while ((clientInput = in.readLine()) != null) {

                if(clientInput.equals("getfile")){
                    String name = in.readLine();
                    get_file(clientSocket,name);
                    out.println("get");
                }
                else if(clientInput.equals("sendfile")){
                    server_video_sender(clientSocket);
                    out.println("sent");
                }

                // Process client input (you can customize this part)
                //String response = "Hello, client!"; // Your custom response

                // Send response back to client
                //out.println(response);
            }

            // Clean up
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
