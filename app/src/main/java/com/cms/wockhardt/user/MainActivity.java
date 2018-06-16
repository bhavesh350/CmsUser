package com.cms.wockhardt.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

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

        }

        setupUiElements();
    }

    private void setupUiElements() {
        setClick(R.id.btn_my_doctors);
        setClick(R.id.btn_camp_plan);
        setClick(R.id.btn_camp_execution);
        setClick(R.id.btn_notification);
        setClick(R.id.btn_leaderboard);
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

        }

    }

    private Context getContext() {
        return MainActivity.this;
    }
}
