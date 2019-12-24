package com.uits.register;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uits.register.adapter.DashBoardAdapter;
import com.uits.register.model.User;
import com.uits.register.sqliteDB.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * DashBoardActivity
 * <p>
 * Copyright © 2019 UITS CO.,LTD
 * Created PHUQUY on 2019-12-24.
 **/
public class DashBoardActivity extends AppCompatActivity {

    private RecyclerView mRecycelUser;
    private List<User> mListData = new ArrayList<>();
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mDBHelper = new DatabaseHelper(this);
        mListData.addAll(mDBHelper.getAllUser());

        mRecycelUser = findViewById(R.id.mRecycelUser);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycelUser.setLayoutManager(linearLayoutManager);

        mRecycelUser.setAdapter(new DashBoardAdapter(this, mListData));

    }
}
