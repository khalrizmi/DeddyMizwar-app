package com.example.hamz.dedymizwarapp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.adapter.GalleryAdapter;
import com.example.hamz.dedymizwarapp.adapter.VideoAdapter;
import com.example.hamz.dedymizwarapp.api.ApiClient;
import com.example.hamz.dedymizwarapp.api.ApiInterface;
import com.example.hamz.dedymizwarapp.list.VideoList;
import com.example.hamz.dedymizwarapp.model.Gallery;
import com.example.hamz.dedymizwarapp.model.Video;
import com.example.hamz.dedymizwarapp.utils.CommonUtils;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity {

    private String TAG = VideoActivity.class.getSimpleName();

    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Video> videos;
    VideoAdapter adapter;
    TextView count;
    ApiInterface apiInterface;
//    AlertDialog dialog;

    ProgressDialog mProgressDialog;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        container = findViewById(R.id.container);
        container.setVisibility(View.INVISIBLE);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Video");

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);

        count = findViewById(R.id.count);

        apiInterface = new ApiClient().getClient().create(ApiInterface.class);
//        dialog = new SpotsDialog(this);

        videos = new ArrayList<>();
        adapter = new VideoAdapter(this, videos);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setNestedScrollingEnabled(false);

//        loadData();
        loadJSON();
    }

    private void loadJSON()
    {
        videos.clear();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
        apiInterface.videos().enqueue(new Callback<VideoList>() {
            @Override
            public void onResponse(Call<VideoList> call, Response<VideoList> response) {
                String http_status = Integer.toString(response.code()); // get http status

                if (response.isSuccessful()) {
                    videos = response.body().getVideos();
                    adapter = new VideoAdapter(VideoActivity.this, videos);
                    recyclerView.setAdapter(adapter);

                    count.setText(Integer.toString(videos.size()));

                    container.setVisibility(View.VISIBLE);

                    mProgressDialog.cancel();
                }

                Log.d(TAG, "onResponse: HTTP status = " + http_status);
            }

            @Override
            public void onFailure(Call<VideoList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void loadData() {
        videos.add(new Video(1, "http://", "description"));
        videos.add(new Video(1, "http://", "description"));
        videos.add(new Video(1, "http://", "description"));

        adapter = new VideoAdapter(this, videos);
        recyclerView.setAdapter(adapter);

        count.setText(""+videos.size());
    }
}
