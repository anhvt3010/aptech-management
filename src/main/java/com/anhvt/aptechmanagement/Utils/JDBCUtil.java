package com.anhvt.aptechmanagement.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private static final String URL = "jdbc:mysql://localhost:3307/student_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "3124";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("connection null !");
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        try {
            if (!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
