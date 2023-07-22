package com.anhvt.aptechmanagement.Utils;
import org.mindrot.jbcrypt.BCrypt;
public class PasswordEncoder {
    public static String encodePassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
