package com.example.hamz.dedymizwarapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.adapter.SocialAdapter;
import com.example.hamz.dedymizwarapp.model.Social;

import java.util.ArrayList;

public class SocialMediaActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Social> socials;
    SocialAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        toolbar = findViewById(R.id.toolbar);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Aktifitas");

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);

        socials = new ArrayList<>();
        adapter = new SocialAdapter(this, socials);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData();
    }

    private void loadData() {
        socials.add(new Social("icon", "social", "link"));
        socials.add(new Social("icon", "social", "link"));
        socials.add(new Social("icon", "social", "link"));

        adapter = new SocialAdapter(this, socials);
        recyclerView.setAdapter(adapter);

    }
}
