package com.cms.wockhardt.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CampExecutionClickActivity extends CustomActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_execution_click);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.camp_execution));
        setupUiElements();
    }

    private void setupUiElements() {
        setTouchNClick(R.id.btn_new_patient);
        setTouchNClick(R.id.btn_history);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_history) {

        } else if (v.getId() == R.id.btn_new_patient) {
            startActivity(new Intent(getContext(), AddNewPatientActivity.class));
        }
    }

    private Context getContext() {
        return CampExecutionClickActivity.this;
    }


}
