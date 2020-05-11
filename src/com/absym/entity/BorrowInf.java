package com.absym.entity;


import java.util.Date;

public class BorrowInf {
    private String borrower;
    private String bookISBN;
    private Date borrowTime;
    private Integer borrowID;

    public BorrowInf() {
    }

    public BorrowInf(String borrower, String bookISBN) {
        this.borrower = borrower;
        this.bookISBN = bookISBN;
    }

    public BorrowInf(String borrower, String bookISBN, Date borrowTime) {
        this.borrower = borrower;
        this.bookISBN = bookISBN;
        this.borrowTime = borrowTime;
    }

    public BorrowInf(String borrower, String bookISBN, Date borrowTime, Integer borrowID) {
        this.borrower = borrower;
        this.bookISBN = bookISBN;
        this.borrowTime = borrowTime;
        this.borrowID = borrowID;
    }

    public Integer getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(Integer borrowID) {
        this.borrowID = borrowID;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }
}

