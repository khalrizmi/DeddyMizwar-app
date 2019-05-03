package com.example.hamz.dedymizwarapp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.adapter.ActivityAdapter;
import com.example.hamz.dedymizwarapp.adapter.MessageAdapter;
import com.example.hamz.dedymizwarapp.api.ApiClient;
import com.example.hamz.dedymizwarapp.api.ApiInterface;
import com.example.hamz.dedymizwarapp.helper.PrefManager;
import com.example.hamz.dedymizwarapp.list.MessageList;
import com.example.hamz.dedymizwarapp.model.Message;
import com.example.hamz.dedymizwarapp.utils.CommonUtils;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageActivity extends AppCompatActivity {

    private String TAG = MessageActivity.class.getSimpleName();

    Toolbar toolbar;
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    ArrayList<Message> messageList;

    EditText textMessage;
    ImageView btnSend;

    PrefManager prefManager;
    ApiInterface apiInterface;
//    AlertDialog dialog;

    ProgressDialog mProgressDialog;
    RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        container = findViewById(R.id.container);
        container.setVisibility(View.INVISIBLE);

        prefManager = new PrefManager(this);
        if (!prefManager.getIsLogged())
            startActivity(new Intent(MessageActivity.this, LoginRegisterActivity.class));

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kirim Pesan");

//        Toast.makeText(this,"id "+prefManager.getId() + " token : "+ prefManager.getToken(), Toast.LENGTH_LONG).show();

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);

        messageList = new ArrayList<>();

//        messageList.add(new Message(1, 4, 1, "Bagaimana cara mengundang Anda ke acara leadership yang akan kami adakan september 2018?"));
//        messageList.add(new Message(1, 4, 1, "Mohon dijawab, terima kasih."));

        recyclerView = findViewById(R.id.recyclerview);
        messageAdapter = new MessageAdapter(this, messageList);
        LinearLayoutManager linear = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linear);
        recyclerView.setAdapter(messageAdapter);

        apiInterface = new ApiClient().getClient().create(ApiInterface.class);
//        dialog = new SpotsDialog(this);

        btnSend = findViewById(R.id.button_chatbox_send);
        textMessage = findViewById(R.id.edittext_chatbox);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senMessage();
            }
        });

        loadJSON();
    }

    private void loadJSON() {
        mProgressDialog = CommonUtils.showLoadingDialog(this);

        apiInterface.getMessage("Bearer "+prefManager.getToken()).enqueue(new Callback<MessageList>() {
            @Override
            public void onResponse(Call<MessageList> call, Response<MessageList> response) {
                String http_status = Integer.toString(response.code()); // get http status

                if (response.isSuccessful()) {
//                    messageList = response.body().getMessages();
                    messageList.clear();
                    messageList.add(new Message(1, 1, 2, "Halo, Selamat Datang dikirim pesan Deddy Mizwar. Ada yang ditanyakan ?"));
                    messageList.add(new Message(1, 1, 2, "Pertanyaan akan dijawab apabila indikator online diatas menyala"));
                    messageList.addAll(response.body().getMessages());
                    messageAdapter = new MessageAdapter(MessageActivity.this, messageList);
                    recyclerView.setAdapter(messageAdapter);
                    recyclerView.scrollToPosition(messageList.size() - 1);

                    container.setVisibility(View.VISIBLE);

                    mProgressDialog.cancel();
                }

                Log.d(TAG, "onResponse: HTTP status = " + http_status);
            }

            @Override
            public void onFailure(Call<MessageList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    private void senMessage() {

        mProgressDialog = CommonUtils.showLoadingDialog(this);
        String message = textMessage.getText().toString();
        apiInterface.sendMessage("Bearer "+prefManager.getToken(), message).enqueue(new Callback<MessageList>() {
            @Override
            public void onResponse(Call<MessageList> call, Response<MessageList> response) {
                String http_status = Integer.toString(response.code()); // get http status

                if (response.isSuccessful()) {

                    textMessage.setText("");
                    loadJSON();
                    mProgressDialog.cancel();
                }

                Log.d(TAG, "onResponse: HTTP status = " + http_status);
            }

            @Override
            public void onFailure(Call<MessageList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
