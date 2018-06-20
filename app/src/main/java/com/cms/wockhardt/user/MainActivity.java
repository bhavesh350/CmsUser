package com.cms.wockhardt.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.cms.wockhardt.user.application.MyApp;

public class MainActivity extends CustomActivity {

    private Toolbar toolbar;
    private int userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userType = getIntent().getIntExtra("UserType", 0);

        if (userType == 1) {
            startActivity(new Intent(getContext(), RmMainActivity.class));
            finish();
        } else if (userType == 2 || userType == 3) {
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

        } else if (v.getId() == R.id.btn_leaderboard) {

        } else if (v.getId() == R.id.txt_logout) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            finishAffinity();
        }


    }

    private Context getContext() {
        return MainActivity.this;
    }
}
