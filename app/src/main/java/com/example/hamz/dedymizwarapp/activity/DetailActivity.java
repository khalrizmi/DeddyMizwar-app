package com.example.hamz.dedymizwarapp.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.adapter.ViewPagerAdapter;
import com.example.hamz.dedymizwarapp.api.ApiClient;
import com.example.hamz.dedymizwarapp.api.ApiInterface;
import com.example.hamz.dedymizwarapp.list.LocationList;
import com.example.hamz.dedymizwarapp.model.Location;
import com.example.hamz.dedymizwarapp.utils.CommonUtils;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private String TAG = DetailActivity.class.getSimpleName();

    Toolbar toolbar;
    int mapId;
    ApiInterface apiInterface;
//    Dialog dialog;
    Location location;
    ProgressDialog mProgressDialog;

    TextView tvTps, tvDescription;
    ViewPager viewPager;

    RelativeLayout container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        container = findViewById(R.id.container);
        container.setVisibility(View.INVISIBLE);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail TPS");

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);

        apiInterface = new ApiClient().getClient().create(ApiInterface.class);


        tvTps = findViewById(R.id.tv_tps);
        tvDescription = findViewById(R.id.tv_description);
        viewPager = findViewById(R.id.view_pager);

        mapId = getIntent().getIntExtra("id", 0);


        loadJSON();
    }

    private void loadJSON() {
        mProgressDialog = CommonUtils.showLoadingDialog(this);
        apiInterface.singleLocation(mapId).enqueue(new Callback<LocationList>() {
            @Override
            public void onResponse(Call<LocationList> call, Response<LocationList> response) {
                String http_status = Integer.toString(response.code()); // get http status

                if (response.isSuccessful()) {
                    location = response.body().getLocation();
                    tvTps.setText(location.getImageLocationName());
                    tvDescription.setText(location.getDescription());
                    ViewPagerAdapter adapter = new ViewPagerAdapter(DetailActivity.this, location.getImageMaps());
                    viewPager.setAdapter(adapter);
                    container.setVisibility(View.VISIBLE);
                    mProgressDialog.cancel();
                }

                Log.d(TAG, "onResponse: HTTP status = " + http_status);
            }

            @Override
            public void onFailure(Call<LocationList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
