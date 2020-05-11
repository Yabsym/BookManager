package com.absym.entity;

public class Book {
    private String bookISBN;
    private String bookName;
    private String publicer;
    private String publicTime;
    private String type;
    private String per;
    private Double price;
    private Integer allNum;
    private Integer inventoryNum;

    public Book() {
        this.bookISBN = "";
        this.bookName = "";
        this.publicer = "";
        this.publicTime = "";
        this.type = "A";
        this.per = "";
        this.price = 0.0;
        this.allNum = 0;
        this.inventoryNum = 0;
    }

    public Book(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public Book(String bookISBN, String bookName, String per, Double price, String publicer, String publicTime, String type, Integer allNum, Integer inventoryNum) {
        this.bookISBN = bookISBN;
        this.bookName = bookName;
        this.publicer = publicer;
        this.publicTime = publicTime;
        this.type = type;
        this.per = per;
        this.price = price;
        this.allNum = allNum;
        this.inventoryNum = inventoryNum;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublicer() {
        return publicer;
    }

    public void setPublicer(String publicer) {
        this.publicer = publicer;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public Integer getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(Integer inventoryNum) {
        this.inventoryNum = inventoryNum;
    }
}
