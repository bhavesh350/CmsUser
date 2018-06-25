package com.cms.wockhardt.user;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.cms.wockhardt.user.adapters.MyCampsAdapter;
import com.cms.wockhardt.user.application.AppConstants;
import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.models.Camp;
import com.cms.wockhardt.user.models.Doctor;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.williamww.silkysignature.views.SignaturePad;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.cms.wockhardt.user.application.AppConstants.BASE_URL;
import static com.cms.wockhardt.user.application.AppConstants.EMPLOYEE_ID;

public class MyCampsActivity extends CustomActivity implements CustomActivity.ResponseCallback {

    private RecyclerView rv_list;
    private Toolbar toolbar;
    public boolean amIRm = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        setResponseListener(this);
        amIRm = getIntent().getBooleanExtra(AppConstants.EXTRA, false);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        if (amIRm)
            getSupportActionBar().setTitle(getString(R.string.camp_approval));
        else
            getSupportActionBar().setTitle(getString(R.string.my_camps));
        setupUiElements();

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            MyApp.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
//            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();
//            lp.setMargins(0, MyApp.getApplication().getStatusBarHeight(), 0, -MyApp.getApplication().getStatusBarHeight());
            MyApp.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        RequestParams p = new RequestParams();
        p.put("user_id", MyApp.getSharedPrefString(EMPLOYEE_ID));
        postCall(getContext(), BASE_URL + "my-camp", p, "Fetching camp data...", 1);
    }

    private void setupUiElements() {
        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    private Context getContext() {
        return MyCampsActivity.this;
    }


    public void openApprovalCampDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ffffff")));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.approval_dialog);

        Button btn_approve = dialog.findViewById(R.id.btn_approve);
        Button btn_reject = dialog.findViewById(R.id.btn_reject);

        btn_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                MyApp.showMassage(getContext(), "Color will be change based on the real data.");
            }
        });

        btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


//        dialog.show();
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width = -1;
//        lp.height = -1;
//        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }

    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {
        if (callNumber == 1 && o.optBoolean("status")) {
            Camp c = new Gson().fromJson(o.toString(), Camp.class);
            if (c.getData().size() == 0) {
                MyApp.popFinishableMessage("Message", "No camp created yet", MyCampsActivity.this);
            } else {
                MyCampsAdapter adapter = new MyCampsAdapter(getContext(), c.getData());
                rv_list.setAdapter(adapter);
            }
        } else {
            MyApp.popFinishableMessage("Error", o.optJSONArray("data").optString(0), MyCampsActivity.this);
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
