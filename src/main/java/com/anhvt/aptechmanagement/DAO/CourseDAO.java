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
    static Connection cnn = null;
    PreparedStatement stm = null;
    public static CourseDAO getIntance(){
        cnn = JDBCUtil.getConnection();
        return new CourseDAO();
    }
    @Override
    public int insert(CourseProperty course) {
        return 0;
    }

    @Override
    public int update(CourseProperty course) {
        String sql = "UPDATE courses SET name = ?, semester = ?, status = ? WHERE id = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setString(1, course.getName());
            stm.setInt(2, course.getSemester());
            stm.setByte(3, (byte) (course.getStatus().equals("LOCK")?0:1));
            stm.setInt(4, course.getId());
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
    public int remove(CourseProperty course) {
        return 0;
    }

    @Override
    public ArrayList<CourseProperty> findAll() {
        ArrayList<CourseProperty> courses = new ArrayList<>();

        try {
            String sql = "SELECT * FROM courses";
            stm = cnn.prepareStatement(sql);
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
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return courses;
    }

    @Override
    public CourseProperty selectById(int id) {
        CourseProperty course = new CourseProperty(new Course());
        String sql = "SELECT * FROM courses WHERE id = ?";
        try {
//            stm = cnn.prepareStatement(sql);
//            stm.setInt(1, id);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()){
//                course.setId(rs.getInt("id"));
//                course.setName(rs.getString("name"));
//                course.setSemester(rs.getInt("semester"));
//                course.setStatus((byte) rs.getInt("status"));
//            }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return course;
    }

    @Override
    public ArrayList<CourseProperty> selectByCondition(String condition) {
        return null;
    }
    public Course selectByIdCourse(int id) {
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
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return course;
    }


    public ArrayList<Course> findAllCourse() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT * FROM courses";
            stm = cnn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setSemester(rs.getInt("semester"));
                course.setStatus((byte) rs.getInt("status"));

                courses.add(course);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return courses;
    }
}
