package com.example.hamz.dedymizwarapp.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.api.ApiClient;
import com.example.hamz.dedymizwarapp.api.ApiInterface;
import com.example.hamz.dedymizwarapp.list.BannerList;
import com.example.hamz.dedymizwarapp.model.Activity;
import com.example.hamz.dedymizwarapp.model.Banner;
import com.example.hamz.dedymizwarapp.utils.CommonUtils;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    FloatingActionButton profile, activity, gallery, video, question, message, maps, social, information, chat;

    int[] sampleImages = {
            R.drawable.banner,
            R.drawable.banner2,
            R.drawable.banner3
    };

    CarouselView carouselView;
    ApiInterface apiInterface;
    ArrayList<Banner> banners;
    LinearLayout container;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
        container.setVisibility(View.INVISIBLE);


        apiInterface = new ApiClient().getClient().create(ApiInterface.class);

        profile  = findViewById(R.id.fab_profile);
        activity = findViewById(R.id.fab_activity);
        gallery  = findViewById(R.id.fab_gallery);
        video    = findViewById(R.id.fab_video);
        question = findViewById(R.id.fab_question);
        message  = findViewById(R.id.fab_message);
        maps     = findViewById(R.id.fab_maps);
        social   = findViewById(R.id.fab_social);
        information = findViewById(R.id.fab_information);
        chat = findViewById(R.id.fab_chat);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChatActivity.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityActivity.class));
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GalleryActivity.class));
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VideoActivity.class));
            }
        });

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QuestionsActivity.class));
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MessageActivity.class));
            }
        });

        social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SocialMediaActivity.class));
            }
        });

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InformationActivity.class));
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });

//        findViewById(R.id.fab_pulsa).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, PulsaActivity.class));
//            }
//        });

        loadJSON();

        carouselView = findViewById(R.id.carouselView);
//        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        carouselView.setSlideInterval(5000);

    }

    private void loadJSON() {
        mProgressDialog = CommonUtils.showLoadingDialog(this);
        apiInterface.banner().enqueue(new Callback<BannerList>() {
            @Override
            public void onResponse(Call<BannerList> call, Response<BannerList> response) {
                String http_status = Integer.toString(response.code()); // get http status

                if (response.isSuccessful()) {
                    banners = response.body().getBanners();
                    carouselView.setPageCount(banners.size());
                    container.setVisibility(View.VISIBLE);

                    mProgressDialog.cancel();
                }

                Log.d(TAG, "onResponse: HTTP status = " + http_status);
            }

            @Override
            public void onFailure(Call<BannerList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.get()
                    .load(banners.get(position).getImage())
                    .into(imageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
//                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.e("Picasso error : ", e.getMessage());
                            e.printStackTrace();
                        }
                    });
//            imageView.setImageResource(sampleImages[position]);
        }
    };
}
