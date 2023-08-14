package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClassDAO implements IDAO<Classes>{
    static Connection cnn = null;
    PreparedStatement stm = null;
    public static ClassDAO getIntance(){
        cnn =JDBCUtil.getConnection();
        return new ClassDAO();
    }
    @Override
    public int insert(Classes classes) {
        String sql = "INSERT INTO class(sro_id, course_id, name, description, student_limits, created, type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, classes.getStaff().getId());
            stm.setInt(2, classes.getCourse().getId());
            stm.setString(3, classes.getName());
            stm.setString(4, classes.getDescription());
            stm.setInt(5, classes.getLimit());
            stm.setDate(6, Date.valueOf(classes.getCreated()));
            stm.setInt(7, classes.getType());

            stm.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return 0;
    }

    @Override
    public int update(Classes classes) {
        String sql = "UPDATE class SET sro_id=?, course_id=?, name=?, description=?, student_limits=?, type=? WHERE id=?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, classes.getStaff().getId());
            stm.setInt(2, classes.getCourse().getId());
            stm.setString(3, classes.getName());
            stm.setString(4, classes.getDescription());
            stm.setInt(5, classes.getLimit());
            stm.setInt(6, classes.getType());
            stm.setInt(7, classes.getId());

            stm.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return 0;
    }

    @Override
    public int remove(Classes classes) {
        return 0;
    }

    @Override
    public ArrayList<Classes> findAll() {
        ArrayList<Classes> listClasses = new ArrayList<>();
        String sql = "SELECT * FROM class";
        try {
            stm = cnn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Classes cl = new Classes();

                cl.setId(rs.getInt("id"));
                cl.setName(rs.getString("name"));
                cl.setCourse(CourseDAO.getIntance().selectByIdCourse(rs.getInt("course_id")));
                cl.setStaff(SroDAO.getInstance().selectById(rs.getInt("Sro_id")));
                cl.setDescription(rs.getString("description"));
                cl.setLimit(rs.getInt("student_limits"));
                cl.setType((byte) rs.getInt("type"));

                listClasses.add(cl);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return listClasses;
    }

    @Override
    public Classes selectById(int id) {
        Classes classes = new Classes();
        String sql = "SELECT * FROM class WHERE id = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                classes.setId(rs.getInt("id"));
                classes.setName(rs.getString("name"));
                classes.setDescription(rs.getString("description"));
                classes.setLimit(rs.getInt("student_limits"));
                classes.setType((byte) rs.getInt("type"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return classes;
    }

    @Override
    public ArrayList<Classes> selectByCondition(String condition) {
        return null;
    }

    public ArrayList<Classes> findClassesByCourseID(int course_id) {
        ArrayList<Classes> listClasses = new ArrayList<>();
        String sql = "SELECT * FROM class WHERE course_id = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, course_id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Classes cl = new Classes();

                cl.setId(rs.getInt("id"));
                cl.setName(rs.getString("name"));
                cl.setCourse(CourseDAO.getIntance().selectByIdCourse(rs.getInt("course_id")));
                cl.setStaff(SroDAO.getInstance().selectById(rs.getInt("Sro_id")));
                cl.setDescription(rs.getString("description"));
                cl.setLimit(rs.getInt("student_limits"));
                cl.setType((byte) rs.getInt("type"));

                listClasses.add(cl);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return listClasses;
    }

    public Classes getClassWithLastestCreatedTime() {
        Classes cls = null;
        String sql = "SELECT * FROM class ORDER BY created DESC LIMIT 1";

        try {
            stm = cnn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                cls = new Classes();
                cls.setId(rs.getInt("id"));
                cls.setName(rs.getString("name"));
                cls.setCourse(CourseDAO.getIntance().selectByIdCourse(rs.getInt("course_id")));
                cls.setStaff(SroDAO.getInstance().selectById(rs.getInt("Sro_id")));
                cls.setDescription(rs.getString("description"));
                cls.setLimit(rs.getInt("student_limits"));
                cls.setType((byte) rs.getInt("type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return cls;
    }


}
