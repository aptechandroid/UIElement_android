package com.uits.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uits.register.model.User;
import com.uits.register.sqliteDB.DatabaseHelper;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * RegisterActivity
 * <p>
 * Copyright © 2019 UITS CO.,LTD
 * Created PHUQUY on 2019-12-16.
 **/
public class RegisterActivity extends AppCompatActivity {
    private AVLoadingIndicatorView register_loader;
    private TextView registerBtn;
    private FrameLayout blur;

    private DatabaseHelper mDBHelper;

    private AutoCompleteTextView mUserName;
    private AutoCompleteTextView mFirstName;
    private EditText mPassword;
    private EditText mConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        blur = findViewById(R.id.background_blur_register);
        register_loader = findViewById(R.id.register_loader);

        mUserName = findViewById(R.id.userName);
        mFirstName = findViewById(R.id.first_name);
        mPassword = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.re_password);

        mDBHelper = new DatabaseHelper(this);

        registerBtn = findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    User user = new User();
                    user.setUserName(mUserName.getText().toString().trim());
                    user.setFirstName(mFirstName.getText().toString().trim());
                    user.setLastName("Phu Quy");
                    user.setEmail("phuquycntt@gmail.com");
                    user.setPassword(mPassword.getText().toString());
                    user.setNumberPhone("0935 366 007");
                    mDBHelper.insertUser(user);
                }
            }
        });
    }

    private boolean validation() {

        if (TextUtils.isEmpty(mUserName.getText().toString().trim())) {
            Toast.makeText(this,"Vui long nhap ten dang nhap cua ban!",Toast.LENGTH_LONG).show();
            return false;
        }

        if (TextUtils.isEmpty(mFirstName.getText().toString().trim())) {
            Toast.makeText(this,"Vui long nhap ho cua ban!",Toast.LENGTH_LONG).show();
            return false;
        }

        if (TextUtils.isEmpty(mPassword.getText().toString())) {
            Toast.makeText(this,"Vui long nhap mat khau cua ban!",Toast.LENGTH_LONG).show();
            return false;
        }

        if (TextUtils.isEmpty(mConfirmPassword.getText().toString())) {
            Toast.makeText(this,"Vui long nhap lai mat khau cua ban!",Toast.LENGTH_LONG).show();
            return false;
        }

        if (!mPassword.getText().toString().equals(mConfirmPassword.getText().toString())) {
            Toast.makeText(this, "Mật khâu không trùng nhau !", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public void openLoginPage(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
