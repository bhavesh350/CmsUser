package com.cms.wockhardt.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cms.wockhardt.user.adapters.CampHistoryDetailsAdapter;
import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.models.Doctor;

import java.util.ArrayList;

public class CampHistoryDetailsActivity extends CustomActivity {

    private Toolbar toolbar;
    private RecyclerView rv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_history_details);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("Employee Name");
        setupUiElements();
    }

    private void setupUiElements() {
        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_list.setNestedScrollingEnabled(false);
        CampHistoryDetailsAdapter adapter = new CampHistoryDetailsAdapter(getContext(), new ArrayList<Doctor>());
        rv_list.setAdapter(adapter);
        setTouchNClick(R.id.btn_share);
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
