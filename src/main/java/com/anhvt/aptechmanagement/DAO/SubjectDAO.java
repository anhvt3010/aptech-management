package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SubjectDAO implements IDAO<Subject>{

    static Connection cnn = null;
    PreparedStatement stm = null;

    public static SubjectDAO getIntance(){
        cnn = JDBCUtil.getConnection();
        return new SubjectDAO();
    }


    @Override
    public int insert(Subject subject) {
        String sql = "INSERT INTO subject(name, code, number_of_sessions, description, exam_format, type)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            stm = cnn.prepareStatement(sql);

            stm.setString(1, subject.getName());
            stm.setString(2, subject.getCode());
            stm.setInt(3, subject.getNumber_of_sessions());
            stm.setString(4, subject.getDescription());
            stm.setInt(5, subject.getExam_format());
            stm.setInt(6, subject.getType());

            stm.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return 0;
    }

    @Override
    public int update(Subject subject) {
        return 0;
    }

    @Override
    public int remove(Subject subject) {
        String sql = "DELETE FROM subject WHERE id = ? ";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, subject.getId());
            stm.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return 1;
    }

    @Override
    public ArrayList<Subject> findAll() {
        ArrayList<Subject> subjects = new ArrayList<>();
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
        Subject subject = new Subject();
        String sql = "SELECT * FROM subject WHERE id = ?";
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
