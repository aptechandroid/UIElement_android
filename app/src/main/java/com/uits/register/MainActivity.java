package com.uits.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.uits.register.model.User;
import com.uits.register.sqliteDB.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private Button mBtnLoginPage;
    // inwoki
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DatabaseHelper(this);

        mBtnLoginPage = findViewById(R.id.btnLogin);

        mBtnLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });

        for (User us :
                mDBHelper.getAllUser()) {

            Log.d("xxx", "onCreate: "+us.getUserName());
        }
    }

    public void openSignupPage(View view) {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }

}
