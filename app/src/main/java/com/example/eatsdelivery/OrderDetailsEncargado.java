package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.eatsdelivery.SQLite.Model;

public class OrderDetailsEncargado extends AppCompatActivity {
    Object info;
    Object direc;
    Object ordenid;
    Object idEncargado;

    TextView detalles_pedido2;
    Button cancel_order;
    Button devol_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_encargado);

        this.detalles_pedido2 = (TextView) findViewById(R.id.detalles_pedido2);
        this.cancel_order = (Button) findViewById(R.id.cancel_order);
        this.devol_order = (Button) findViewById(R.id.devol_order);

        this.info = getIntent().getStringExtra("detail");
        this.direc = getIntent().getStringExtra("direc");
        this.ordenid = getIntent().getStringExtra("orden");
        this.idEncargado = getIntent().getStringExtra("idEncargado");

        detalles_pedido2.setText(info.toString());

        cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarOrden();
            }
        });

        devol_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarOrden();
            }
        });
    }

    private void eliminarOrden(){
        Model model = new Model();
        model.updateOrdenActiva(this, "0", ordenid.toString());
    }

    
}