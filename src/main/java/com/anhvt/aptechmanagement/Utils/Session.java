package com.anhvt.aptechmanagement.Utils;

import com.anhvt.aptechmanagement.Model.Staff;
import com.anhvt.aptechmanagement.Model.Student;

import java.util.HashMap;
import java.util.Map;

public class Session {
    private static Student sessionData = new Student() ;

    private static Staff sessionDataAdmin = new Staff() ;

    // Lưu dữ liệu vào phiên
    public static void setAttribute(Student value) {
        sessionData = value ;
    }

    public static void setAttribute(Staff value) {
        sessionDataAdmin = value ;
    }

    // Lấy dữ liệu từ phiên
    public static Student getAttribute() {
        return sessionData;
    }
    public static Staff getAttributeAdmin() {
        return sessionDataAdmin;
    }

    public static void removeAttribute() {
        sessionData = new Student();
    }

    public static void removeAttributeAdmin() {
        sessionDataAdmin = new Staff();
    }
}
