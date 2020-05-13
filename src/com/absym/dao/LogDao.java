package com.absym.dao;


import com.absym.entity.Log;
import com.absym.util.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LogDao {

    public static List<Log> search(Object key, Object offset, Object limit) {
        List<Log> retValue = new ArrayList<>();
        final String sql = "SELECT * FROM log WHERE logID LIKE '%?%' OR operator LIKE '%?%' LIMIT ? , ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, key, key, offset, limit);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            while (resultSet.next()) {
                retValue.add(new Log(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2),
                        resultSet.getString(3), simpleDateFormat.parse(resultSet.getString(4)),
                        resultSet.getString(5)));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public static int searchNum(Object key) {
        final String sql = "SELECT * FROM log WHERE logID LIKE '%?%' OR operator LIKE '%?%'";
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

    public static List<Log> queryList(Object offset, Object limit) {
        List<Log> retValue = new ArrayList<>();
        final String sql = "SELECT * FROM log ORDER BY logID DESC LIMIT  ? , ? ";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, offset, limit);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            while (resultSet.next()) {
                retValue.add(new Log(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2),
                        resultSet.getString(3), simpleDateFormat.parse(resultSet.getString(4)),
                        resultSet.getString(5)));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public static int queryNumAll() {
        final String sql = "SELECT * FROM log";
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

    public static void insert(Log log) {
        LogDao.insert(log.getOperator(),log.getContext(),log.getType());
    }


    public static void insert(String operator,String context,String type) {
        final String sql = " INSERT INTO Log(operator,context,type)VALUES(?,?,?)";
        DBConnector connector = new DBConnector();
        connector.excuteUpdate(sql, operator, context, type);
    }
}

