package com.cms.wockhardt.user;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cms.wockhardt.user.adapters.QuestionnaireAdapter;
import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.models.Doctor;
import com.williamww.silkysignature.views.SignaturePad;

import java.util.ArrayList;

public class SubmitQuestionnaireActivity extends CustomActivity {

    private Toolbar toolbar;
    private TextView txt_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_qustionnaires);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.result));
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
        txt_score = findViewById(R.id.txt_score);
        setTouchNClick(R.id.btn_share);
        setTouchNClick(R.id.btn_next_patient);
        setTouchNClick(R.id.btn_finish_camp);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_share) {
            MyApp.shareText(getContext(), "Patient Report", "Some text will be hare based on the generated report.");
        } else if (v.getId() == R.id.btn_next_patient) {
            finish();
        } else if (v.getId() == R.id.btn_finish_camp) {
            startActivity(new Intent(getContext(), MainActivity.class));
            finishAffinity();
        }

    }

    private Context getContext() {
        return SubmitQuestionnaireActivity.this;
    }
}
