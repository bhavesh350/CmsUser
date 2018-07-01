package com.cms.wockhardt.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.cms.wockhardt.user.application.AppConstants;
import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.models.Doctor;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.cms.wockhardt.user.application.AppConstants.BASE_URL;
import static com.cms.wockhardt.user.application.AppConstants.EMPLOYEE_ID;

public class MainActivity extends CustomActivity implements CustomActivity.ResponseCallback {

    private Toolbar toolbar;
    private int userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setResponseListener(this);
        userType = getIntent().getIntExtra("UserType", 0);

        RequestParams p = new RequestParams();
        p.put("emp_no", MyApp.getSharedPrefString(EMPLOYEE_ID));
        postCall(getContext(), BASE_URL + "get-all-doctors", p, "", 1);

        if (userType == 1) {
            startActivity(new Intent(getContext(), RmMainActivity.class));
            finish();
        } else if (userType == 2 || userType == 3 || userType == 4) {
            startActivity(new Intent(getContext(), ZsmSmMainActivity.class));
            finish();
        }
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
        setupUiElements();
    }

    private void setupUiElements() {
        setClick(R.id.btn_my_doctors);
        setClick(R.id.btn_camp_plan);
        setClick(R.id.btn_camp_execution);
        setClick(R.id.btn_notification);
        setClick(R.id.btn_leaderboard);
        setClick(R.id.txt_logout);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_my_doctors) {
            startActivity(new Intent(getContext(), MyDoctorsActivity.class));
        } else if (v.getId() == R.id.btn_camp_plan) {
            startActivity(new Intent(getContext(), CampPlanActivity.class));
        } else if (v.getId() == R.id.btn_camp_execution) {
            startActivity(new Intent(getContext(), CampExecutionActivity.class));
        } else if (v.getId() == R.id.btn_notification) {
            startActivity(new Intent(getContext(), NotificationsActivity.class));
        } else if (v.getId() == R.id.btn_leaderboard) {

        } else if (v.getId() == R.id.txt_logout) {
            MyApp.setStatus(AppConstants.IS_LOGIN, false);
            startActivity(new Intent(getContext(), LoginActivity.class));
            finishAffinity();
        }


    }

    private Context getContext() {
        return MainActivity.this;
    }

    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {
        if (callNumber == 1) {
            if (o.optBoolean("status")) {
                Doctor d = new Gson().fromJson(o.toString(), Doctor.class);
                MyApp.getApplication().writeDoctors(d.getData());
            } else {
//                MyApp.popMessage("Error","");
            }
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
