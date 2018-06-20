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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.application.SingleInstance;
import com.cms.wockhardt.user.models.Doctor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreatePlanActivity extends CustomActivity {

    private Toolbar toolbar;
    private TextView txt_date;
    private TextView txt_doctor_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.create_plan));
        setupUiElements();

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            MyApp.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();
            lp.setMargins(0, MyApp.getApplication().getStatusBarHeight(), 0, -MyApp.getApplication().getStatusBarHeight());
            MyApp.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setupUiElements() {
        txt_date = findViewById(R.id.txt_date);
        txt_doctor_details = findViewById(R.id.txt_doctor_details);
        setClick(R.id.txt_date);
        setClick(R.id.txt_doctor_details);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.txt_date) {
            dateDialog();
        } else if (v.getId() == R.id.txt_doctor_details) {
            startActivity(new Intent(getContext(), AddDoctorActivity.class));
        }
    }

    public void dateDialog() {
        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        txt_date.setText(parseDate(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year));
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    public String parseDate(String time) {
        Log.e("Date", "parseDateToHHMM: " + time);
        String inputPattern = "DD-M-yyyy";
        String outputPattern = "d MMM, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Doctor d = SingleInstance.getInstance().getSelectedDoctor();
        if (d != null) {
            txt_doctor_details.setText(d.getName() + "\n" + d.getMobile());
        }
    }

    private Context getContext() {
        return CreatePlanActivity.this;
    }


}
