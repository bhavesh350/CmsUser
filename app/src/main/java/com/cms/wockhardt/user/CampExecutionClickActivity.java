package com.cms.wockhardt.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.application.SingleInstance;
import com.cms.wockhardt.user.models.Camp;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

import static com.cms.wockhardt.user.application.AppConstants.BASE_URL;

public class CampExecutionClickActivity extends CustomActivity implements CustomActivity.ResponseCallback{

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_execution_click);
        toolbar = findViewById(R.id.toolbar);
        setResponseListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.camp_execution));
        setupUiElements();

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            MyApp.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            MyApp.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setupUiElements() {
        setTouchNClick(R.id.btn_new_patient);
        setTouchNClick(R.id.btn_history);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_history) {
            // showing camp history
            startActivity(new Intent(getContext(), CampHistoryDetailsUsersActivity.class));
        } else if (v.getId() == R.id.btn_new_patient) {
            startActivity(new Intent(getContext(), AddNewPatientActivity.class));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        RequestParams p = new RequestParams();
        p.put("user_id", MyApp.getApplication().readUser().getData().getId());
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        int year = c.get(Calendar.YEAR);
        p.put("month", month);
        p.put("year", year);
        p.put("user_id", MyApp.getApplication().readUser().getData().getId());
        postCall(getContext(), BASE_URL + "camp-history-for-tm", p, "", 10);
    }

    private Context getContext() {
        return CampExecutionClickActivity.this;
    }


    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {
        if (callNumber == 10 && o.optBoolean("status")) {
            Camp c = new Gson().fromJson(o.toString(), Camp.class);
            if (c.getData().size() == 0) {
//                MyApp.popFinishableMessage("Message", "No camp created yet", CampExecutionActivity.this);
            } else {
                for (int i = 0; i <c.getData().size() ; i++) {
                    if(SingleInstance.getInstance().getSelectedCamp().getId()==c.getData().get(i).getId()){
                        SingleInstance.getInstance().setSelectedCamp(c.getData().get(i));
                    }
                }
//                ExecuteCampsAdapter adapter = new ExecuteCampsAdapter(getContext(), c.getData());
//                rv_list.setAdapter(adapter);
            }
        } else {
//            MyApp.popFinishableMessage("Error", o.optJSONArray("data").optString(0), CampExecutionActivity.this);
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

    }
}
