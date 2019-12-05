package sapproject.project.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String getHashString (String input){
        String generatedHashString = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(input.getBytes());

            byte[] bytes = messageDigest.digest();

            StringBuilder stringBuilder = new StringBuilder();

            for(int i = 0; i<bytes.length;i++){
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            generatedHashString = stringBuilder.toString();
        }catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        return generatedHashString;
    }
}
