package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;

public class OrderStateDriver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_state_driver);

        Model model = new Model();
        TextView direccion = (TextView) findViewById(R.id.direccion_state);
        TextView state = (TextView) findViewById(R.id.textView16);
        Button entregado_btn = (Button)findViewById(R.id.confirm_order);


        Object info = getIntent().getStringExtra("direc");
        Object ordenid = getIntent().getStringExtra("orden");
        Object repartidor = getIntent().getStringExtra("repartidor");
        direccion.setText("\n\nOrden # " + ordenid.toString() + "\n\n Direccion de entrega: " + info.toString());

        entregado_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model.updateOrdenEnCamino(getApplicationContext(), "2", ordenid.toString());
                state.setText("Entregado");
                Toast.makeText(getApplicationContext(), "Confirmado. Orden entregada.", Toast.LENGTH_SHORT).show();
                Intent next = new Intent(getApplicationContext(), SelectOrdersDriver.class);
                next.putExtra("userID", repartidor.toString());
            }
        });
    }
}