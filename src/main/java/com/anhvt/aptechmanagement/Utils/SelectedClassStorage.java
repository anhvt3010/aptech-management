package com.anhvt.aptechmanagement.Utils;

import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Student;

public class SelectedClassStorage {
    private static Classes selectedClass;

    public static void setSelectedClass(Classes classes) {
        selectedClass = classes;
    }

    public static Classes getSelectedClass() {
        return selectedClass;
    }
}
