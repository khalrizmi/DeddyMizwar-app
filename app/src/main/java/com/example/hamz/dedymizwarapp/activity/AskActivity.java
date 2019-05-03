package com.example.hamz.dedymizwarapp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.api.ApiClient;
import com.example.hamz.dedymizwarapp.api.ApiInterface;
import com.example.hamz.dedymizwarapp.helper.PrefManager;
import com.example.hamz.dedymizwarapp.list.AskList;
import com.example.hamz.dedymizwarapp.utils.CommonUtils;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AskActivity extends AppCompatActivity {

    private String TAG = AskActivity.class.getSimpleName();

    Toolbar toolbar;
    PrefManager prefManager;
    EditText fullName, address, telepon, message;
    Button send;

    ApiInterface apiInterface;
//    AlertDialog dialog;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tanya Deddy");

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);

        apiInterface = new ApiClient().getClient().create(ApiInterface.class);
//        dialog = new SpotsDialog(this);

        fullName = findViewById(R.id.full_name);
        address = findViewById(R.id.address);
        telepon = findViewById(R.id.telepon);
        message = findViewById(R.id.message);
        send = findViewById(R.id.send);

        prefManager = new PrefManager(this);

        if (prefManager.getIsLogged())
            setLabel();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAsk();
            }
        });

    }

    private void sendAsk()
    {
        mProgressDialog = CommonUtils.showLoadingDialog(this);
        String name = fullName.getText().toString();
        String email = address.getText().toString();
        String telpon = telepon.getText().toString();
        String ask = message.getText().toString();
        apiInterface.sendAsk(name, email, telpon, ask).enqueue(new Callback<AskList>() {
            @Override
            public void onResponse(Call<AskList> call, Response<AskList> response) {
                String http_status = Integer.toString(response.code()); // get http status

                if (response.isSuccessful()) {

                    Toast.makeText(AskActivity.this, "Pertanyaan telah dikirim", Toast.LENGTH_SHORT).show();
                    mProgressDialog.cancel();
                    finish();
                }

                Log.d(TAG, "onResponse: HTTP status = " + http_status);
            }

            @Override
            public void onFailure(Call<AskList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setLabel()
    {
        fullName.setText(prefManager.getName());
        address.setText(prefManager.getEmail());

    }
}
