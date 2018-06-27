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

import com.cms.wockhardt.user.adapters.QuestionnaireAdapter;
import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.models.Doctor;
import com.williamww.silkysignature.views.SignaturePad;

import java.util.ArrayList;

public class QuestionnaireActivity extends CustomActivity {

    private Toolbar toolbar;
    private RecyclerView rv_list;
    private ImageView img_signature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qustionnaires);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.questionnaire));
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
        setTouchNClick(R.id.btn_submit);
        setTouchNClick(R.id.btn_signature);
        rv_list = findViewById(R.id.rv_list);
        img_signature = findViewById(R.id.img_signature);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_list.setNestedScrollingEnabled(false);
        QuestionnaireAdapter adapter = new QuestionnaireAdapter(getContext(), new ArrayList<Doctor>());
        rv_list.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_submit) {
            startActivity(new Intent(getContext(), SubmitQuestionnaireActivity.class));
            finish();
        } else if (v.getId() == R.id.btn_signature) {
            openSignaturePanel();
        }
    }

    private Context getContext() {
        return QuestionnaireActivity.this;
    }

    private Bitmap signatureBitmap = null;

    private void openSignaturePanel() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.signature_dialog);

        final SignaturePad mSignaturePad = dialog.findViewById(R.id.signature_pad);
        final Button btn_clear = dialog.findViewById(R.id.btn_clear);
        final Button btn_save = dialog.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureBitmap = mSignaturePad.getSignatureBitmap();
                img_signature.setImageBitmap(signatureBitmap);
                dialog.dismiss();
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                img_signature.setImageBitmap(null);
                mSignaturePad.clear();
            }
        });
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {

            }

            @Override
            public void onClear() {
            }
        });

        dialog.show();
    }

}
