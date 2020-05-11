package com.absym.entity;

import java.util.Date;

public class Log {
    private Integer logID;
    private String context;
    private String operator;
    private Date time;
    private String type;

    public Log(Integer logID, String context, String operator, Date time, String type) {
        this.logID = logID;
        this.context = context;
        this.operator = operator;
        this.time = time;
        this.type = type;
    }

    public Integer getLogID() {
        return logID;
    }

    public void setLogID(Integer logID) {
        this.logID = logID;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
