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
        private Question question;

        public Question getQuestion() {
            return question;
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

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

    public class Question {
        private int id;
        private int patient_id;
        private String q1;
        private String q2;
        private String q3;
        private String q4;
        private String q5;
        private String q6;
        private String q7;
        private String score;
        private String signature;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPatient_id() {
            return patient_id;
        }

        public void setPatient_id(int patient_id) {
            this.patient_id = patient_id;
        }

        public String getQ1() {
            return q1;
        }

        public void setQ1(String q1) {
            this.q1 = q1;
        }

        public String getQ2() {
            return q2;
        }

        public void setQ2(String q2) {
            this.q2 = q2;
        }

        public String getQ3() {
            return q3;
        }

        public void setQ3(String q3) {
            this.q3 = q3;
        }

        public String getQ4() {
            return q4;
        }

        public void setQ4(String q4) {
            this.q4 = q4;
        }

        public String getQ5() {
            return q5;
        }

        public void setQ5(String q5) {
            this.q5 = q5;
        }

        public String getQ6() {
            return q6;
        }

        public void setQ6(String q6) {
            this.q6 = q6;
        }

        public String getQ7() {
            return q7;
        }

        public void setQ7(String q7) {
            this.q7 = q7;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }
    }
}
