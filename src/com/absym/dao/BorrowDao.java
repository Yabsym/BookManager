package com.absym.dao;

import com.absym.entity.BorrowInf;
import com.absym.util.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BorrowDao {

    public List<BorrowInf> search(Object key, Object offset, Object limit) {
        List<BorrowInf> retValue = new ArrayList<>();
        final String sql = "SELECT * FROM borrowInf WHERE bookISBN LIKE '%?%' OR borrower LIKE '%?%' LIMIT ? , ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, key, key, offset, limit);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            while (resultSet.next()) {
                retValue.add(new BorrowInf(resultSet.getString("borrower"),resultSet.getString("bookISBN"),
                        resultSet.getDate("borrowTime"),resultSet.getInt("borrowID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public int searchNum(Object key) {
        final String sql = "SELECT * FROM borrowInf WHERE logID LIKE '%?%' OR operator LIKE '%?%'";
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

    public void insert(BorrowInf borrow) {
        final String sql = "INSERT INTO borrowInf (borrower,bookISBN,borrowTime) VALUES(?,?,now())";
        DBConnector connector = new DBConnector();
        connector.excuteUpdate(sql, borrow.getBorrower(), borrow.getBookISBN());
    }

    public void delete(String borrowID) {
        final String sql = "DELETE FROM borrowInf WHERE borrowID = ?";
        DBConnector connector = new DBConnector();
        connector.excuteUpdate(sql, borrowID);
    }

    public void delete(BorrowInf borrow) {
        final String sql = "DELETE FROM borrowInf WHERE borrowID = ?";
        DBConnector connector = new DBConnector();
        connector.excuteUpdate(sql, borrow.getBorrowID());
    }

    public Integer queryBorrowerNum(String account) {
        final String sql = "SELECT * FROM borrowInf WHERE borrower = ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, account);
        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public Integer queryBookNum(String ISBN) {
        final String sql = "SELECT * FROM borrowInf WHERE bookISBN = ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, ISBN);
        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<BorrowInf> queryList(Object account, Object offset, Object limit) {
        List<BorrowInf> retValue = new ArrayList<>();
        final String sql = "SELECT * FROM borrowInf WHERE borrower = ? limit ? , ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, account, offset, limit);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            while (resultSet.next()) {
                retValue.add(new BorrowInf(resultSet.getString("borrower"),resultSet.getString("bookISBN"),
                        resultSet.getDate("borrowTime"),resultSet.getInt("borrowID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public BorrowInf excuteQuery(BorrowInf borrow) {
        final String sql = "SELECT * FROM borrowInf t WHERE t.borrowID = ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, borrow.getBorrowID());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            while (resultSet.next()) {
                return new BorrowInf(resultSet.getString("borrower"),resultSet.getString("bookISBN"),
                        resultSet.getDate("borrowTime"),resultSet.getInt("borrowID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BorrowInf> queryList(Object offset, Object limit) {
        List<BorrowInf> retValue = new ArrayList<>();
        final String sql = "SELECT * FROM borrowInf LIMIT ? , ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, offset, limit);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            while (resultSet.next()) {
                retValue.add(new BorrowInf(resultSet.getString("borrower"),resultSet.getString("bookISBN"),
                        resultSet.getDate("borrowTime"),resultSet.getInt("borrowID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public int queryNumAll() {
        final String sql = "SELECT * FROM borrowInf";
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
