package com.anhvt.aptechmanagement.Utils;

import com.anhvt.aptechmanagement.Model.Student;

public class SelectedIDStorage {

    private static int selected_id;

    public static void setSelectedIDStorage(int id){
        selected_id = id;
    }

    public static int getSelectedIDStorage(){
        return selected_id;
    }
}
