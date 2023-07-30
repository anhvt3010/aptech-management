package com.anhvt.aptechmanagement.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regexPattern = "^(84|0[3579])\\d{8}$";

        if (phoneNumber.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(phoneNumber.trim());
        return matcher.matches();
    }
}
