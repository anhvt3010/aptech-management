package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Model.Student_Learn;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentDAO implements IDAO<Student> {
    static Connection cnn;
    PreparedStatement stm = null;
    public static StudentDAO getInstance(){
        cnn = JDBCUtil.getConnection();
        return new StudentDAO();
    }

    public Student getAccountByEmail(String email){
        Student student = null;
        String sql = "SELECT * FROM student WHERE email = ?";
        try {
            stm = cnn.prepareStatement(sql);
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
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return student;
    }
    @Override
    public int insert(Student student) {
        String sql = "INSERT INTO student (first_name, last_name, gender, birth, phone, email, password, address, created, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            stm = cnn.prepareStatement(sql);

            stm.setString(1, student.getFirstName());
            stm.setString(2, student.getLastName());
            stm.setBoolean(3, student.getGender());
            stm.setDate(4, Date.valueOf(student.getBirth()));
            stm.setString(5, student.getPhone());
            stm.setString(6, student.getEmail());
            stm.setString(7, student.getPassword());
            stm.setString(8, student.getAddress());
            stm.setDate(9, Date.valueOf(student.getCreated()));
            stm.setInt(10, student.getStatus());

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return 0;
    }

    public int insertStudent_StudentLearn(Student student, Student_Learn studentLearn) {
        String sql = "INSERT INTO student (first_name, last_name, gender, birth, phone, email, password, address, created, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection cnn = JDBCUtil.getConnection();
            PreparedStatement stm = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stm.setString(1, student.getFirstName());
            stm.setString(2, student.getLastName());
            stm.setBoolean(3, student.getGender());
            stm.setDate(4, Date.valueOf(student.getBirth()));
            stm.setString(5, student.getPhone());
            stm.setString(6, student.getEmail());
            stm.setString(7, student.getPassword());
            stm.setString(8, student.getAddress());
            stm.setDate(9, Date.valueOf(student.getCreated()));
            stm.setInt(10, student.getStatus());

            stm.executeUpdate();

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int studentId = generatedKeys.getInt(1);
                    // Tiếp tục thêm bản ghi vào bảng student_learn với studentId như khóa ngoại
                    String insertStudentLearnSql = "INSERT INTO student_learn (student_id, class_id, course_id, student_code) VALUES (?, ?, ?, ?)";
                    PreparedStatement insertStudentLearnStmt = cnn.prepareStatement(insertStudentLearnSql);
                    insertStudentLearnStmt.setInt(1, studentId);
                    insertStudentLearnStmt.setInt(2, studentLearn.getClasses().getId());
                    insertStudentLearnStmt.setInt(3, studentLearn.getCourse().getId());
                    insertStudentLearnStmt.setString(4, studentLearn.getStudent_code());
                    insertStudentLearnStmt.executeUpdate();
                } else {
                    System.out.println("Lấy giá trị ID không thành công.");
                }
            }

            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int update(Student student) {
        String sql = "UPDATE student " +
                "SET first_name=?, last_name=?, gender=?, birth=?, phone=?, email=?, address=?, status =? " +
                "WHERE id = ?";
        try {
            stm = cnn.prepareStatement(sql);

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
        String sql = "SELECT * FROM student";
        try {
            stm = cnn.prepareStatement(sql);
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
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return findAll;
    }

    @Override
    public Student selectById(int id_selected) {
        Student student = new Student();

        String sql = "SELECT * FROM student WHERE id = ?";
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
