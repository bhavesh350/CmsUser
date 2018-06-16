package com.cms.wockhardt.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cms.wockhardt.user.application.AppConstants;

public class RmMainActivity extends CustomActivity {

//    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_main);

        setupUiElements();
    }

    private void setupUiElements() {

        setClick(R.id.btn_notification);
        setClick(R.id.btn_my_team);
        setClick(R.id.btn_camp_approval);
        setClick(R.id.btn_leaderboard);
        setClick(R.id.btn_camp_history);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_my_team) {
            startActivity(new Intent(getContext(), MyTeamActivity.class).putExtra(AppConstants.EXTRA, false));
        } else if (v.getId() == R.id.btn_camp_approval) {
            startActivity(new Intent(getContext(), MyTeamActivity.class).putExtra(AppConstants.EXTRA, true));
        } else if (v.getId() == R.id.btn_camp_history) {
            startActivity(new Intent(getContext(), CampHistoryActivity.class).putExtra(AppConstants.EXTRA, true));
        } else if (v.getId() == R.id.btn_notification) {

        } else if (v.getId() == R.id.btn_leaderboard) {

        }

    }

    private Context getContext() {
        return RmMainActivity.this;
    }
}
