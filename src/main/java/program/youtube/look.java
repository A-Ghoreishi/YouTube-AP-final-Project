package program.youtube;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;


public class look {
    public void get_thumbnail(Socket clientSocket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();

            // Parse the JSON data
            JSONObject json = new JSONObject(clientData);
            int video_id = json.getInt("video_id");

            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[8192];
            int bytesRead;

            // Read video_id from the client


            String name = Integer.toString(video_id);
            String path = "D:\\final_project\\src\\main\\resources\\server_thumbnail\\" + name + ".jpg";

            //lock.lock();
           // database.add_thumbnail(video_id, path);
            //lock.unlock();


            // Receive thumbnail data and save it to the file
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            while ((bytesRead = inputStream.read(buffer)) != -1) {

                fileOutputStream.write(buffer, 0, bytesRead);
            }


            fileOutputStream.close();
            inputStream.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send_thumbnail(int video_id, String path) {
        try (Socket socket = new Socket("localhost", 4042);
             OutputStream outputStream = socket.getOutputStream()) {

            // Send the "get_thumbnail" message
            PrintWriter out = new PrintWriter(outputStream, true);
            String message = "get_thumbnail";
            out.println(message);

            // Wait for a moment (optional)
            Thread.sleep(1000);

            // Send the video_id as raw bytes
            JSONObject json = new JSONObject();
            json.put("video_id", video_id);

            // Send the JSON object as a string
            out.println(json.toString());

            File videofile = new File(path);
            byte[] buffer = new byte[8192];
            int bytesRead;

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(videofile));
            while ((bytesRead = bis.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                outputStream.flush();
            }

            bis.close();

            // Send the thumbnail data
            // ... (rest of the method remains the same)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
