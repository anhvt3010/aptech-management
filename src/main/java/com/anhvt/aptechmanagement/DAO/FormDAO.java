package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Form;
import com.anhvt.aptechmanagement.Property.FormProperty;
import com.anhvt.aptechmanagement.Utils.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FormDAO implements IDAO<Form>{

    public static Connection cnn = null;
    private PreparedStatement stm = null;

    public static FormDAO getInstance(){
        cnn = JDBCUtil.getConnection();
        return new FormDAO();
    }

    @Override
    public int insert(Form form) {
        String sql = "INSERT INTO form(title, link, created, status)" +
                "VALUES (?, ?, ?, ?)";
        try {
            stm = cnn.prepareStatement(sql);

            stm.setString(1, form.getTitle());
            stm.setString(2, form.getLink());
            stm.setDate(3, Date.valueOf(form.getCreated()));
            stm.setInt(4, form.getStatus());

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
    public int update(Form form) {
        String sql = "UPDATE form SET title = ?, link = ?, status = ? WHERE id = ?";

        try {
            stm = cnn.prepareStatement(sql);

            stm.setString(1, form.getTitle());
            stm.setString(2, form.getLink());
            stm.setInt(3,form.getStatus());
            stm.setInt(4, form.getId());

            stm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return 0;
    }

    @Override
    public int remove(Form form) {
        int result = 0;
        String sql = "DELETE FROM form WHERE id = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, form.getId());

            result = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return result;
    }

    @Override
    public ArrayList<Form> findAll() {
        ArrayList<Form> forms = new ArrayList<>();
        String sql = "SELECT * FROM form";
        try {
            stm = cnn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Form form = new Form();
                form.setId(rs.getInt("id"));
                form.setTitle(rs.getString("title"));
                form.setLink(rs.getString("link"));
                form.setCreated(rs.getDate("created").toLocalDate());
                form.setStatus(rs.getByte("status"));

                forms.add(form);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return forms;
    }

    @Override
    public Form selectById(int id) {
        return null;
    }

    @Override
    public ArrayList<Form> selectByCondition(String condition) {
        return null;
    }


    public ArrayList<FormProperty> findAllProperty() {
        ArrayList<FormProperty> forms = new ArrayList<>();
        String sql = "SELECT * FROM form";
        try {
            stm = cnn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Form form = new Form();
                form.setId(rs.getInt("id"));
                form.setTitle(rs.getString("title"));
                form.setLink(rs.getString("link"));
                form.setCreated(rs.getDate("created").toLocalDate());
                form.setStatus(rs.getByte("status"));

                FormProperty formProperty = new FormProperty(form);

                forms.add(formProperty);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return forms;
    }

    public int removeByID(int form_id) {
        int result = 0;
        String sql = "DELETE FROM form WHERE id = ?";
        try {
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, form_id);

            result = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closePreparedStatement(stm);
            JDBCUtil.closeConnection(cnn);
        }
        return result;
    }
}
