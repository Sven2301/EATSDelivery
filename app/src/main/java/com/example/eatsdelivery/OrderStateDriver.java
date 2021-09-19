package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrderStateDriver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_state_driver);

        TextView direccion = (TextView) findViewById(R.id.direccion_state);
        Object info = getIntent().getStringExtra("info");
        direccion.setText(info.toString());
    }
}