package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Exam;
import com.anhvt.aptechmanagement.Property.ExamProperty;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ExamDAO implements IDAO<Exam>{
    static Connection cnn = null;
    PreparedStatement stm = null;

    public static ExamDAO getInstance(){
        cnn = JDBCUtil.getConnection();
        return new ExamDAO();
    }
    @Override
    public int insert(Exam exam) {
        String sql = "INSERT INTO exam(subject_id, class_id, name, exam_day, format, status)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            stm = cnn.prepareStatement(sql);

            stm.setInt(1, exam.getSubject().getId());
            stm.setInt(2, exam.getClasses().getId());
            stm.setString(3, exam.getName());
            stm.setDate(4, Date.valueOf(exam.getExam_day()));
            stm.setInt(5, exam.getFormat());
            stm.setInt(6, exam.getStatus());

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
    public int update(Exam exam) {
        String sql = "UPDATE exam SET subject_id = ?, class_id = ?, name = ?, exam_day = ?, format = ?, status = ? WHERE id = ?";
        try {
            stm = cnn.prepareStatement(sql);

            stm.setInt(1, exam.getSubject().getId());
            stm.setInt(2, exam.getClasses().getId());
            stm.setString(3,exam.getName());
            stm.setDate(4, Date.valueOf(exam.getExam_day()));
            stm.setInt(5,exam.getFormat());
            stm.setInt(6,exam.getStatus());
            stm.setInt(7, exam.getId());

            stm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return 0;
    }

    @Override
    public int remove(Exam exam) {
        return 0;
    }

    @Override
    public ArrayList<Exam> findAll() {
        ArrayList<Exam> exams = new ArrayList<>();
        String sql = "SELECT * FROM exam";
        try {
            stm = cnn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Exam exam = new Exam();
                exam.setId(rs.getInt("id"));
                exam.setSubject(SubjectDAO.getIntance().selectById(rs.getInt("subject_id")));
                exam.setClasses(ClassDAO.getIntance().selectById(rs.getInt("class_id")));
                exam.setName(rs.getString("name"));
                exam.setExam_day(rs.getDate("exam_day").toLocalDate());
                exam.setFormat(rs.getByte("format"));
                exam.setStatus(rs.getByte("status"));

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

    public ArrayList<ExamProperty> findAllProperty() {
        ArrayList<ExamProperty> exams = new ArrayList<>();
        String sql = "SELECT * FROM exam";
        try {
            stm = cnn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Exam exam = new Exam();
                exam.setId(rs.getInt("id"));
                exam.setSubject(SubjectDAO.getIntance().selectById(rs.getInt("subject_id")));
                exam.setClasses(ClassDAO.getIntance().selectById(rs.getInt("class_id")));
                exam.setName(rs.getString("name"));
                exam.setExam_day(rs.getDate("exam_day").toLocalDate());
                exam.setFormat(rs.getByte("format"));
                exam.setStatus(rs.getByte("status"));

                ExamProperty examProperty = new ExamProperty(exam);

                exams.add(examProperty);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return exams;
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
        String sql = "SELECT * FROM exam WHERE class_id = ?";

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


    public void updateStatusInDatabase(Exam exam) {
        String sql = "UPDATE exam SET status = ? WHERE id = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, exam.getStatus());
            stm.setInt(2, exam.getId());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
    }
}
