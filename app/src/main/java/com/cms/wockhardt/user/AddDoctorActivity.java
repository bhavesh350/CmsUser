package com.cms.wockhardt.user;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cms.wockhardt.user.application.AppConstants;
import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.application.SingleInstance;
import com.cms.wockhardt.user.models.Doctor;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.cms.wockhardt.user.application.AppConstants.BASE_URL;
import static com.cms.wockhardt.user.application.AppConstants.EMPLOYEE_ID;

public class AddDoctorActivity extends CustomActivity implements CustomActivity.ResponseCallback {

    private AutoCompleteTextView edt_name;
    private EditText edt_msl_code;
    private EditText edt_mobile;
    private EditText edt_speciality;
    private EditText edt_city;
    private Toolbar toolbar;
    private Doctor.Data selectedDoctor = null;
    private TextView txt_clear_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        setResponseListener(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("Add Doctor");
        setupUiElements();

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            MyApp.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();
            lp.setMargins(0, MyApp.getApplication().getStatusBarHeight(), 0, -MyApp.getApplication().getStatusBarHeight());
            MyApp.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        RequestParams p = new RequestParams();
        p.put("emp_no", MyApp.getSharedPrefString(EMPLOYEE_ID));
        postCall(getContext(), BASE_URL + "get-all-doctors", p, "", 1);
    }

    private void setupUiElements() {
        txt_clear_data = findViewById(R.id.txt_clear_data);
        edt_name = findViewById(R.id.edt_name);
        edt_name.setThreshold(1);
        edt_msl_code = findViewById(R.id.edt_msl_code);
        edt_mobile = findViewById(R.id.edt_mobile);
        edt_speciality = findViewById(R.id.edt_speciality);
        edt_city = findViewById(R.id.edt_city);

        setTouchNClick(R.id.btn_submit);
        setTouchNClick(R.id.txt_clear_data);

        final List<Doctor.Data> dList = MyApp.getApplication().readDoctors();
        String names[] = new String[dList.size()];
        for (int i = 0; i < dList.size(); i++) {
            names[i] = dList.get(i).getName() + " (" + dList.get(i).getMsl_code() + ")";
        }
        if (dList.size() > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.text_spinner, names);
            edt_name.setAdapter(adapter);
            edt_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    edt_city.setText(dList.get(position).getCity());
                    edt_mobile.setText(dList.get(position).getMobile());
                    edt_msl_code.setText(dList.get(position).getMsl_code());
                    edt_name.setText(dList.get(position).getName());
                    edt_speciality.setText(dList.get(position).getSpeciality());
                    isFromSaved = true;
                    selectedDoctor = dList.get(position);
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

            if (isFromSaved) {
                SingleInstance.getInstance().setSelectedDoctor(selectedDoctor);
                finish();
            } else {
                RequestParams p = new RequestParams();
                p.put("user_id", MyApp.getSharedPrefString(AppConstants.EMPLOYEE_ID));
                p.put("name", edt_name.getText().toString());
                p.put("unique", edt_msl_code.getText().toString());
                p.put("mobile", edt_mobile.getText().toString());
                p.put("speciality", edt_speciality.getText().toString());
                p.put("city", edt_city.getText().toString());

                postCall(getContext(), AppConstants.BASE_URL + "create-doctor", p, "Creating doctor...", 2);
            }
        } else if (v == txt_clear_data) {
            edt_city.setText("");
            edt_speciality.setText("");
            edt_mobile.setText("");
            edt_msl_code.setText("");
            edt_name.setText("");
            isFromSaved = false;
        }
    }

    private Context getContext() {
        return AddDoctorActivity.this;
    }

    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {
        if (callNumber == 1 && o.optBoolean("status")) {
            Doctor d = new Gson().fromJson(o.toString(), Doctor.class);
            MyApp.getApplication().writeDoctors(d.getData());

            final List<Doctor.Data> dList = MyApp.getApplication().readDoctors();
            String names[] = new String[dList.size()];
            for (int i = 0; i < dList.size(); i++) {
                names[i] = dList.get(i).getName();
            }
            if (dList.size() > 0) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.text_spinner, names);
                edt_name.setAdapter(adapter);
                edt_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        edt_city.setText(dList.get(position).getCity());
                        edt_mobile.setText(dList.get(position).getMobile());
                        edt_msl_code.setText(dList.get(position).getMsl_code());
                        edt_name.setText(dList.get(position).getName());
                        edt_speciality.setText(dList.get(position).getSpeciality());
                        isFromSaved = true;
                        selectedDoctor = dList.get(position);
                    }
                });

            }
        } else if (callNumber == 2) {
            if (o.optBoolean("status")) {
                Doctor d = new Gson().fromJson(o.toString(), Doctor.class);
                MyApp.getApplication().writeDoctors(d.getData());
            } else {
                MyApp.popMessage("Error", "MSL code already exists", getContext());
            }
            List<Doctor.Data> list = MyApp.getApplication().readDoctors();
            Doctor.Data d = new Doctor().new Data();
            d.setCity(edt_city.getText().toString());
            d.setMobile(edt_mobile.getText().toString());
            d.setMsl_code(edt_msl_code.getText().toString());
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

    @Override
    public void onJsonArrayResponseReceived(JSONArray a, int callNumber) {

    }

    @Override
    public void onTimeOutRetry(int callNumber) {

    }

    @Override
    public void onErrorReceived(String error) {

    }


}
