package com.cms.wockhardt.user;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.cms.wockhardt.user.adapters.CampHistoryDetailsAdapter;
import com.cms.wockhardt.user.adapters.CampHistoryRMAdapter;
import com.cms.wockhardt.user.adapters.CampHistoryZSMAdapter;
import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.application.SingleInstance;
import com.cms.wockhardt.user.models.Doctor;
import com.cms.wockhardt.user.models.MyTeam;

import java.util.ArrayList;

public class CampHistoryDetailsActivity extends CustomActivity {

    private Toolbar toolbar;
    private RecyclerView rv_list;
    private MyTeam.Data currentData;
    private TextView txt_emp_details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentData = SingleInstance.getInstance().getZsmHistoryData();
        setContentView(R.layout.activity_camp_history_details);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle(currentData.getName());
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
        rv_list = findViewById(R.id.rv_list);
        txt_emp_details = findViewById(R.id.txt_emp_details);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_list.setNestedScrollingEnabled(false);
        setTouchNClick(R.id.btn_share);
        txt_emp_details.setText(currentData.getHq() + ", " + getIntent().getStringExtra("month"));


        if (currentData.getDesignation().equals("ZSM"))
            rv_list.setAdapter(new CampHistoryZSMAdapter(getContext(), currentData.getChild(), getIntent().getStringExtra("month")));
        else
            rv_list.setAdapter(new CampHistoryRMAdapter(getContext(), currentData.getChild()));


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_share) {
            MyApp.showMassage(getContext(), "An csv file will be generated based on the data, and that will be shared.");
        }
    }

    private Context getContext() {
        return CampHistoryDetailsActivity.this;
    }


}
