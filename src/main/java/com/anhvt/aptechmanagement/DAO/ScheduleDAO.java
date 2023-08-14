package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Schedule;
import com.anhvt.aptechmanagement.Model.Semester;
import com.anhvt.aptechmanagement.Model.Student_Learn;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ScheduleDAO implements IDAO<Schedule>{
    static Connection cnn = null;
    private PreparedStatement stm = null;

    public static ScheduleDAO getInstance(){
        cnn = JDBCUtil.getConnection();
        return  new ScheduleDAO();
    }

    @Override
    public int insert(Schedule schedule) {
        String sql = "INSERT INTO schedule(semester_id, class_id, link) VALUES (?, ? ,?)";
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
        String sql = "UPDATE schedule SET link=? WHERE id = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setString(1, schedule.getLink());
            stm.setInt(2, schedule.getId());

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
        ArrayList<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT  * FROM schedule";
        try {
            stm = cnn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt("id"));
                schedule.setClasses(ClassDAO.getIntance().selectById(rs.getInt("class_id")));
                schedule.setSemester(SemesterDAO.getInstance().selectById(rs.getInt("semester_id")));
                schedule.setLink(rs.getString("link"));

                schedules.add(schedule);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return schedules;
    }

    public boolean checkLinkExisted(Semester semester, Classes classes) {
        String sql = "SELECT COUNT(*) AS count FROM schedule WHERE semester_id = ? AND class_id = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, semester.getId());
            stm.setInt(2, classes.getId());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return false;
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
        Schedule schedule = new Schedule();
        String sql = "SELECT * FROM schedule WHERE class_id = ? AND semester_id = ?";
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
