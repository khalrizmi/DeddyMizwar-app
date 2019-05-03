package com.example.hamz.dedymizwarapp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.adapter.ActivityAdapter;
import com.example.hamz.dedymizwarapp.api.ApiClient;
import com.example.hamz.dedymizwarapp.api.ApiInterface;
import com.example.hamz.dedymizwarapp.list.ActivityList;
import com.example.hamz.dedymizwarapp.model.Activity;
import com.example.hamz.dedymizwarapp.utils.CommonUtils;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityActivity extends AppCompatActivity {

    private String TAG = ActivityActivity.class.getSimpleName();

    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Activity> activities;
    ActivityAdapter adapter;
    ApiInterface apiInterface;

    LinearLayout container;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        container = findViewById(R.id.container);
        container.setVisibility(View.INVISIBLE);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Aktifitas");

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);


        apiInterface = new ApiClient().getClient().create(ApiInterface.class);

        activities = new ArrayList<>();
        adapter = new ActivityAdapter(this, activities);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

//        loadData();
        loadJSON();
    }

    private void loadJSON() {
        mProgressDialog = CommonUtils.showLoadingDialog(this);
        activities.clear();

        apiInterface.listActivities().enqueue(new Callback<ActivityList>() {
            @Override
            public void onResponse(retrofit2.Call<ActivityList> call, Response<ActivityList> response) {
                String http_status = Integer.toString(response.code()); // get http status

                if (response.isSuccessful()) {
                    activities = response.body().getActivities();
                    adapter = new ActivityAdapter(ActivityActivity.this, activities);
                    recyclerView.setAdapter(adapter);

                    mProgressDialog.cancel();
                    container.setVisibility(View.VISIBLE);
                }

                Log.d(TAG, "onResponse: HTTP status = " + http_status);
            }

            @Override
            public void onFailure(retrofit2.Call<ActivityList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void loadData() {
        activities.add(new Activity(1, "http://", "Lorem", "lorem", "lorem"));
        activities.add(new Activity(1, "http://", "Lorem", "lorem", "lorem"));
        activities.add(new Activity(1, "http://", "Lorem", "lorem", "lorem"));
        activities.add(new Activity(1, "http://", "Lorem", "lorem", "lorem"));
        activities.add(new Activity(1, "http://", "Lorem", "lorem", "lorem"));
        activities.add(new Activity(1, "http://", "Lorem", "lorem", "lorem"));
        activities.add(new Activity(1, "http://", "Lorem", "lorem", "lorem"));
        activities.add(new Activity(1, "http://", "Lorem", "lorem", "lorem"));
        activities.add(new Activity(1, "http://", "Lorem", "lorem", "lorem"));

        adapter = new ActivityAdapter(this, activities);
        recyclerView.setAdapter(adapter);
    }
}
