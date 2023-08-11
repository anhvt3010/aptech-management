package com.anhvt.aptechmanagement.Utils;
import org.mindrot.jbcrypt.BCrypt;
public class PasswordEncoder {
    public static String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
