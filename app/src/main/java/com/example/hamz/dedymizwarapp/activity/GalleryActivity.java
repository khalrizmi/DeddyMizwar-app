package com.example.hamz.dedymizwarapp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.adapter.ActivityAdapter;
import com.example.hamz.dedymizwarapp.adapter.GalleryAdapter;
import com.example.hamz.dedymizwarapp.api.ApiClient;
import com.example.hamz.dedymizwarapp.api.ApiInterface;
import com.example.hamz.dedymizwarapp.list.GalleryList;
import com.example.hamz.dedymizwarapp.model.Activity;
import com.example.hamz.dedymizwarapp.model.Gallery;
import com.example.hamz.dedymizwarapp.utils.CommonUtils;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryActivity extends AppCompatActivity {

    private String TAG = GalleryActivity.class.getSimpleName();

    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Gallery> galleries;
    GalleryAdapter adapter;
    TextView count;
    ApiInterface apiInterface;
//    AlertDialog dialog;

    LinearLayout container;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        container = findViewById(R.id.container);
        container.setVisibility(View.INVISIBLE);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tentang Aplikasi");

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);

        count = findViewById(R.id.count);

        apiInterface = new ApiClient().getClient().create(ApiInterface.class);

//        dialog = new SpotsDialog(this);

        galleries = new ArrayList<>();
        adapter = new GalleryAdapter(this, galleries);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

//        loadData();
        loadJSON();

    }

    private void loadJSON()
    {
        galleries.clear();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
        apiInterface.Galleries().enqueue(new Callback<GalleryList>() {
            @Override
            public void onResponse(Call<GalleryList> call, Response<GalleryList> response) {
                String http_status = Integer.toString(response.code()); // get http status

                if (response.isSuccessful()) {
                    galleries = response.body().getGalleries();
                    adapter = new GalleryAdapter(GalleryActivity.this, galleries);
                    recyclerView.setAdapter(adapter);

                    count.setText(Integer.toString(galleries.size()));

                    container.setVisibility(View.VISIBLE);

                    mProgressDialog.cancel();
                }

                Log.d(TAG, "onResponse: HTTP status = " + http_status);
            }

            @Override
            public void onFailure(Call<GalleryList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void loadData() {
        galleries.add(new Gallery(1, "http://", "description"));
        galleries.add(new Gallery(1, "http://", "description"));
        galleries.add(new Gallery(1, "http://", "description"));
        galleries.add(new Gallery(1, "http://", "description"));
        galleries.add(new Gallery(1, "http://", "description"));
        galleries.add(new Gallery(1, "http://", "description"));
        galleries.add(new Gallery(1, "http://", "description"));
        galleries.add(new Gallery(1, "http://", "description"));
        galleries.add(new Gallery(1, "http://", "description"));
        galleries.add(new Gallery(1, "http://", "description"));
        galleries.add(new Gallery(1, "http://", "description"));
        galleries.add(new Gallery(1, "http://", "description"));
        galleries.add(new Gallery(1, "http://", "description"));

        adapter = new GalleryAdapter(this, galleries);
        recyclerView.setAdapter(adapter);
        count.setText(""+galleries.size());

    }
}
