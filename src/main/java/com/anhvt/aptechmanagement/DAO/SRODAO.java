package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Staff;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SRODAO implements IDAO<Staff>{

    public static SRODAO getIntance(){
        return new SRODAO();
    }

    @Override
    public int insert(Staff staff) {
        return 0;
    }

    @Override
    public int update(Staff staff) {
        return 0;
    }

    @Override
    public int remove(Staff staff) {
        return 0;
    }

    @Override
    public ArrayList<Staff> findAll() {
        ArrayList<Staff> findAllSRO = new ArrayList<>();
        Connection cnn = JDBCUtil.getConnection();
        try {
            String sql = "SELECT * FROM staff WHERE role_id = 1";
            PreparedStatement stm = cnn.prepareStatement(sql);

            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()){
                Staff staff = new Staff();
                staff.setId(resultSet.getInt("id"));
                staff.setFirst_name(resultSet.getString("first_name"));
                staff.setLast_name(resultSet.getString("last_name"));
                staff.setEmail(resultSet.getString("email"));
                staff.setPhone(resultSet.getString("phone"));
                staff.setStatus((byte) resultSet.getInt("status"));

                findAllSRO.add(staff);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(cnn);
        }
        return findAllSRO;
    }

    @Override
    public Staff selectById(int id) {
        return null;
    }

    @Override
    public ArrayList<Staff> selectByCondition(String condition) {
        return null;
    }
}
