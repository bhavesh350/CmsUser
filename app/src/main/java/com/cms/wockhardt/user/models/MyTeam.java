package com.cms.wockhardt.user.models;

import android.app.admin.DeviceAdminInfo;

import java.util.List;

public class MyTeam {
    private boolean status;
    private String message;
    private List<Data> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data {
        private int id;
        private int parent_id;
        private int emp_no;
        private String hq;
        private String name;
        private String mobile_no;
        private String personal_email;
        private String corporate_email;
        private String designation;
        private int status;
        private String device_token;
        private List<Data> child;
        private Data parent;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public int getEmp_no() {
            return emp_no;
        }

        public void setEmp_no(int emp_no) {
            this.emp_no = emp_no;
        }

        public String getHq() {
            return hq;
        }

        public void setHq(String hq) {
            this.hq = hq;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
        }

        public String getPersonal_email() {
            return personal_email;
        }

        public void setPersonal_email(String personal_email) {
            this.personal_email = personal_email;
        }

        public String getCorporate_email() {
            return corporate_email;
        }

        public void setCorporate_email(String corporate_email) {
            this.corporate_email = corporate_email;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDevice_token() {
            return device_token;
        }

        public void setDevice_token(String device_token) {
            this.device_token = device_token;
        }

        public List<Data> getChild() {
            return child;
        }

        public void setChild(List<Data> child) {
            this.child = child;
        }

        public Data getParent() {
            return parent;
        }

        public void setParent(Data parent) {
            this.parent = parent;
        }
    }
}
