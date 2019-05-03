package com.example.hamz.dedymizwarapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.hamz.dedymizwarapp.R;

public class OrderDetail extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView tvDate, tvNoTelepon, tvOrderId, tvPulsaText, tvPulsa, tvSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order Detail");

        mToolbar.setNavigationIcon(R.drawable.ic_left_arrow);

        initTextView();
    }

    private void initTextView() {
        tvDate      = findViewById(R.id.tv_date);
        tvNoTelepon = findViewById(R.id.tv_no_telepon);
        tvOrderId   = findViewById(R.id.tv_order_id);
        tvPulsaText = findViewById(R.id.tv_pulsa_text);
        tvPulsa     = findViewById(R.id.tv_pulsa);
        tvSaldo     = findViewById(R.id.tv_saldo);
    }
}
