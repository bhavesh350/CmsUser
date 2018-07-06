package com.cms.wockhardt.user;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.cms.wockhardt.user.adapters.CampHistoryZSMAdapter;
import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.application.SingleInstance;
import com.cms.wockhardt.user.models.CampHistoryZsm;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.cms.wockhardt.user.application.AppConstants.BASE_URL;

public class CampHistoryZSMActivity extends CustomActivity implements CustomActivity.ResponseCallback {

    private Toolbar toolbar;
    public TextView select_month;
    private RecyclerView rl_list;
//    private Spinner spinner_designation;
//    private Spinner spinner_month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResponseListener(this);
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
//        spinner_designation = findViewById(R.id.spinner_designation);
        rl_list = findViewById(R.id.rl_list);
        rl_list.setLayoutManager(new LinearLayoutManager(getContext()));
//        List<String> categories = new ArrayList<>();
//        categories.add("TM");
//        categories.add("RM");
//        categories.add("ZSM");
//        categories.add("SM");
//        categories.add("NSM");
//
//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(R.layout.text_spinner);
//        // attaching data adapter to spinner
////        spinner_designation.setAdapter(dataAdapter);


//        List<String> categoriesMonths = new ArrayList<>();
//        categoriesMonths.add("January");
//        categoriesMonths.add("February");
//        categoriesMonths.add("March");
//        categoriesMonths.add("April");
//        categoriesMonths.add("May");
//        categoriesMonths.add("June");
//        categoriesMonths.add("July");
//        categoriesMonths.add("August");
//        categoriesMonths.add("September");
//        categoriesMonths.add("October");
//        categoriesMonths.add("November");
//        categoriesMonths.add("December");

//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapterMonths = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriesMonths);
//
//        // Drop down layout style - list view with radio button
//        dataAdapterMonths.setDropDownViewResource(R.layout.text_spinner);
//
//        // attaching data adapter to spinner
//        spinner_month.setAdapter(dataAdapterMonths);

        select_month = findViewById(R.id.select_month);
        select_month.setText("JUNE, 2018");
        setTouchNClick(R.id.select_month);

        RequestParams p = new RequestParams();
        p.put("user_id", MyApp.getApplication().readUser().getData().getId());
        p.put("month", 6);
        p.put("year", 2018);

        postCall(getContext(), BASE_URL + "camp-history", p, "Loading...", 1);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == select_month) {

            Calendar c = Calendar.getInstance();
            MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(getContext(), new MonthPickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(int selectedMonth, int selectedYear) {
                    Log.d("Selected", "selectedMonth : " + selectedMonth + " selectedYear : " + selectedYear);

                    RequestParams p = new RequestParams();
                    p.put("user_id", MyApp.getApplication().readUser().getData().getId());
                    p.put("month", (selectedMonth + 1));
                    p.put("year", selectedYear);

                    postCall(getContext(), BASE_URL + "camp-history", p, "Loading...", 1);
                    select_month.setText(getMonth(selectedMonth) + ", " + selectedYear);
                }
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH));

            builder.setActivatedMonth(Calendar.JULY)
                    .setMinYear(2017)
                    .setActivatedYear(2018)
                    .setMaxYear(2030)
                    .setTitle("Select month")
                    .setOnMonthChangedListener(new MonthPickerDialog.OnMonthChangedListener() {
                        @Override
                        public void onMonthChanged(int selectedMonth) {
                            Log.d("Selected", "Selected month : " + selectedMonth);
                            // Toast.makeText(MainActivity.this, " Selected month : " + selectedMonth, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
                        @Override
                        public void onYearChanged(int selectedYear) {
                            Log.d("Selected", "Selected year : " + selectedYear);
                            // Toast.makeText(MainActivity.this, " Selected year : " + selectedYear, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build()
                    .show();
        }
    }

    private String getMonth(int selectedMonth) {
        switch (selectedMonth) {
            case 0:
                return "JAN";
            case 1:
                return "FEB";
            case 2:
                return "MARCH";
            case 3:
                return "APRIL";
            case 4:
                return "MAY";
            case 5:
                return "JUNE";
            case 6:
                return "JULY";
            case 7:
                return "AUG";
            case 8:
                return "SEPT";
            case 9:
                return "OCT";
            case 10:
                return "NOV";
            case 11:
                return "DEC";
            default:
                return "JULY";
        }
    }

    private Context getContext() {
        return CampHistoryZSMActivity.this;
    }


    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {
        if (o.optBoolean("status")) {
            CampHistoryZsm h = new Gson().fromJson(o.toString(), CampHistoryZsm.class);
            CampHistoryZSMAdapter adapter = new CampHistoryZSMAdapter(getContext(), h.getData().get(0).getChild());
            rl_list.setAdapter(adapter);
        } else {
            MyApp.popMessage("Message", "No camp history found for the selected month.", getContext());
        }
    }

    @Override
    public void onJsonArrayResponseReceived(JSONArray a, int callNumber) {

    }

    @Override
    public void onTimeOutRetry(int callNumber) {

    }

    @Override
    public void onErrorReceived(String error) {
        MyApp.popMessage("Error", error, getContext());
    }
}
