package com.uits.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.uits.register.model.User;
import com.uits.register.sqliteDB.DatabaseHelper;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * LoginActivity
 * <p>
 * Copyright © 2019 UITS CO.,LTD
 * Created PHUQUY on 2019-12-16.
 **/
public class LoginActivity extends AppCompatActivity {

    private TextView login;
    private ImageView googleBtn;
    private ImageView facebookBtn;
    private FrameLayout blur;
    private AVLoadingIndicatorView login_loader;

    private DatabaseHelper mDBHelper;

    private AppCompatEditText mPassword;
    private AutoCompleteTextView mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDBHelper = new DatabaseHelper(this);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);

        login_loader = findViewById(R.id.login_loader);
        login = findViewById(R.id.sign_in_login);
        blur = findViewById(R.id.background_blur_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    Toast.makeText(LoginActivity.this, "Login thanh cong", Toast.LENGTH_LONG).show();
                }
            }
        });

        googleBtn = findViewById(R.id.google_sign_btn);
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_loader.hide();
                blur.setBackgroundResource(R.drawable.button_background);
                login_loader.setVisibility(View.VISIBLE);
                login_loader.show();
            }
        });


        facebookBtn = findViewById(R.id.facebook_signin_btn);
        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_loader.hide();
                blur.setBackgroundResource(R.drawable.button_background);
                login_loader.setVisibility(View.VISIBLE);
                login_loader.show();
            }
        });


    }

    private boolean validation() {

        if (TextUtils.isEmpty(mEmail.getText().toString().trim())) {
            Toast.makeText(this, "Vui long nhap ten dang nhap hoac email cua ban!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (TextUtils.isEmpty(mPassword.getText().toString().trim())) {
            Toast.makeText(this, "Vui long nhap mat khau cua ban!", Toast.LENGTH_LONG).show();
            return false;
        }

        User user = mDBHelper.getUser(mEmail.getText().toString().trim());
        if (user != null) {
            if (!user.getPassword().equals(mPassword.getText().toString())) {
                Toast.makeText(this, "Mat Khau khong dung!", Toast.LENGTH_LONG).show();
                return false;
            }
        }

        return true;
    }

    public void openSignupPage(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }


    public void openLoginPage(View view) {

    }
}
