package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.*;
import java.time.LocalDate;
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
                student.setGender(resultSet.getBoolean("gender"));
                student.setBirth(resultSet.getDate("birth").toLocalDate());
                student.setAddress(resultSet.getString("address"));

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
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO student (first_name, last_name, gender, birth, phone, email, password, address, created, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setBoolean(3, student.getGender());
            statement.setDate(4, Date.valueOf(student.getBirth()));
            statement.setString(5, student.getPhone());
            statement.setString(6, student.getEmail());
            statement.setString(7, student.getPassword());
            statement.setString(8, student.getAddress());
            statement.setDate(9, Date.valueOf(student.getCreated()));
            statement.setInt(10, student.getStatus());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(connection);
        }
        return 0;
    }

    @Override
    public int update(Student student) {
        Connection cnn = JDBCUtil.getConnection();
        String query = "UPDATE student " +
                "SET first_name=?, last_name=?, gender=?, birth=?, phone=?, email=?, address=?, status =? " +
                "WHERE id = ?";
        PreparedStatement stm = null;
        try {
            stm = cnn.prepareStatement(query);

            stm.setString(1, student.getFirstName());
            stm.setString(2, student.getLastName());
            stm.setBoolean(3, student.getGender());
            stm.setDate(4, Date.valueOf(student.getBirth()));
            stm.setString(5, student.getPhone());
            stm.setString(6, student.getEmail());
            stm.setString(7, student.getAddress());
            stm.setInt(8, student.getStatus());
            stm.setInt(9, student.getId());

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return 0;
    }

    @Override
    public int remove(Student student) {
        return 0;
    }

    @Override
    public ArrayList<Student> findAll() {
        ArrayList<Student> findAll = new ArrayList<>();
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
                student.setStatus((byte) resultSet.getInt("status"));

                findAll.add(student);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(cnn);
        }
        return findAll;
    }

    @Override
    public Student selectById(int id_selected) {
        Student student = new Student();

        Connection cnn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM student WHERE id = ?";
        PreparedStatement stm = null;

        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, id_selected);
            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()){
                student.setId(resultSet.getInt("id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setGender(resultSet.getBoolean("gender"));
                student.setBirth(resultSet.getDate("birth").toLocalDate());
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                student.setPassword(resultSet.getString("password"));
                student.setAddress(resultSet.getString("address"));
                student.setCreated(resultSet.getDate("created").toLocalDate());
                student.setStatus(resultSet.getByte("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return student;
    }

    @Override
    public ArrayList<Student> selectByCondition(String condition) {
        return null;
    }

}
