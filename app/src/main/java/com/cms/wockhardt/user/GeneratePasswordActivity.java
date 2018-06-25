package com.cms.wockhardt.user;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.cms.wockhardt.user.application.MyApp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class GeneratePasswordActivity extends CustomActivity implements CustomActivity.ResponseCallback{

    private Toolbar toolbar;
    private EditText edt_password;
    private EditText edt_confirm_password;
    private EditText edt_emp_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_password);
        toolbar = findViewById(R.id.toolbar);
        setResponseListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.generate_password));
        setupUiElements();


        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            MyApp.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            MyApp.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setupUiElements() {

        setTouchNClick(R.id.btn_generate_password);
        edt_confirm_password = findViewById(R.id.edt_confirm_password);
        edt_password = findViewById(R.id.edt_password);
        edt_emp_id = findViewById(R.id.edt_emp_id);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_generate_password) {
            if (edt_emp_id.getText().toString().isEmpty()) {
                edt_emp_id.setError("Enter your employee id");
                return;
            }
            if (edt_password.getText().toString().isEmpty()) {
                edt_password.setError("Enter password");
                return;
            }
            if (!edt_password.getText().toString().equalsIgnoreCase(edt_confirm_password.getText().toString())) {
                MyApp.popMessage("Error!", "Password does not match", getContext());
                return;
            }

            MyApp.spinnerStart(getContext(), "Generating password...");
            HashMap<String, String> params = new HashMap<>();
            params.put("emp_no", edt_emp_id.getText().toString());
            params.put("password", edt_password.getText().toString());
        }
    }

    private Context getContext() {
        return GeneratePasswordActivity.this;
    }


    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {

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
