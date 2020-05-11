package com.absym.entity;

public class Borrower {
    private String borrowerName;
    private String borrowerAccount;
    private String borrowerAddress;
    private String phone;
    private String borrowerID;
    private String sex;
    private Integer borrowBook;
    private Integer maxBook;

    public Borrower() {
        this.borrowerName = "";
        this.borrowerAccount = "";
        this.borrowerAddress = "";
        this.phone = "";
        this.borrowerID = "";
        this.sex = "";
        this.borrowBook = 0;
        this.maxBook = 0;
    }

    public Borrower(String borrowerAccount) {
        this.borrowerAccount = borrowerAccount;
    }

    public Borrower(String borrowerName, String borrowerAccount, String borrowerAddress, String phone, String borrowerID, String sex, Integer maxBook, Integer borrowBook) {
        this.borrowerName = borrowerName;
        this.borrowerAccount = borrowerAccount;
        this.borrowerAddress = borrowerAddress;
        this.phone = phone;
        this.borrowerID = borrowerID;
        this.sex = sex;
        this.borrowBook = borrowBook;
        this.maxBook = maxBook;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerAccount() {
        return borrowerAccount;
    }

    public void setBorrowerAccount(String borrowerAccount) {
        this.borrowerAccount = borrowerAccount;
    }

    public String getBorrowerAddress() {
        return borrowerAddress;
    }

    public void setBorrowerAddress(String borrowerAddress) {
        this.borrowerAddress = borrowerAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(String borrowerID) {
        this.borrowerID = borrowerID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getBorrowBook() {
        return borrowBook;
    }

    public void setBorrowBook(Integer borrowBook) {
        this.borrowBook = borrowBook;
    }

    public Integer getMaxBook() {
        return maxBook;
    }

    public void setMaxBook(Integer maxBook) {
        this.maxBook = maxBook;
    }
}
