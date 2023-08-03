package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Model.Student_Learn;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;

public class Student_LearnDAO implements IDAO<Student_Learn>{
    static Connection cnn = null;
    PreparedStatement stm = null;

    public static Student_LearnDAO getInstance(){
        cnn = JDBCUtil.getConnection();
        return new Student_LearnDAO();
    }

    @Override
    public int insert(Student_Learn studentLearn) {
        return 0;
    }

    @Override
    public int update(Student_Learn studentLearn) {
        Connection cnnn = JDBCUtil.getConnection();
        String sql = "UPDATE student_learn SET class_id=?, course_id=?, student_code=? WHERE student_id=?";

        try {
            stm = cnnn.prepareStatement(sql);

            stm.setInt(1, studentLearn.getClasses().getId());
            stm.setInt(2, studentLearn.getCourse().getId());
            stm.setString(3, studentLearn.getStudent_code());
            stm.setInt(4, studentLearn.getStudent().getId());

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnnn);
        }
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
        Student_Learn studentLearn = null;
        String sql = "SELECT * FROM student_learn WHERE student_id = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                studentLearn = new Student_Learn();
                studentLearn.setId(rs.getInt("id"));
                studentLearn.setStudent(StudentDAO.getInstance().selectById(rs.getInt("student_id")));
                studentLearn.setClasses(ClassDAO.getIntance().selectById(rs.getInt("class_id")));
                studentLearn.setCourse(CourseDAO.getIntance().selectByIdCourse(rs.getInt("course_id")));
                studentLearn.setStudent_code(rs.getString("student_code"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return studentLearn;
    }

    public ArrayList<Student> selectAllStudentsByClass(Classes classes) {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student s " +
                "INNER JOIN Student_learn sl ON s.id = sl.student_id " +
                "WHERE sl.class_id = ?";

        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, classes.getId());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setGender(rs.getBoolean("gender"));
                student.setBirth(rs.getDate("birth").toLocalDate());
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                student.setAddress(rs.getString("address"));
                student.setStatus(rs.getByte("status"));

                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return students;
    }

    public Student_Learn selectByStudentCode(String studentCode) {
        Student_Learn studentLearn = new Student_Learn();
        String sql = "SELECT * FROM student_learn WHERE student_code = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setString(1, studentCode);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                studentLearn.setId(rs.getInt("id"));
                studentLearn.setStudent(StudentDAO.getInstance().selectById(rs.getInt("student_id")));
                studentLearn.setClasses(ClassDAO.getIntance().selectById(rs.getInt("class_id")));
                studentLearn.setCourse(CourseDAO.getIntance().selectByIdCourse(rs.getInt("course_id")));
                studentLearn.setStudent_code(rs.getString("student_code"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return studentLearn;
    }

}
