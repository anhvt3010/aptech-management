package com.anhvt.aptechmanagement.Utils;

import com.anhvt.aptechmanagement.Model.Student;

import java.util.HashMap;
import java.util.Map;

public class Session {
    private static Student sessionData = new Student() ;

    // Lưu dữ liệu vào phiên
    public static void setAttribute(Student value) {
        sessionData = value ;
    }

    // Lấy dữ liệu từ phiên
    public static Student getAttribute() {
        return sessionData;
    }

    public static void removeAttribute() {
        sessionData = new Student();
    }
}
