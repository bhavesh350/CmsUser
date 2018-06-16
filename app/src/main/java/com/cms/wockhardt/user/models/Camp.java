package com.cms.wockhardt.user.models;

public class Camp {
    private String campDate;
    private Doctor doctor;
    private int approvalStatus;

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getCampDate() {
        return campDate;
    }

    public void setCampDate(String campDate) {
        this.campDate = campDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
