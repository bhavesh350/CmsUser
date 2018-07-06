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
import com.cms.wockhardt.user.adapters.CampHistoryDetailsUsersAdapter;
import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.application.SingleInstance;
import com.cms.wockhardt.user.models.Camp;
import com.cms.wockhardt.user.models.Doctor;

import java.util.ArrayList;

public class CampHistoryDetailsUsersActivity extends CustomActivity {

    private Toolbar toolbar;
    private RecyclerView rv_list;
    private TextView txt_doctor;
    private Camp.Data campData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_history_details_users);
        campData = SingleInstance.getInstance().getSelectedCamp();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("History");
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
        txt_doctor = findViewById(R.id.txt_doctor);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
//        rv_list.setNestedScrollingEnabled(false);

        txt_doctor.setText("Dr. "+campData.getDoctor().getName() + " (" + campData.getDoctor().getMsl_code() + ")"
                + ((campData.getPatients().size() > 0) ? "" : "\n\n\n No patient created yet"));

        CampHistoryDetailsUsersAdapter adapter = new CampHistoryDetailsUsersAdapter(getContext(),
                SingleInstance.getInstance().getSelectedCamp().getPatients());
        rv_list.setAdapter(adapter);
//        setTouchNClick(R.id.btn_share);
    }

    @Override
    protected void onResume() {
        super.onResume();

        CampHistoryDetailsUsersAdapter adapter = new CampHistoryDetailsUsersAdapter(getContext(),
                SingleInstance.getInstance().getSelectedCamp().getPatients());
        rv_list.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
//        if (v.getId() == R.id.btn_share) {
//            MyApp.showMassage(getContext(), "An csv file will be generated based on the data, and that will be shared.");
//        }
    }

    private Context getContext() {
        return CampHistoryDetailsUsersActivity.this;
    }


}
