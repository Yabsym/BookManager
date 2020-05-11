package com.absym.entity;

public class Admin {
    private String adminAccount;
    private String adminName;
    private String email;
    private String adminId;
    private String phone;
    private String power;

    public Admin() {
    }

    public Admin(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public Admin(String adminAccount, String adminName, String email, String adminId, String phone, String power) {
        this.adminAccount = adminAccount;
        this.adminName = adminName;
        this.email = email;
        this.adminId = adminId;
        this.phone = phone;
        this.power = power;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
