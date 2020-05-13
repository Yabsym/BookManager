package com.absym.dao;

import com.absym.entity.User;
import com.absym.util.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static User excuteQuery(User user) {
        final String sql = "SELECT * FROM user t WHERE t.account = ? AND t.password = ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, user.getAccount(), user.getPassword());
        try {
            if (resultSet == null) {
                return null;
            }
            if (resultSet.next()) {
                return new User(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static void excuteUpdate(User user) {
        final String sql = "UPDATE user SET password = ?,username = ?,type = ? WHERE account = ?";
        DBConnector connector = new DBConnector();
        connector.excuteUpdate(user.getPassword(), user.getUsername(), user.getType(), user.getAccount());
    }
}
