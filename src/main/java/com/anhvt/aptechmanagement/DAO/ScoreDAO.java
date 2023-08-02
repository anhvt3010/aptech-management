package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Score;
import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScoreDAO implements IDAO<Score>{
    private static Connection cnn ;
    private PreparedStatement stm = null;

    public static ScoreDAO getInstance(){
        cnn = JDBCUtil.getConnection();
        return new ScoreDAO();
    }
    @Override
    public int insert(Score score) {
        String sql = "INSERT INTO score (student_id, subject_id, score, score_max, type) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            stm = cnn.prepareStatement(sql);

            stm.setInt(1, score.getStudent().getId());
            stm.setInt(2, score.getSubject().getId());
            stm.setInt(3, score.getScore());
            stm.setInt(4, score.getScore_max());
            stm.setInt(5, score.getType());

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return 0;
    }

    @Override
    public int update(Score score) {
        String sql = "UPDATE score SET score = ?, score_max = ? WHERE student_id =? AND subject_id = ? AND type = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, score.getScore());
            stm.setInt(2, score.getScore_max());
            stm.setInt(3, score.getStudent().getId());
            stm.setInt(4, score.getSubject().getId());
            stm.setInt(5, score.getType());

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
    public int remove(Score score) {
        return 0;
    }

    @Override
    public ArrayList<Score> findAll() {
        return null;
    }

    @Override
    public Score selectById(int id) {
        Score score = new Score();
        String sql = "SELECT * FROM score WHERE id = ?";
        try{
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                score.setId(rs.getInt("id"));
                score.setStudent(StudentDAO.getInstance().selectById(rs.getInt("student_id")));
                score.setSubject(SubjectDAO.getIntance().selectById(rs.getInt("subject_id")));
                score.setScore(rs.getInt("score"));
                score.setScore_max(rs.getInt("score_max"));
                score.setType((byte) rs.getInt("type"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return score;
    }

    @Override
    public ArrayList<Score> selectByCondition(String condition) {
        return null;
    }

    public ArrayList<Score> selectByIdStudent(int id) {
        ArrayList<Score> list = new ArrayList<>();
        String sql = "SELECT * FROM score WHERE student_id = ?";
        try{
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Score score = new Score();
                score.setId(rs.getInt("id"));
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
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return list;
    }
    public ArrayList<Score> selectByIdStudentAndIdSubject(int student_id, int subject_id) {
        ArrayList<Score> list = new ArrayList<>();
        String sql = "SELECT * FROM score WHERE student_id = ? AND subject_id = ?";
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
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return list;
    }

    public Score selectByIdStudentAndIdSubjectLT(int student_id, int subject_id) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement stmm = null;
        Score score = new Score();
        String sql = "SELECT * FROM score WHERE student_id = ? AND subject_id = ? AND type = 1";
        try {
            stmm = connection.prepareStatement(sql);
            stmm.setInt(1, student_id);
            stmm.setInt(2, subject_id);
            ResultSet rs = stmm.executeQuery();
            while(rs.next()){
                score.setId(rs.getInt("id"));
                score.setStudent(StudentDAO.getInstance().selectById(rs.getInt("student_id")));
                score.setSubject(SubjectDAO.getIntance().selectById(rs.getInt("subject_id")));
                score.setScore(rs.getInt("score"));
                score.setScore_max(rs.getInt("score_max"));
                score.setType((byte) rs.getInt("type"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stmm);
            JDBCUtil.closeConnection(connection);
        }
        return score;
    }
    public Score selectByIdStudentAndIdSubjectTH(int student_id, int subject_id) {
        Score score = new Score();
        String sql = "SELECT * FROM score WHERE student_id = ? AND subject_id = ? AND type = 2";
        try{
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, student_id);
            stm.setInt(2, subject_id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                score.setStudent(StudentDAO.getInstance().selectById(rs.getInt("student_id")));
                score.setSubject(SubjectDAO.getIntance().selectById(rs.getInt("subject_id")));
                score.setScore(rs.getInt("score"));
                score.setScore_max(rs.getInt("score_max"));
                score.setType((byte) rs.getInt("type"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return score;
    }

    public boolean isScoreExists(int studentId, int subjectId, int type) {
        String sql = "SELECT COUNT(*) FROM score WHERE student_id = ? AND subject_id = ? AND type = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, studentId);
            stm.setInt(2, subjectId);
            stm.setInt(3, type);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
