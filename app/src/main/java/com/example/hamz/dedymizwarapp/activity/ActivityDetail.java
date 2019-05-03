package com.example.hamz.dedymizwarapp.activity;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.adapter.ActivityAdapter;
import com.example.hamz.dedymizwarapp.api.ApiClient;
import com.example.hamz.dedymizwarapp.api.ApiInterface;
import com.example.hamz.dedymizwarapp.list.ActivityList;
import com.example.hamz.dedymizwarapp.model.Activity;
import com.squareup.picasso.Picasso;

import dmax.dialog.SpotsDialog;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDetail extends AppCompatActivity {

    private String TAG = ActivityDetail.class.getSimpleName();

    Toolbar toolbar;
    AlertDialog dialog;
    ImageView image;
    TextView date, title, description;
    ApiInterface apiInterface;
    int id;

    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        container = findViewById(R.id.container);
//        container.setVisibility(View.INVISIBLE);

        id = getIntent().getIntExtra("id", 0);
        if (id == 0) {
            finish();
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Aktifitas");

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);

        image = findViewById(R.id.image);
        date  = findViewById(R.id.date);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        apiInterface = new ApiClient().getClient().create(ApiInterface.class);


//        dialog = new SpotsDialog(this);

//        loadJSON();


        date.setText(getIntent().getStringExtra("date"));
        title.setText(getIntent().getStringExtra("title"));
        description.setText(getIntent().getStringExtra("desc"));

        image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        Picasso.get()
                .load(getIntent().getStringExtra("image"))
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.noimage)
                .into(image);

        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    private void loadJSON() {
        dialog.show();
        apiInterface.activity(id).enqueue(new Callback<ActivityList>() {
            @Override
            public void onResponse(retrofit2.Call<ActivityList> call, Response<ActivityList> response) {
                String http_status = Integer.toString(response.code()); // get http status

                if (response.isSuccessful()) {

                    Activity activity = response.body().getActivity();
                    date.setText(activity.getDate());
                    title.setText(activity.getTitle());
                    description.setText(activity.getDescription());

                    image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                    Picasso.get()
                            .load(activity.getImage())
                            .placeholder(R.drawable.progress_animation)
                            .error(R.drawable.noimage)
                            .into(image);

                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);

                    container.setVisibility(View.VISIBLE);

                    dialog.dismiss();
                }

                Log.d(TAG, "onResponse: HTTP status = " + http_status);
            }

            @Override
            public void onFailure(retrofit2.Call<ActivityList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


}
