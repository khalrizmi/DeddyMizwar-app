package com.example.hamz.dedymizwarapp.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.api.ApiClient;
import com.example.hamz.dedymizwarapp.api.ApiInterface;
import com.example.hamz.dedymizwarapp.model.Profile;
import com.example.hamz.dedymizwarapp.utils.CommonUtils;
import com.squareup.picasso.Picasso;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private String TAG = ProfileActivity.class.getSimpleName();

    Toolbar toolbar;

    ImageView image;
    TextView description;
    ApiInterface apiInterface;
//    Dialog dialog;
    Profile profile;
    LinearLayout container;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);
//        toolbar.setNavigationOnClickListener();

        container = findViewById(R.id.container);
        container.setVisibility(View.INVISIBLE);

        profile = new Profile();

        image = findViewById(R.id.image);
        description = findViewById(R.id.description);


        apiInterface = new ApiClient().getClient().create(ApiInterface.class);

        loadJSON();

    }

    private void loadJSON()
    {
        mProgressDialog = CommonUtils.showLoadingDialog(this);
        apiInterface.profile().enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                String http_status = Integer.toString(response.code()); // get http status

                if (response.isSuccessful()) {
                    profile = response.body();

                    Picasso.get()
                            .load(profile.getImage())
                            .placeholder(R.drawable.progress_animation)
                            .error(R.drawable.noimage)
                            .into(image, new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {
                                    image.setScaleType(ImageView.ScaleType.FIT_XY);
                                }

                                @Override
                                public void onError(Exception e) {
                                    Toast.makeText(ProfileActivity.this, "Failed when loading image "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                    description.setText(profile.getDescription());

                    container.setVisibility(View.VISIBLE);

                    mProgressDialog.cancel();
                }

                Log.d(TAG, "onResponse: HTTP status = " + http_status);
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
