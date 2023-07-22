package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Semester_SubjectDAO {

    public static Semester_SubjectDAO getInstance(){
        return new Semester_SubjectDAO();
    }
    public ArrayList<Integer> selectBySemester(int semester){
        ArrayList<Integer> listIDSubjects = new ArrayList<>();
        Connection cnn = JDBCUtil.getConnection();
        PreparedStatement stm = null;
        String sql = "SELECT * FROM semester_subject WHERE semester_id = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, semester);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                listIDSubjects.add(rs.getInt("subject_id"));
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return listIDSubjects;
    }
}
