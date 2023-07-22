package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Student_Learn;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Student_LearnDAO implements IDAO<Student_Learn>{

    public static Student_LearnDAO getInstance(){
        return new Student_LearnDAO();
    }

    @Override
    public int insert(Student_Learn studentLearn) {
        return 0;
    }

    @Override
    public int update(Student_Learn studentLearn) {
        return 0;
    }

    @Override
    public int remove(Student_Learn studentLearn) {
        return 0;
    }

    @Override
    public ArrayList<Student_Learn> findAll() {
        return null;
    }

    @Override
    public Student_Learn selectById(int id) {
        return null;
    }

    @Override
    public ArrayList<Student_Learn> selectByCondition(String condition) {
        return null;
    }

    public Student_Learn selectByStudentID(int id) {
        Connection cnn = JDBCUtil.getConnection();
        Student_Learn studentLearn = new Student_Learn();
        String sql = "SELECT * FROM student_learn WHERE student_id = ?";
        PreparedStatement stm = null;
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                studentLearn.setId(rs.getInt("id"));
                studentLearn.setStudent(StudentDAO.getIntance().selectById(rs.getInt("student_id")));
                studentLearn.setClasses(ClassDAO.getIntance().selectById(rs.getInt("class_id")));
                studentLearn.setCourse(CourseDAO.getIntance().selectByIdCourse(rs.getInt("course_id")));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return studentLearn;
    }
}
