package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Exam;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ExamDAO implements IDAO<Exam>{

    public static ExamDAO getInstance(){
        return new ExamDAO();
    }
    @Override
    public int insert(Exam exam) {
        return 0;
    }

    @Override
    public int update(Exam exam) {
        return 0;
    }

    @Override
    public int remove(Exam exam) {
        return 0;
    }

    @Override
    public ArrayList<Exam> findAll() {
        return null;
    }

    @Override
    public Exam selectById(int id) {
        return null;
    }

    @Override
    public ArrayList<Exam> selectByCondition(String condition) {
        return null;
    }

    public ArrayList<Exam> selectByClassId(int id) {

        ArrayList<Exam> exams = new ArrayList<>();

        Connection cnn = JDBCUtil.getConnection();

        String sql = "SELECT * FROM exam WHERE class_id = ?";
        PreparedStatement stm = null;
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Exam exam = new Exam();

                exam.setId(rs.getInt("id"));
                exam.setClasses(ClassDAO.getIntance().selectById(rs.getInt("class_id")));
                exam.setSubject(SubjectDAO.getIntance().selectById(rs.getInt("subject_id")));
                exam.setExam_day(rs.getDate("exam_day").toLocalDate());
                exam.setName(rs.getString("name"));
                exam.setFormat((byte) rs.getInt("format"));
                exam.setStatus((byte) rs.getInt("status"));

                exams.add(exam);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return exams;
    }
}
