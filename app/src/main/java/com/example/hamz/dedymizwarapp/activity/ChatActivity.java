package com.example.hamz.dedymizwarapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.helper.PrefManager;

import im.crisp.sdk.Crisp;

public class ChatActivity extends AppCompatActivity {

    Toolbar toolbar;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chat");

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);

        prefManager = new PrefManager(this);
        if (prefManager.getIsLogged())
            Crisp.User.setEmail(prefManager.getEmail().toString());
    }
}
