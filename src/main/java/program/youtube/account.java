package program.youtube;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//this class only have some static mthod like hash and regex
//these methods can be transfers somewhere else
public class account {

    public static boolean isValidEmail(String email) {
        // Define the email pattern using regex
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(emailPattern);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(email);

        // Check if the input matches the pattern
        return matcher.matches();
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Handle the error appropriately
        }
    }


    public static void main(String[] args) {

    }


}
