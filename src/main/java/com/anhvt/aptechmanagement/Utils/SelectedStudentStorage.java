package com.anhvt.aptechmanagement.Utils;

import com.anhvt.aptechmanagement.Model.Student;

public class SelectedStudentStorage {
    private static Student selectedStudent;

    public static void setSelectedStudent(Student student) {
        selectedStudent = student;
    }

    public static Student getSelectedStudent() {
        return selectedStudent;
    }
}
