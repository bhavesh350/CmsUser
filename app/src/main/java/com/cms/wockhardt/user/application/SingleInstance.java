package com.cms.wockhardt.user.application;

import com.cms.wockhardt.user.models.Doctor;

public class SingleInstance {
    private static final SingleInstance ourInstance = new SingleInstance();

    private Doctor selectedDoctor = null;

    public Doctor getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(Doctor selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }

    public static SingleInstance getInstance() {
        return ourInstance;
    }

    private SingleInstance() {
    }


}
