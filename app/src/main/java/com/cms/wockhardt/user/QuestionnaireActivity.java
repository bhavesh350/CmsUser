package com.cms.wockhardt.user;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.application.SingleInstance;
import com.cms.wockhardt.user.models.Camp;
import com.cms.wockhardt.user.models.Patient;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.williamww.silkysignature.views.SignaturePad;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.cms.wockhardt.user.application.AppConstants.BASE_URL;

public class QuestionnaireActivity extends CustomActivity implements CustomActivity.ResponseCallback, RadioGroup.OnCheckedChangeListener, CheckBox.OnCheckedChangeListener {

    private Toolbar toolbar;
    private ImageView img_signature;
    private CheckedTextView chk_txt_5, chk_txt_4, chk_txt_3, chk_txt_2, chk_txt_1;
    private RadioGroup radio_group_6, radio_group_5, radio_group_4, radio_group_3, radio_group_2, radio_group_1;
    private RadioButton radio_6_yes, radio_5_yes, radio_4_yes, radio_3_yes, radio_2_yes, radio_1_yes;
    private RadioButton radio_6_no, radio_5_no, radio_4_no, radio_3_no, radio_2_no, radio_1_no;
    private Patient.Data currentPatient;
    private int patientPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResponseListener(this);
        patientPosition = getIntent().getIntExtra("position", -1);
        currentPatient = SingleInstance.getInstance().getPatient();
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
        setClick(R.id.qstn5);
        setTouchNClick(R.id.btn_signature);
        img_signature = findViewById(R.id.img_signature);
        chk_txt_5 = findViewById(R.id.chk_txt_5);
        chk_txt_4 = findViewById(R.id.chk_txt_4);
        chk_txt_3 = findViewById(R.id.chk_txt_3);
        chk_txt_2 = findViewById(R.id.chk_txt_2);
        chk_txt_1 = findViewById(R.id.chk_txt_1);
        chk_txt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chk_txt_1.toggle();
            }
        });
        chk_txt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chk_txt_2.toggle();
            }
        });
        chk_txt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chk_txt_3.toggle();
            }
        });
        chk_txt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chk_txt_4.toggle();
            }
        });
        chk_txt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chk_txt_5.toggle();
            }
        });


        radio_group_6 = findViewById(R.id.radio_group_6);
        radio_group_5 = findViewById(R.id.radio_group_5);
        radio_group_4 = findViewById(R.id.radio_group_4);
        radio_group_3 = findViewById(R.id.radio_group_3);
        radio_group_2 = findViewById(R.id.radio_group_2);
        radio_group_1 = findViewById(R.id.radio_group_1);

        radio_group_1.setOnCheckedChangeListener(this);
        radio_group_2.setOnCheckedChangeListener(this);
        radio_group_3.setOnCheckedChangeListener(this);
        radio_group_4.setOnCheckedChangeListener(this);
        radio_group_5.setOnCheckedChangeListener(this);
        radio_group_6.setOnCheckedChangeListener(this);

    }

    private File signature = null;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_submit) {
            final String contentType = RequestParams.APPLICATION_OCTET_STREAM;
            RequestParams p = new RequestParams();
            p.put("patient_id", currentPatient.getId());
            p.put("q1", firstAns ? "Yes" : "No");
            p.put("q2", secondAns ? "Yes" : "No");
            p.put("q3", thirdAns ? "Yes" : "No");
            p.put("q4", fourthAns ? "Yes" : "No");
            p.put("q5", firstAns ? "Yes" : "No");
            String ans6 = (chk_txt_1.isChecked() ? "Yes," : "No,")
                    + (chk_txt_2.isChecked() ? "Yes," : "No,")
                    + (chk_txt_3.isChecked() ? "Yes," : "No,")
                    + (chk_txt_4.isChecked() ? "Yes," : "No,")
                    + (chk_txt_5.isChecked() ? "Yes" : "No");
            p.put("q6", ans6);
            p.put("q7", sixthAns ? "Yes" : "No");

            float f1 = 1.3369f * (currentPatient.getSex().equals("f") ? 0f : 1f);
            float f2 = Float.parseFloat(currentPatient.getAbdominal_circumference()) * 0.1897f;
            float f3 = 1.3738f * (secondAns ? 1f : 0f);
            float f4 = 1.5084f * (thirdAns ? 1f : 0f);
            float f5 = 0.8768f * (sixthAns ? 1f : 0f);

            p.put("score", (f1 + f2 + f3 + f4 + f5) + "");
            if (signature == null) {
                MyApp.popMessage("Alert!", "User's signature required to proceed.", getContext());
                return;
            }
            try {
                p.put("signature", signature, contentType);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            postCall(getContext(), BASE_URL + "create-question", p, "Saving Questionnaire...", 1);
            patientUpdatedData = new Patient();

        } else if (v.getId() == R.id.btn_signature) {
            openSignaturePanel();
        } else if (v.getId() == R.id.qstn5) {
            MyApp.popMessage("Sake Information", "Sake 180ml = Beer 500ml = Shochu 80ml = Whisky 60ml = Wine 240ml", getContext());
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
                signature = new File(getContext().getCacheDir(), "sign.jpg");
                try {
                    signature.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

//Convert bitmap to byte array
                Bitmap bitmap = signatureBitmap;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos);
                byte[] bitmapdata = bos.toByteArray();

                //write the bytes in file
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(signature);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    Patient patientUpdatedData;

    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {
        if (callNumber == 1 && o.optBoolean("status")) {
            Patient.Question q = new Gson().fromJson(o.optJSONObject("data").toString(), Patient.Question.class);
            if (patientPosition != -1) {
                Camp.Data data = SingleInstance.getInstance().getSelectedCamp();
                data.getPatients().get(patientPosition).setQuestion(q);
                SingleInstance.getInstance().setSelectedCamp(data);

            } else {
                SingleInstance.getInstance().setCurrentQuestionReport(q);
                startActivity(new Intent(getContext(), SubmitQuestionnaireActivity.class));
                finish();
            }
        } else {
            MyApp.popMessage("Error", "Some error while submission, Please retry to submit questionnaire.", getContext());

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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.chk_txt_5) {

        } else if (buttonView.getId() == R.id.chk_txt_4) {

        } else if (buttonView.getId() == R.id.chk_txt_3) {

        } else if (buttonView.getId() == R.id.chk_txt_2) {

        } else if (buttonView.getId() == R.id.chk_txt_1) {

        }
    }

    private boolean firstAns = false, secondAns = false, thirdAns = false, fourthAns = false, fifthAns = false, sixthAns = false;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.radio_6_yes) {
            sixthAns = true;
        } else if (checkedId == R.id.radio_6_no) {
            sixthAns = false;
        } else if (checkedId == R.id.radio_5_yes) {
            fifthAns = true;
        } else if (checkedId == R.id.radio_5_no) {
            fifthAns = false;
        } else if (checkedId == R.id.radio_4_yes) {
            fourthAns = true;
        } else if (checkedId == R.id.radio_4_no) {
            fourthAns = false;
        } else if (checkedId == R.id.radio_3_yes) {
            thirdAns = true;
        } else if (checkedId == R.id.radio_3_no) {
            thirdAns = false;
        } else if (checkedId == R.id.radio_2_yes) {
            secondAns = true;
        } else if (checkedId == R.id.radio_2_no) {
            secondAns = false;
        } else if (checkedId == R.id.radio_1_yes) {
            firstAns = true;
        } else if (checkedId == R.id.radio_1_no) {
            firstAns = false;
        }
    }
}
