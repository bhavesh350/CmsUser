package com.cms.wockhardt.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cms.wockhardt.user.adapters.CampHistoryAdapter;
import com.cms.wockhardt.user.adapters.MyTeamAdapter;
import com.cms.wockhardt.user.models.Doctor;

import java.util.ArrayList;
import java.util.List;

public class CampHistoryActivity extends CustomActivity {

    private Toolbar toolbar;
    private Spinner spinner_months;
    private RecyclerView rv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_history);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.camp_history));
        setupUiElements();
    }

    private void setupUiElements() {
        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        CampHistoryAdapter adapter = new CampHistoryAdapter(getContext(), new ArrayList<Doctor>());
        rv_list.setAdapter(adapter);

        spinner_months = findViewById(R.id.spinner_months);
        List<String> categories = new ArrayList<>();
        categories.add("January");
        categories.add("February");
        categories.add("March");
        categories.add("April");
        categories.add("May");
        categories.add("June");
        categories.add("July");
        categories.add("August");
        categories.add("September");
        categories.add("October");
        categories.add("November");
        categories.add("December");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.text_spinner);

        // attaching data adapter to spinner
        spinner_months.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private Context getContext() {
        return CampHistoryActivity.this;
    }


}
