package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClassDAO implements IDAO<Classes>{

    public static ClassDAO getIntance(){
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
        Connection cnn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM class";
        try {
            PreparedStatement stm = cnn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Classes cl = new Classes();

                cl.setId(rs.getInt("id"));
                cl.setName(rs.getString("name"));
                cl.setDescription(rs.getString("description"));
                cl.setLimit(rs.getInt("student_limits"));
                cl.setType((byte) rs.getInt("type"));

                listClasses.add(cl);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(cnn);
        }
        return listClasses;
    }

    @Override
    public Classes selectById(Classes classes) {
        return null;
    }

    @Override
    public ArrayList<Classes> selectByCondition(String condition) {
        return null;
    }
}
