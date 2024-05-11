package cue.edu.co.velocerentals.utils;

import org.mindrot.jbcrypt.BCrypt;

// Utility class for password hashing and verification.
public class HashingUtil {

    // Method to hash a plaintext password.
    public static String hashPassword(String textPasswoord) {
        return BCrypt.hashpw(textPasswoord, BCrypt.gensalt());
    }

    // Method to verify a plaintext password against a hashed password.
    public static boolean checkPassword(String textPassword, String hashedPassword) {
        return BCrypt.checkpw(textPassword, hashedPassword);
    }
}
