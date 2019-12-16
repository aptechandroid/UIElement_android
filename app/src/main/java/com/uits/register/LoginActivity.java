package com.uits.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_loader = findViewById(R.id.login_loader);
        login = findViewById(R.id.sign_in_login);
        blur = findViewById(R.id.background_blur_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blur.setBackgroundResource(R.drawable.button_background);
                login_loader.setVisibility(View.VISIBLE);
                login_loader.show();
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

    public void openSignupPage(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }


    public void openLoginPage(View view) {

    }
}
