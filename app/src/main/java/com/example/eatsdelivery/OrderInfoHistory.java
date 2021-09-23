package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrderInfoHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info_history);

        TextView txtInfo = (TextView) findViewById(R.id.orderinfohistory);
        String detail = getIntent().getStringExtra("detail");
        txtInfo.setText(detail);
    }
}