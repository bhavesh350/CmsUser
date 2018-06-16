package com.cms.wockhardt.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends CustomActivity {

    private EditText edt_emp_id;
    private EditText edt_password;
    private Spinner spinner_designation;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupUiElements();
        setClick(R.id.btn_login);
    }

    private void setupUiElements() {
        edt_emp_id = findViewById(R.id.edt_emp_id);
        edt_password = findViewById(R.id.edt_password);
        spinner_designation = findViewById(R.id.spinner_designation);
        btn_login = findViewById(R.id.btn_login);

        List<String> categories = new ArrayList<>();
        categories.add("TM");
        categories.add("RM");
        categories.add("ZSM");
        categories.add("SM");
        categories.add("NSM");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.text_spinner);

        // attaching data adapter to spinner
        spinner_designation.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == btn_login) {

            if (edt_emp_id.getText().toString().isEmpty()) {
                edt_emp_id.setError(getString(R.string.err_emp_id));
                return;
            }
            if (edt_password.getText().toString().isEmpty()) {
                edt_password.setError(getString(R.string.err_password));
            }
            startActivity(new Intent(getContext(), MainActivity.class).putExtra("UserType",
                    spinner_designation.getSelectedItemPosition()));
            finish();
        }
    }

    private Context getContext() {
        return LoginActivity.this;
    }


}
