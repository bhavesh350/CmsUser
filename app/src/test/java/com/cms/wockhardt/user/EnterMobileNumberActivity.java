package com.cms.wockhardt.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cms.wockhardt.user.application.MyApp;

import org.json.JSONArray;
import org.json.JSONObject;


public class EnterMobileNumberActivity extends CustomActivity implements CustomActivity.ResponseCallback{
    private TextView tv_welcome;
    private EditText edt_phone_no;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_mobile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Verify Mobile Number");
//        toolbar.setBackgroundResource(NULL);
        RelativeLayout v = findViewById(R.id.toolbar);
        // transparent statusbar for marshmallow and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (v != null) {
                v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        //make full transparent statusBar
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
        setupUiElement();
    }

    private void setupUiElement() {

        setTouchNClick(R.id.btn_login);
        edt_phone_no = findViewById(R.id.edt_phone_no);
        tv_welcome = findViewById(R.id.tv_welcome);
        tv_welcome.setText("Welcome User" + "\nEnter your Mobile Number");

//        Shader textShader = new LinearGradient(0, 0, 0, 50,
//                new int[]{Color.parseColor("#3CBEA3"), Color.parseColor("#1D6D9E")},
//                new float[]{0, 1}, Shader.TileMode.CLAMP);
//        tv_btn_signin.getPaint().setShader(textShader);
    }

    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_login) {
            if (TextUtils.isEmpty(edt_phone_no.getText().toString())) {
                edt_phone_no.setError("Enter Your Mobile Number");
                return;
            }
            if (edt_phone_no.getText().toString().length() < 10) {
                edt_phone_no.setError("Enter a valid mobile number");
                return;
            }

            Intent intent = new Intent(getContext(), VerifyMobileActivity.class);
            intent.putExtra("phone", edt_phone_no.getText().toString());
            startActivity(intent);
        }

    }


    private Context getContext() {
        return EnterMobileNumberActivity.this;
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
