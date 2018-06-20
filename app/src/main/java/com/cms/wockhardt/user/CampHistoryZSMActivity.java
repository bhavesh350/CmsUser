package com.cms.wockhardt.user;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.cms.wockhardt.user.application.MyApp;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CampHistoryZSMActivity extends CustomActivity {

    private Toolbar toolbar;
    private Spinner spinner_designation;
    private Spinner spinner_month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_hostory_zsm);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.camp_history));
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
        spinner_designation = findViewById(R.id.spinner_designation);
        spinner_month = findViewById(R.id.spinner_month);
        List<String> categories = new ArrayList<>();
        categories.add("TM");
        categories.add("RM");
        categories.add("ZSM");
        categories.add("SM");
        categories.add("NSM");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.text_spinner);
        // attaching data adapter to spinner
        spinner_designation.setAdapter(dataAdapter);


        List<String> categoriesMonths = new ArrayList<>();
        categoriesMonths.add("January");
        categoriesMonths.add("February");
        categoriesMonths.add("March");
        categoriesMonths.add("April");
        categoriesMonths.add("May");
        categoriesMonths.add("June");
        categoriesMonths.add("July");
        categoriesMonths.add("August");
        categoriesMonths.add("September");
        categoriesMonths.add("October");
        categoriesMonths.add("November");
        categoriesMonths.add("December");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterMonths = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriesMonths);

        // Drop down layout style - list view with radio button
        dataAdapterMonths.setDropDownViewResource(R.layout.text_spinner);

        // attaching data adapter to spinner
        spinner_month.setAdapter(dataAdapterMonths);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

    }

    private Context getContext() {
        return CampHistoryZSMActivity.this;
    }


}
