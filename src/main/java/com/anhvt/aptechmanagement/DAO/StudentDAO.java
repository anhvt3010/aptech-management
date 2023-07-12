package com.anhvt.aptechmanagement.DAO;



import com.anhvt.aptechmanagement.Entity.Student;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO implements IDAO<Student> {

    public static StudentDAO getIntance(){
        return new StudentDAO();
    }

    public Student getAccountByEmail(String email){
        Student student = null;
        Connection connection = JDBCUtil.getConnection();
        try {
            String sql = "SELECT * FROM student WHERE email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet resultSet = stm.executeQuery();
            if(resultSet.next()){
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setEmail(email);
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setPhone(resultSet.getString("phone"));
                student.setPassword(resultSet.getString("password"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closeConnection(connection);
        }
        return student;
    }
    @Override
    public int insert(Student student) {
        return 0;
    }

    @Override
    public int update(Student student) {
        return 0;
    }

    @Override
    public int remove(Student student) {
        return 0;
    }

    @Override
    public ArrayList<Student> findAll() {
        ArrayList<Student> findAll = new ArrayList<Student>();
        Connection cnn = JDBCUtil.getConnection();
        try {
            String sql = "SELECT * FROM student";
            PreparedStatement stm = cnn.prepareStatement(sql);

            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setEmail(resultSet.getString("email"));
                student.setPhone(resultSet.getString("phone"));

                findAll.add(student);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return findAll;
    }

    @Override
    public Student selectById(Student student) {
        return null;
    }

    @Override
    public ArrayList<Student> selectByCondition(String condition) {
        return null;
    }
}
