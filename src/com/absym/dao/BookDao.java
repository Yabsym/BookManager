package com.absym.dao;

import com.absym.entity.Book;
import com.absym.util.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public static List<Book> search(Object key, Object offset, Object limit) {
        List<Book> retValue = new ArrayList<>();
        final String sql = "SELECT * FROM book WHERE bookISBN LIKE '%?%' OR bookName LIKE '%?%' LIMIT ? , ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, key, key, offset, limit);
        try {
            while (resultSet.next()) {
                retValue.add(new Book(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), Double.parseDouble(resultSet.getString(4)),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), Integer.parseInt(resultSet.getString(8)),
                        Integer.parseInt(resultSet.getString(9))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public static int searchNum(Object key) {
        final String sql = "SELECT * FROM book WHERE bookISBN LIKE '%?%' OR bookName LIKE '%?%'";
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

    public static void insert(Book book) {
        final String sql = "INSERT INTO book(bookName,type,publicer,publicTime,per,price,inventoryNum,allNum,bookISBN)VALUES(?,?,?,?,?,?,?,?,?)";
        DBConnector connector = new DBConnector();
        connector.excuteUpdate(sql, book.getBookName(), book.getType(), book.getPublicer(), book.getPublicTime(),
                book.getPer(), book.getPrice(), book.getInventoryNum(), book.getAllNum(), book.getBookISBN());
    }

    public static void update(Book book) {
        final String sql = "UPDATE book SET bookName=?,publicer=?,publicTime=?,type=?,per=?,price =?,inventoryNum=?,allNum=? WHERE bookISBN=?";
        DBConnector connector = new DBConnector();
        connector.excuteUpdate(sql, book.getBookName(), book.getPublicer(), book.getPublicTime(), book.getType(),
                book.getPer(), book.getPrice(), book.getInventoryNum(), book.getAllNum(), book.getBookISBN());
    }

    public static void delete(String isbn) {
        final String sql = "DELETE FROM book WHERE bookISBN = ?";
        DBConnector connector = new DBConnector();
        connector.excuteUpdate(sql, isbn);
    }

    public static Book excuteQuery(Book book) {
        return BookDao.excuteQuery(book.getBookISBN());
    }

    public static Book excuteQuery(String ISBN) {
        final String sql = "SELECT * FROM book t WHERE t.bookISBN = ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, ISBN);
        try {
            while (resultSet.next()) {
                return new Book(resultSet.getString("bookISBN"),resultSet.getString("bookName"),
                        resultSet.getString("per"),resultSet.getDouble("price"),
                        resultSet.getString("publicer"),resultSet.getString("publicTime"),
                        resultSet.getString("type"),resultSet.getInt("allNum"),
                        resultSet.getInt("inventoryNum"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Book> queryList(Object offset, Object limit) {
        List<Book> retValue = new ArrayList<>();
        final String sql = "SELECT * FROM book WHERE bookISBN LIMIT ? , ?";
        DBConnector connector = new DBConnector();
        ResultSet resultSet = connector.excuteQuery(sql, offset, limit);
        try {
            while (resultSet.next()) {
                retValue.add(new Book(resultSet.getString("bookISBN"),resultSet.getString("bookName"),
                        resultSet.getString("per"),resultSet.getDouble("price"),resultSet.getString("publicer"),
                        resultSet.getString("publicTime"),resultSet.getString("type"),resultSet.getInt("allNum"),
                        resultSet.getInt("inventoryNum")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public static int queryNumAll() {

        final String sql = "SELECT * FROM book";
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
