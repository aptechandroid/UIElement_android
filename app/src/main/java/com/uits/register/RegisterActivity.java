package com.uits.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wang.avi.AVLoadingIndicatorView;

/**
 * RegisterActivity
 * <p>
 * Copyright © 2019 UITS CO.,LTD
 * Created PHUQUY on 2019-12-16.
 **/
public class RegisterActivity extends AppCompatActivity {
    AVLoadingIndicatorView register_loader;
    TextView registerBtn;
    FrameLayout blur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        blur = findViewById(R.id.background_blur_register);
        register_loader = findViewById(R.id.register_loader);


        registerBtn = findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blur.setBackgroundResource(R.drawable.button_background);
                register_loader.setVisibility(View.VISIBLE);
                register_loader.show();
            }
        });
    }

    public void openLoginPage(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
