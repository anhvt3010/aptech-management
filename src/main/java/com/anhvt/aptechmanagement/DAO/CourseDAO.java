package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Course;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CourseDAO implements IDAO<Course>{
    public static CourseDAO getIntance(){
        return new CourseDAO();
    }
    @Override
    public int insert(Course course) {
        return 0;
    }

    @Override
    public int update(Course course) {
        return 0;
    }

    @Override
    public int remove(Course course) {
        return 0;
    }

    @Override
    public ArrayList<Course> findAll() {
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

    @Override
    public Course selectById(Course course) {
        return null;
    }

    @Override
    public ArrayList<Course> selectByCondition(String condition) {
        return null;
    }
}
