package com.cms.wockhardt.user.application;

import com.cms.wockhardt.user.models.Camp;
import com.cms.wockhardt.user.models.Doctor;
import com.cms.wockhardt.user.models.MyTeam;

import java.util.ArrayList;
import java.util.List;

public class SingleInstance {
    private static final SingleInstance ourInstance = new SingleInstance();

    private Doctor.Data selectedDoctor = null;
    private Camp.Data selectedCamp = null;
    private List<MyTeam.Data> nextTeam = new ArrayList<>();

    public List<MyTeam.Data> getNextTeam() {
        return nextTeam;
    }

    public void setNextTeam(List<MyTeam.Data> nextTeam) {
        this.nextTeam = nextTeam;
    }

    public Camp.Data getSelectedCamp() {
        return selectedCamp;
    }

    public void setSelectedCamp(Camp.Data selectedCamp) {
        this.selectedCamp = selectedCamp;
    }

    public Doctor.Data getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(Doctor.Data selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }

    public static SingleInstance getInstance() {
        return ourInstance;
    }

    private SingleInstance() {
    }


}
