package cue.edu.co.velocerentals.utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashingUtil {

    public static String hashPassword(String textPassword) {
        return BCrypt.hashpw(textPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String textPassword, String hashedPassword) {
        return BCrypt.checkpw(textPassword, hashedPassword);
    }
}
