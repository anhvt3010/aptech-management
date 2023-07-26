package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Course;
import com.anhvt.aptechmanagement.Model.Semester;
import com.anhvt.aptechmanagement.Model.Student_Learn;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SemesterDAO implements IDAO<Semester>{

    static Connection cnn = null;
    private PreparedStatement stm = null;

    public static SemesterDAO getInstance(){
        cnn = JDBCUtil.getConnection();
        return new SemesterDAO();
    }

    @Override
    public int insert(Semester semester) {
        String sql = "INSERT INTO semester(course_id, name) VALUES (?, ?)";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, semester.getCourse().getId());
            stm.setString(2, semester.getName());

            stm.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return 1;
    }

    @Override
    public int update(Semester semester) {
        return 0;
    }

    @Override
    public int remove(Semester semester) {
        return 0;
    }

    @Override
    public ArrayList<Semester> findAll() {
        ArrayList<Semester> semesters = new ArrayList<>();
        String sql = "SELECT * FROM semester";
        try {
            stm = cnn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                Semester semester = new Semester();
                semester.setId(rs.getInt("id"));
                semester.setName(rs.getString("name"));
                semester.setCourse(CourseDAO.getIntance().selectByIdCourse(rs.getInt("course_id")));

                semesters.add(semester);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return semesters;
    }

    @Override
    public Semester selectById(int id) {
        Semester semester = new Semester();
        String sql = "SELECT * FROM semester WHERE id = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                semester.setId(rs.getInt("id"));
                semester.setCourse(CourseDAO.getIntance().selectByIdCourse(rs.getInt("course_id")));
                semester.setName(rs.getString("name"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return semester;
    }

    @Override
    public ArrayList<Semester> selectByCondition(String condition) {
        return null;
    }

    public ArrayList<Semester>  selectByCourseId(int course_id) {
        ArrayList<Semester> semesters = new ArrayList<>();
        String sql = "SELECT * FROM semester WHERE course_id = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, course_id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Semester semester = new Semester();

                semester.setId(rs.getInt("id"));
                semester.setCourse(CourseDAO.getIntance().selectByIdCourse(rs.getInt("course_id")));
                semester.setName(rs.getString("name"));

                semesters.add(semester);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return semesters;
    }

}
