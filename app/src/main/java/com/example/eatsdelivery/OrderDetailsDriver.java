package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;

public class OrderDetailsDriver extends AppCompatActivity {

    private Model model = new Model();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_driver);

        TextView detalles = (TextView) findViewById(R.id.detalles_pedido);
        Button confirm = (Button) findViewById(R.id.realizarPedido_btn);

        Object info = getIntent().getStringExtra("detail");
        Object direc = getIntent().getStringExtra("direc");
        Object ordenid = getIntent().getStringExtra("orden");
        Object  repartidorid = getIntent().getStringExtra("repartidor");

        detalles.setText(info.toString());

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model.updateOrdenEnCamino(getApplicationContext(), "1", ordenid.toString());
                model.updateOrdenRepartidorID(getApplicationContext(), repartidorid.toString(), ordenid.toString());

                Intent next = new Intent(getApplicationContext(), OrderStateDriver.class);
                next.putExtra("direc", direc.toString());
                next.putExtra("orden", ordenid.toString());
                next.putExtra("repartidor", repartidorid.toString());
                Toast.makeText(getApplicationContext(), "Confirmado", Toast.LENGTH_SHORT).show();
                startActivity(next);
            }
        });
    }
}