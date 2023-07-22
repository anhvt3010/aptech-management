package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Course;
import com.anhvt.aptechmanagement.Property.CourseProperty;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAO implements IDAO<CourseProperty>{
    public static CourseDAO getIntance(){
        return new CourseDAO();
    }
    @Override
    public int insert(CourseProperty course) {
        return 0;
    }

    @Override
    public int update(CourseProperty course) {
        Connection cnn = JDBCUtil.getConnection();
        String sql = "UPDATE courses SET name = ?, semester = ?, status = ? WHERE id = ?";
        try {
            PreparedStatement statement = cnn.prepareStatement(sql);
            statement.setString(1, course.getName());
            statement.setInt(2, course.getSemester());
            statement.setByte(3, (byte) (course.getStatus().equals("LOCK")?0:1));
            statement.setInt(4, course.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int remove(CourseProperty course) {
        return 0;
    }

    @Override
    public ArrayList<CourseProperty> findAll() {
        Connection cnn = JDBCUtil.getConnection();
        ArrayList<CourseProperty> courses = new ArrayList<>();

        try {
            String sql = "SELECT * FROM courses";
            PreparedStatement stm = cnn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setSemester(rs.getInt("semester"));
                course.setStatus((byte) rs.getInt("status"));

                CourseProperty courseProperty = new CourseProperty(course);
                courses.add(courseProperty);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(cnn);
        }
        return courses;
    }

    @Override
    public CourseProperty selectById(int id) {
        CourseProperty course = new CourseProperty(new Course());
        Connection cnn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM courses WHERE id = ?";
        try {
//            PreparedStatement stm = cnn.prepareStatement(sql);
//            stm.setInt(1, id);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()){
//                course.setId(rs.getInt("id"));
//                course.setName(rs.getString("name"));
//                course.setSemester(rs.getInt("semester"));
//                course.setStatus((byte) rs.getInt("status"));
//            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(cnn);
        }
        return course;
    }

    @Override
    public ArrayList<CourseProperty> selectByCondition(String condition) {
        return null;
    }
    public Course selectByIdCourse(int id) {
        Connection cnn = JDBCUtil.getConnection();
        PreparedStatement stm = null;
        String sql = "SELECT * FROM courses WHERE id = ?";
        Course course = new Course();
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setSemester(rs.getInt("semester"));
                course.setStatus((byte) rs.getInt("status"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(cnn);
        }
        return course;
    }


    public ArrayList<Course> findAllCourse() {
        Connection cnn = JDBCUtil.getConnection();
        ArrayList<Course> courses = new ArrayList<>();

        try {
            String sql = "SELECT * FROM courses";
            PreparedStatement stm = cnn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setSemester(rs.getInt("semester"));
                course.setStatus((byte) rs.getInt("status"));

                courses.add(course);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(cnn);
        }
        return courses;
    }
}
