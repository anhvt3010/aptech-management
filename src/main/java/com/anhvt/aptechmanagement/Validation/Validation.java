package com.anhvt.aptechmanagement.Validation;

public class Validation {
    public static boolean isNullOrEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }
}
