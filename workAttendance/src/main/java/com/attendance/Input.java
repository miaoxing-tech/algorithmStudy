package com.attendance;

import java.util.Date;

/**
 * @author: miaoxing
 * DATE:    2017/6/20
 */
public class Input {
    /**
     * 登记号码
     */
    private String userNo;

    /**
     * 签到时间
     */
    private Date date;

    /**
     * 签到类型
     */
    private AttendanceStatus status;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
}
