package com.anhvt.aptechmanagement.Utils;

import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Semester;

public class SelectedSemesterStorage {
    private static Semester selectedSemester;

    public static void setSelectedClass(Semester semester) {
        selectedSemester = semester;
    }

    public static Semester getSelectedClass() {
        return selectedSemester;
    }
}
