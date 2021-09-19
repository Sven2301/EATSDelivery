package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderDetailsDriver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_driver);
        Object info = getIntent().getStringExtra("info");
        TextView detalles = (TextView) findViewById(R.id.detalles_pedido);
        Button confirm = (Button) findViewById(R.id.realizarPedido_btn);
        detalles.setText(info.toString());

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent next = new Intent(getApplicationContext(), OrderStateDriver.class);
                next.putExtra("info", info.toString());
                startActivity(next);
            }
        });
    }
}