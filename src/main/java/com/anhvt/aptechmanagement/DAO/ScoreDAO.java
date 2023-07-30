package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Score;
import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ScoreDAO implements IDAO<Score>{

    public static ScoreDAO getInstance(){
        return new ScoreDAO();
    }
    @Override
    public int insert(Score score) {
        return 0;
    }

    @Override
    public int update(Score score) {
        return 0;
    }

    @Override
    public int remove(Score score) {
        return 0;
    }

    @Override
    public ArrayList<Score> findAll() {
        return null;
    }

    @Override
    public Score selectById(int id) {
        return null;
    }

    @Override
    public ArrayList<Score> selectByCondition(String condition) {
        return null;
    }

    public ArrayList<Score> selectByIdStudent(int id) {
        ArrayList<Score> list = new ArrayList<>();
        Connection cnn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM score WHERE student_id = ?";
        PreparedStatement stm = null;
        try{
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Score score = new Score();
                score.setStudent(StudentDAO.getInstance().selectById(rs.getInt("student_id")));
                score.setSubject(SubjectDAO.getIntance().selectById(rs.getInt("subject_id")));
                score.setScore(rs.getInt("score"));
                score.setScore_max(rs.getInt("score_max"));
                score.setType((byte) rs.getInt("type"));

                list.add(score);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(cnn);
        }
        return list;
    }
    public ArrayList<Score> selectByIdStudentAndIdSubject(int student_id, int subject_id) {
        ArrayList<Score> list = new ArrayList<>();
        Connection cnn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM score WHERE student_id = ? AND subject_id = ?";
        PreparedStatement stm = null;
        try{
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, student_id);
            stm.setInt(2, subject_id);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Score score = new Score();
                score.setStudent(StudentDAO.getInstance().selectById(rs.getInt("student_id")));
                score.setSubject(SubjectDAO.getIntance().selectById(rs.getInt("subject_id")));
                score.setScore(rs.getInt("score"));
                score.setScore_max(rs.getInt("score_max"));
                score.setType((byte) rs.getInt("type"));

                list.add(score);
            }


        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(cnn);
        }

        return list;
    }
}
