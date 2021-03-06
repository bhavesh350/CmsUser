package com.cms.wockhardt.user.application;

import com.cms.wockhardt.user.models.Camp;
import com.cms.wockhardt.user.models.Doctor;
import com.cms.wockhardt.user.models.MyTeam;
import com.cms.wockhardt.user.models.Patient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleInstance {
    private static final SingleInstance ourInstance = new SingleInstance();

    private Doctor.Data selectedDoctor = null;
    private Patient.Data patient;
    private Patient.Question currentQuestionReport;
    private MyTeam.Data zsmHistoryData;
    private Camp historyCamp ;
    private Map<String,String> campDates = new HashMap<>();

    public Map<String, String> getCampDates() {
        return campDates;
    }

    public void setCampDates(Map<String, String> campDates) {
        this.campDates = campDates;
    }

    public Camp getHistoryCamp() {
        return historyCamp;
    }

    public void setHistoryCamp(Camp historyCamp) {
        this.historyCamp = historyCamp;
    }

    public MyTeam.Data getZsmHistoryData() {
        return zsmHistoryData;
    }

    public void setZsmHistoryData(MyTeam.Data zsmHistoryData) {
        this.zsmHistoryData = zsmHistoryData;
    }

    public Patient.Question getCurrentQuestionReport() {
        return currentQuestionReport;
    }

    public void setCurrentQuestionReport(Patient.Question currentQuestionReport) {
        this.currentQuestionReport = currentQuestionReport;
    }

    public Patient.Data getPatient() {
        return patient;
    }

    public void setPatient(Patient.Data patient) {
        this.patient = patient;
    }

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
