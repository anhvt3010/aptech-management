package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Notification;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NotificationDAO implements IDAO<Notification> {

    public static NotificationDAO getInstance(){
        return new NotificationDAO();
    }

    @Override
    public int insert(Notification notification) {
        return 0;
    }

    @Override
    public int update(Notification notification) {
        return 0;
    }

    @Override
    public int remove(Notification notification) {
        return 0;
    }

    @Override
    public ArrayList<Notification> findAll() {
        return null;
    }

    @Override
    public Notification selectById(int id) {
        Connection cnn = JDBCUtil.getConnection();
        Notification notification = new Notification();
        String sql = "SELECT * FROM notification WHERE id = ?";
        PreparedStatement stm = null;
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                notification.setId(rs.getInt("id"));
                notification.setStudent(StudentDAO.getIntance().selectById(rs.getInt("student_id")));
                notification.setTitle(rs.getString("title"));
                notification.setContent(rs.getString("content"));
                notification.setStatus((byte) rs.getInt("status"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return notification;
    }

    @Override
    public ArrayList<Notification> selectByCondition(String condition) {
        return null;
    }
    public ArrayList<Notification> selectByStudentId(int student_id) {
        ArrayList<Notification> notifications = new ArrayList<>();
        Connection cnn = JDBCUtil.getConnection();

        String sql = "SELECT * FROM notification WHERE student_id = ?";
        PreparedStatement stm = null;
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, student_id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Notification notification = new Notification();

                notification.setId(rs.getInt("id"));
                notification.setStudent(StudentDAO.getIntance().selectById(rs.getInt("student_id")));
                notification.setTitle(rs.getString("title"));
                notification.setContent(rs.getString("content"));
                notification.setStatus((byte) rs.getInt("status"));

                notifications.add(notification);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return notifications;
    }
}
