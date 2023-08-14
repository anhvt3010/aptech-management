package com.anhvt.aptechmanagement.Validation;

import com.anhvt.aptechmanagement.Utils.AlertUtil;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.MalformedURLException;
import java.net.URL;
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

    public static boolean isPositiveInteger(String number, TextField textField) {
        try {
            int value = Integer.parseInt(number);
            if (value > 0 && value < 128) {
                return true;
            } else {
                AlertUtil.showErrorAlert("Lỗi", "Vui lòng nhập số giờ học hợp lệ.","");
                textField.setText("");
                return false;
            }
        } catch (NumberFormatException e) {
            AlertUtil.showErrorAlert("Lỗi", "Vui lòng nhập số giờ học hợp lệ.","");
            textField.setText("");
            return false;
        }
    }

    public static boolean isValidInteger(TextField textField) {
        try {
            int value = Integer.parseInt(textField.getText());
            if (value > 0 && value < 128) {
                return true;
            } else {
                AlertUtil.showErrorAlert("Lỗi", "Vui lòng nhập số học sinh hợp lệ.","");
                textField.setText("");
                return false;
            }
        } catch (NumberFormatException e) {
            AlertUtil.showErrorAlert("Lỗi", "Vui lòng nhập số học sinh hợp lệ.","");
            textField.setText("");
            return false;
        }
    }

    public static boolean isValidScore(String scoreText) {
        try {
            int scoreValue = Integer.parseInt(scoreText);
            return scoreValue >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidURL(String inputURL) {
        try {
            new URL(inputURL);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

}
