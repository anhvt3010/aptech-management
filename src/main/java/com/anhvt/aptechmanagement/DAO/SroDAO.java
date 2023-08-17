package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Staff;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SroDAO implements IDAO<Staff>{
    private static Connection cnn = null;
    PreparedStatement stm = null;

    public static SroDAO getInstance(){
        cnn = JDBCUtil.getConnection();
        return new SroDAO();
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
        String sql = "SELECT * FROM staff WHERE role_id = 1";
        try {
            stm =cnn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setFirst_name(rs.getString("first_name"));
                staff.setLast_name(rs.getString("last_name"));
                staff.setCode(rs.getString("code"));
                staff.setEmail(rs.getString("email"));
                staff.setPhone(rs.getString("phone"));
                staff.setStatus((byte) rs.getInt("status"));

                findAllSRO.add(staff);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return findAllSRO;
    }

    @Override
    public Staff selectById(int id) {
        Staff staff = new Staff();
        String sql = "SELECT * FROM staff WHERE id = ? AND role_id = 1";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                staff.setId(rs.getInt("id"));
                staff.setFirst_name(rs.getString("first_name"));
                staff.setLast_name(rs.getString("last_name"));
                staff.setCode(rs.getString("code"));
                staff.setEmail(rs.getString("email"));
                staff.setPhone(rs.getString("phone"));
                staff.setStatus((byte) rs.getInt("status"));
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return staff;
    }

    @Override
    public ArrayList<Staff> selectByCondition(String condition) {
        return null;
    }


    public Staff getAccountByEmail(String email){
        Staff staff = null;
        String sql = "SELECT * FROM staff WHERE email = ? AND role_id = 1";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet resultSet = stm.executeQuery();
            if(resultSet.next()){
                staff = new Staff();
                staff.setId(resultSet.getInt("id"));
                staff.setEmail(email);
                staff.setFirst_name(resultSet.getString("first_name"));
                staff.setLast_name(resultSet.getString("last_name"));
                staff.setCode(resultSet.getString("code"));
                staff.setPhone(resultSet.getString("phone"));
                staff.setPassword(resultSet.getString("password"));
                staff.setBirth(resultSet.getDate("birth").toLocalDate());
                staff.setStatus(resultSet.getByte("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return staff;
    }

}
