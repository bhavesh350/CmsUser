package com.cms.wockhardt.user.models;

import java.util.List;

public class Patient {

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
        private int camp_id;
        private String name;
        private String sex;
        private String mobile;
        private String height;
        private String weight;
        private String abdominal_circumference;
        private int id;
        private int user_id;

        public int getCamp_id() {
            return camp_id;
        }

        public void setCamp_id(int camp_id) {
            this.camp_id = camp_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getAbdominal_circumference() {
            return abdominal_circumference;
        }

        public void setAbdominal_circumference(String abdominal_circumference) {
            this.abdominal_circumference = abdominal_circumference;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
    }
}
