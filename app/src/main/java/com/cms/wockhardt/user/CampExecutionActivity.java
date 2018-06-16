package com.cms.wockhardt.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.cms.wockhardt.user.adapters.ExecuteCampsAdapter;
import com.cms.wockhardt.user.models.Doctor;

import java.util.ArrayList;

public class CampExecutionActivity extends CustomActivity {

    private Toolbar toolbar;
    private RecyclerView rv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_execution);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.execute_camp));
        setupUiElements();
    }

    private void setupUiElements() {
        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));

        ExecuteCampsAdapter adapter = new ExecuteCampsAdapter(getContext(), new ArrayList<Doctor>());
        rv_list.setAdapter(adapter);
    }


    private Context getContext() {
        return CampExecutionActivity.this;
    }


}
