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

            // Thực thi câu lệnh SQL INSERT
            int rowsInserted = statement.executeUpdate();

            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(connection);
        }
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
        Student student = null;
        Connection connection = JDBCUtil.getConnection();
        String query = "SELECT * FROM student WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_selected);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Boolean gender = resultSet.getBoolean("gender");
                LocalDate birth = resultSet.getDate("birth").toLocalDate();
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                LocalDate created = resultSet.getDate("created").toLocalDate();
                LocalDate yearOfAdmission = created.plusYears(2).plusMonths(6);;
                Byte status = resultSet.getByte("status");

                student = new Student(id, firstName, lastName, gender, birth, phone, email, password,
                        address, yearOfAdmission, created, status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public ArrayList<Student> selectByCondition(String condition) {
        return null;
    }

}
