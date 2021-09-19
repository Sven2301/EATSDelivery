package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Tables.Orden;

import java.util.ArrayList;
import java.util.List;

public class SelectOrdersDriver extends AppCompatActivity {

    private List botones = new ArrayList();
    private List ordenes = new ArrayList();
    private ArrayList<Button> listaBotones = new ArrayList();
    private LinearLayout lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_orders_driver);

        ordenes.add("Pedido 1");
        ordenes.add("Pedido 2");
        ordenes.add("Pedido 3");

        lista = (LinearLayout) findViewById(R.id.orders_list);

        for (Object orden : ordenes){

            Button button = new Button(this);
            button.setText("Nuevo boton");
            lista.addView(button);
            listaBotones.add(button);

        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = listaBotones.indexOf(v);
                Intent next = new Intent(getApplicationContext(), OrderDetailsDriver.class);
                Object info = ordenes.get(index);
                Toast.makeText(getApplicationContext(), info.toString(), Toast.LENGTH_SHORT).show();
                next.putExtra("info", info.toString());
                startActivity(next);
                }
            };

        for (Button b : listaBotones) {
            b.setOnClickListener(listener);
        }
    }
}