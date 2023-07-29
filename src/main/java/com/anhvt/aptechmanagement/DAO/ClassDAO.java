package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Student_Learn;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
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
        return 0;
    }

    @Override
    public int update(Classes classes) {
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
                cl.setStaff(SroDAO.getIntance().selectById(rs.getInt("Sro_id")));
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
}
