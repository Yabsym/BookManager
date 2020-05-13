package com.absym.util;

import java.sql.*;

public class DBConnector {
    private Connection conn;
    private PreparedStatement preparedStatement;

    public DBConnector() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/BookManagerDB?serverTimezone=UTC&characterEncoding=UTF-8";
            String user = "root";
            String psw = "99370680";
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, user, psw);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet excuteQuery(String sql, Object... params) {
        try {
            preparedStatement = conn.prepareStatement(sql);
            int i = 1;
            for (Object param : params) {
                preparedStatement.setObject(i++, param);
            }
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void excuteUpdate(String sql, Object... params) {
        try {
            preparedStatement = conn.prepareStatement(sql);
            int i = 1;
            for (Object param : params) {
                preparedStatement.setObject(i++, param);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}

