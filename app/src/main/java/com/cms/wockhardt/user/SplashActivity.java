package com.cms.wockhardt.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.cms.wockhardt.user.application.AppConstants;
import com.cms.wockhardt.user.application.MyApp;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MyApp.getStatus(AppConstants.IS_LOGIN)) {
                    startActivity(new Intent(getContext(), MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    finish();
                }

            }
        }, 1500);
    }

    private Context getContext() {
        return SplashActivity.this;
    }
}
