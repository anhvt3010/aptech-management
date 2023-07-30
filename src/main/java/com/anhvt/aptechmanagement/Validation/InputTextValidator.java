package com.anhvt.aptechmanagement.Validation;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputTextValidator {
    public boolean isTextFieldEmpty(TextField textField) {
        return textField == null || textField.getText().trim().isEmpty();
    }

    public static boolean isValidDatePicker(DatePicker datePicker) {
        if (datePicker == null || datePicker.getValue() == null) {
            return false;
        }
        LocalDate selectedDate = datePicker.getValue();
        LocalDate currentDate = LocalDate.now();
        LocalDate twelveYearsAgo = currentDate.minusYears(12);

        return selectedDate.isBefore(twelveYearsAgo);
    }

    public static boolean isValidName(String name) {
        String regex = "^[\\p{L} ]+$";
        if(name.isEmpty()){
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name.trim());
        return matcher.matches();
    }

    public static boolean isValidStudentCode(String studentCode) {
        String regex = "^Student\\d{7}$";
        if(studentCode.isEmpty()){
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(studentCode.trim());
        return matcher.matches();
    }
}
