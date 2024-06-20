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
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\youtube\\src\\main\\resources\\videos\\"+name+".mkv");

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
    public static void send_video(int video_id){
        String name = Integer.toString(video_id);
        String path = "D:\\youtube\\src\\main\\resources\\videos\\";
        path = path+name+".mkv";
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
                    send_video(2);
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
