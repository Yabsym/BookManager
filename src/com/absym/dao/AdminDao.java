package com.absym.dao;

import com.absym.entity.Admin;
import com.absym.util.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    public static List<Admin> search(Object key, Object offset, Object limit) {
        List<Admin> retValue = new ArrayList<>();
        final String sql = "SELECT * FROM admin WHERE adminName LIKE '%?%' OR adminAccount LIKE '%?%' LIMIT ? , ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, key, key, offset, limit);
        try {
            while (resultSet.next()) {
                retValue.add(new Admin(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public static int searchNum(Object key) {
        final String sql = "SELECT * FROM admin WHERE adminName LIKE '%?%' OR adminAccount LIKE '%?%'";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, key, key);
        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static Admin excuteQuery(Admin admin) {
        final String sql = "SELECT * FROM admin t WHERE t.adminAccount = ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, admin.getAdminAccount());
        try {
            while (resultSet.next()) {
                return new Admin(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void excuteUpdate(Admin admin) {
        final String sql = "UPDATE admin SET adminName = ?,email = ?,adminId = ?,phone = ?,power = ? WHERE adminAccount = ?";
        DBConnector connector = new DBConnector();
        connector.excuteUpdate(sql, admin.getAdminName(), admin.getEmail(),
                admin.getAdminId(), admin.getPhone(),
                admin.getPower(), admin.getAdminAccount());
    }

    public static List<Admin> queryList(Object offset, Object limit) {
        List<Admin> retValue = new ArrayList<>();
        final String sql = "SELECT * FROM admin LIMIT ? , ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, offset, limit);
        try {
            while (resultSet.next()) {
                retValue.add(new Admin(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public static int queryNumAll() {
        final String sql = "SELECT * FROM admin";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql);
        ;
        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
