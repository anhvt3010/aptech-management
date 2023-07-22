package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Schedule;
import com.anhvt.aptechmanagement.Model.Semester;
import com.anhvt.aptechmanagement.Model.Student_Learn;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ScheduleDAO implements IDAO<Schedule>{

    public static ScheduleDAO getInstance(){
        return  new ScheduleDAO();
    }

    @Override
    public int insert(Schedule schedule) {
        Connection cnn = JDBCUtil.getConnection();
        String sql = "INSERT INTO schedule(semester_id, class_id, link) VALUES (?, ? ,?)";
        PreparedStatement stm = null;
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, schedule.getSemester().getId());
            stm.setInt(2, schedule.getClasses().getId());
            stm.setString(3, schedule.getLink());

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
    public int update(Schedule schedule) {
        Connection cnn = JDBCUtil.getConnection();
        PreparedStatement stm = null;
        String sql = "UPDATE schedule SET semester_id=?, class_id=?, link=? WHERE id = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, schedule.getSemester().getId());
            stm.setInt(2, schedule.getClasses().getId());
            stm.setString(3, schedule.getLink());
            stm.setInt(4, schedule.getId());

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
    public int remove(Schedule schedule) {
        return 0;
    }

    @Override
    public ArrayList<Schedule> findAll() {
        return null;
    }

    @Override
    public Schedule selectById(int id) {
        return null;
    }

    @Override
    public ArrayList<Schedule> selectByCondition(String condition) {
        return null;
    }

    public Schedule selectByClassIDAndSemesterID(int class_id, int semester_id) {
        Connection cnn = JDBCUtil.getConnection();
        Schedule schedule = new Schedule();
        String sql = "SELECT * FROM schedule WHERE class_id = ? AND semester_id = ?";
        PreparedStatement stm = null;
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, class_id);
            stm.setInt(2, semester_id);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                schedule.setId(rs.getInt("id"));
                schedule.setClasses(ClassDAO.getIntance().selectById(rs.getInt("class_id")));
                schedule.setLink(rs.getString("link"));
                schedule.setSemester(SemesterDAO.getInstance().selectById(rs.getInt("semester_id")));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return schedule;
    }

}
