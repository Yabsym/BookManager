package com.absym.dao;

import com.absym.entity.Borrower;
import com.absym.util.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowerDao {

    public static void delete(String borrowerAccount) {
        final String sql = "DELETE FROM borrower WHERE borrowerAccount = ?";
        DBConnector connector = new DBConnector();
        connector.excuteUpdate(sql, borrowerAccount);
    }

    public static void delete(Borrower borrower) {
        final String sql = "DELETE FROM borrower WHERE borrowerAccount = ?";
        DBConnector connector = new DBConnector();
        connector.excuteUpdate(sql, borrower.getBorrowerAccount());
    }

    public static void insert(Borrower borrower) {
        final String sql = " INSERT INTO borrower(borrowerAccount,borrowerAddress,borrowerName,borrowerID,phone,sex,borrowBook,maxBook)VALUES(?,?,?,?,?,?,?,?)";
        DBConnector connector = new DBConnector();
        connector.excuteUpdate(sql, borrower.getBorrowerAccount(), borrower.getBorrowerAddress(), borrower.getBorrowerName(),
                borrower.getBorrowerID(), borrower.getPhone(), borrower.getSex(), borrower.getBorrowBook(), borrower.getMaxBook());
    }

    public static void update(Borrower borrower) {
        final String sql = "UPDATE borrower SET borrowerAddress=?,borrowerName=?,borrowerID=?,phone=?,sex=?,borrowBook=?,maxBook=? WHERE borrowerAccount=?";
        DBConnector connector = new DBConnector();
        connector.excuteUpdate(sql, borrower.getBorrowerAddress(), borrower.getBorrowerName(), borrower.getBorrowerID(),
                borrower.getPhone(), borrower.getSex(), borrower.getMaxBook(), borrower.getMaxBook(), borrower.getBorrowerAccount());
    }

    public static Borrower excuteQuery(String account) {
        final String sql = "SELECT * FROM borrower t WHERE t.borrowerAccount = ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, account);
        ;
        try {
            while (resultSet.next()) {
                return new Borrower(resultSet.getString("borrowerName"),resultSet.getString("borrowerAccount"),
                        resultSet.getString("borrowerAddress"), resultSet.getString("phone"),
                        resultSet.getString("borrowerID"), resultSet.getString("sex"),
                        resultSet.getInt("maxBook"), resultSet.getInt("borrowBook"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Borrower excuteQuery(Borrower borrower) {
        return BorrowerDao.excuteQuery(borrower.getBorrowerAccount());
    }

    public static List<Borrower> search(Object key, Object offset, Object limit) {
        List<Borrower> retValue = new ArrayList<>();
        final String sql = "SELECT * FROM borrower WHERE borrowerAccount LIKE '%?%' OR borrowerName LIKE '%?%' LIMIT ? , ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, key, key, offset, limit);
        try {
            while (resultSet.next()) {
                retValue.add(new Borrower(resultSet.getString("borrowerName"),resultSet.getString("borrowerAccount"),
                        resultSet.getString("borrowerAddress"),resultSet.getString("phone"),
                        resultSet.getString("borrowerID"),resultSet.getString("sex"),
                        resultSet.getInt("maxBook"),resultSet.getInt("borrowBook")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public static int searchNum(Object key) {
        final String sql = "SELECT * FROM borrower WHERE borrowerAccount LIKE '%?%' OR borrowerName LIKE '%?%'";
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

    public static List<Borrower> queryList(Object offset, Object limit) {
        List<Borrower> retValue = new ArrayList<>();
        final String sql = "SELECT * FROM borrower LIMIT ? , ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, offset, limit);
        try {
            while (resultSet.next()) {
                retValue.add(new Borrower(resultSet.getString("borrowerName"),resultSet.getString("borrowerAccount"),
                        resultSet.getString("borrowerAddress"),resultSet.getString("phone"),
                        resultSet.getString("borrowerID"),resultSet.getString("sex"),
                        resultSet.getInt("maxBook"),resultSet.getInt("borrowBook")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public static int queryNumAll() {
        final String sql = "SELECT * FROM Borrower";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql);
        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
