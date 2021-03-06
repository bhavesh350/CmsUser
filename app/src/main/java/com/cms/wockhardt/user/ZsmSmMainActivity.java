package com.cms.wockhardt.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.cms.wockhardt.user.application.AppConstants;
import com.cms.wockhardt.user.application.MyApp;

import org.json.JSONArray;
import org.json.JSONObject;

public class ZsmSmMainActivity extends CustomActivity implements CustomActivity.ResponseCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResponseListener(this);
        setContentView(R.layout.activity_zsm_main);
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
        setupUiElements();
    }

    private void setupUiElements() {

        setClick(R.id.btn_notification);
        setClick(R.id.btn_my_team);
        setClick(R.id.btn_leaderboard);
        setClick(R.id.btn_camp_history);
        setClick(R.id.txt_logout);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_my_team) {
            startActivity(new Intent(getContext(), MyTeamActivity.class).putExtra(AppConstants.EXTRA, false));
        } else if (v.getId() == R.id.btn_camp_history) {
            if (MyApp.getApplication().readUser().getData().getDesignation().equals("NSM")) {
                startActivity(new Intent(getContext(), CampHistoryNSMActivity.class).putExtra(AppConstants.EXTRA, true));
                return;
            }
            startActivity(new Intent(getContext(), CampHistoryZSMActivity
                    .class).putExtra(AppConstants.EXTRA, true));
        } else if (v.getId() == R.id.btn_notification) {
            startActivity(new Intent(getContext(), NotificationsActivity.class));
        } else if (v.getId() == R.id.btn_leaderboard) {
            startActivity(new Intent(getContext(), LeaderboardActivity.class));
        } else if (v.getId() == R.id.txt_logout) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            finishAffinity();
        }

    }

    private Context getContext() {
        return ZsmSmMainActivity.this;
    }

    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {

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
