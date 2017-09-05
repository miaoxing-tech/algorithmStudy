package com.attendance;

import java.util.Date;

public class ResultInformation {
    /**
     * 姓名
     */
    private String userName;

    /**
     * 登记号码
     */
    private String userNo;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 总时长
     */
    private String totalTime;

    /**
     * 日期情况
     */
    private Date date;

    /**
     * 开始签到情况
     */
    private Boolean startStatus;

    /**
     * 结束签到情况
     */
    private Boolean endStatus;

    /**
     * 一天工作时长
     */
    private String totalWorkTime;

    /**
     * 是否满8小时
     */
    private Boolean isEight;

    /**
     * 一天考勤情况
     */
    private Boolean isRight;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getStartStatus() {
        return startStatus;
    }

    public void setStartStatus(Boolean startStatus) {
        this.startStatus = startStatus;
    }

    public Boolean getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(Boolean endStatus) {
        this.endStatus = endStatus;
    }

    public String getTotalWorkTime() {
        return totalWorkTime;
    }

    public void setTotalWorkTime(String totalWorkTime) {
        this.totalWorkTime = totalWorkTime;
    }

    public Boolean getEight() {
        return isEight;
    }

    public void setEight(Boolean eight) {
        isEight = eight;
    }

    public Boolean getRight() {
        return isRight;
    }

    public void setRight(Boolean right) {
        isRight = right;
    }
}
