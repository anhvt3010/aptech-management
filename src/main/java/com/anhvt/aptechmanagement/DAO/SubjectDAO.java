package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SubjectDAO implements IDAO<Subject>{

    public static SubjectDAO getIntance(){
        return new SubjectDAO();
    }


    @Override
    public int insert(Subject subject) {
        return 0;
    }

    @Override
    public int update(Subject subject) {
        return 0;
    }

    @Override
    public int remove(Subject subject) {
        return 0;
    }

    @Override
    public ArrayList<Subject> findAll() {
        Connection cnn = JDBCUtil.getConnection();
        ArrayList<Subject> subjects = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT * FROM subject";
            stm = cnn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                Subject subject = new Subject();
                subject.setId(rs.getInt("id"));
                subject.setName(rs.getString("name"));
                subject.setCode(rs.getString("code"));
                subject.setNumber_of_sessions(rs.getInt("number_of_sessions"));
                subject.setDescription(rs.getString("description"));
                subject.setExam_format((byte) rs.getInt("exam_format"));
                subject.setType((byte) rs.getInt("type"));

                subjects.add(subject);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return subjects;
    }

    @Override
    public Subject selectById(int id) {
        Connection cnn = JDBCUtil.getConnection();
        Subject subject = new Subject();
        String sql = "SELECT * FROM subject WHERE id = ?";
        PreparedStatement stm = null;
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            subject.setId(id);
            while (rs.next()){
                subject.setName(rs.getString("name"));
                subject.setCode(rs.getString("code"));
                subject.setNumber_of_sessions(rs.getInt("number_of_sessions"));
                subject.setDescription(rs.getString("description"));
                subject.setExam_format((byte) rs.getInt("exam_format"));
                subject.setType((byte) rs.getInt("type"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }

        return subject;
    }

    @Override
    public ArrayList<Subject> selectByCondition(String condition) {
        return null;
    }


}
