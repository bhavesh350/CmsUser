package com.cms.wockhardt.user.models;

import java.io.Serializable;

public class Doctor implements Serializable {
    private static final long serialVersionUID = 7528747L;

    private String name;
    private String mslCode;
    private String mobile;
    private String speciality;
    private String city;

    public String getMslCode() {
        return mslCode;
    }

    public void setMslCode(String mslCode) {
        this.mslCode = mslCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
