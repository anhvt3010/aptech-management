package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Semester_Subject;
import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Semester_SubjectDAO {

    static Connection cnn = null;
    PreparedStatement stm = null;

    public static Semester_SubjectDAO getInstance(){
        cnn = JDBCUtil.getConnection();
        return new Semester_SubjectDAO();
    }
    public ArrayList<Integer> selectBySemester(int semester){
        ArrayList<Integer> listIDSubjects = new ArrayList<>();
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
    public ArrayList<Subject> getListSubjectBySemesterID(int semester_id){
        ArrayList<Subject> listSubjects = new ArrayList<>();
        String sql = "SELECT * FROM semester_subject WHERE semester_id = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, semester_id);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                listSubjects.add(SubjectDAO.getIntance().selectById(rs.getInt("subject_id")));
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return listSubjects;
    }

    public Subject checkRecordSubject(int subject_id){
        Subject subject = new Subject();
        String sql ="SELECT * FROM semester_subject WHERE subject_id = ?";
        try{
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, subject_id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                subject.setId(rs.getInt("id"));
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return subject;
    }

    public int remove(int id){
        String sql = "DELETE FROM semester_subject WHERE subject_id = ? ";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return 1;
    }

    public int insertSubjectToSemester(int semester_id, int subject_id){
        String sql = "INSERT INTO semester_subject(semester_id, subject_id) VALUES (?,?)";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, semester_id);
            stm.setInt(2, subject_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return 1;
    }

    public void insert(Semester_Subject  semesterSubject){
        String sql = "INSERT INTO semester_subject(semester_id, subject_id) VALUES (?,?)";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, semesterSubject.getSemester().getId());
            stm.setInt(2, semesterSubject.getSubject().getId());

            stm.executeUpdate();
        } catch (Exception e ) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
    }
}

