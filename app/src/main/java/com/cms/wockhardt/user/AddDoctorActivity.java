package com.cms.wockhardt.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.application.SingleInstance;
import com.cms.wockhardt.user.models.Doctor;

import java.util.ArrayList;
import java.util.List;

public class AddDoctorActivity extends CustomActivity {

    private AutoCompleteTextView edt_name;
    private EditText edt_msl_code;
    private EditText edt_mobile;
    private EditText edt_speciality;
    private EditText edt_city;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("Doctor Name");
        setupUiElements();
    }

    private void setupUiElements() {
        edt_name = findViewById(R.id.edt_name);
        edt_name.setThreshold(1);
        edt_msl_code = findViewById(R.id.edt_msl_code);
        edt_mobile = findViewById(R.id.edt_mobile);
        edt_speciality = findViewById(R.id.edt_speciality);
        edt_city = findViewById(R.id.edt_city);

        setTouchNClick(R.id.btn_submit);

        final List<Doctor> dList = MyApp.getApplication().readDoctors();
        String names[] = new String[dList.size()];
        for (int i = 0; i < dList.size(); i++) {
            names[i] = dList.get(i).getName();
        }
        if (dList.size() > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.text_spinner, names);
            edt_name.setAdapter(adapter);

            edt_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    edt_city.setText(dList.get(position).getCity());
                    edt_mobile.setText(dList.get(position).getMobile());
                    edt_msl_code.setText(dList.get(position).getMslCode());
                    edt_name.setText(dList.get(position).getName());
                    edt_speciality.setText(dList.get(position).getSpeciality());
                    isFromSaved = true;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }

    private boolean isFromSaved = false;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_submit) {
            if (edt_name.getText().toString().isEmpty()) {
                edt_name.setError("Enter doctor name");
                return;
            }
            if (edt_msl_code.getText().toString().isEmpty()) {
                edt_msl_code.setError("Enter MSL code");
                return;
            }
            if (edt_mobile.getText().toString().length() < 10) {
                edt_mobile.setError("Enter a valid mobile number");
                return;
            }
            if (edt_city.getText().toString().isEmpty()) {
                edt_city.setError("Enter City");
                return;
            }

            List<Doctor> list = MyApp.getApplication().readDoctors();
            Doctor d = new Doctor();
            d.setCity(edt_city.getText().toString());
            d.setMobile(edt_mobile.getText().toString());
            d.setMslCode(edt_msl_code.getText().toString());
            d.setSpeciality(edt_speciality.getText().toString());
            d.setName(edt_name.getText().toString());
            if (!isFromSaved) {
                list.add(d);
                MyApp.getApplication().writeDoctors(list);
            }

            SingleInstance.getInstance().setSelectedDoctor(d);
            finish();
        }
    }

    private Context getContext() {
        return AddDoctorActivity.this;
    }


}
