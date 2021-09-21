package com.example.eatsdelivery;

import static android.graphics.Color.alpha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Orden;

import java.util.ArrayList;
import java.util.List;

public class SelectOrdersDriver extends AppCompatActivity {

    private List botones = new ArrayList();
    private ArrayList<Orden> ordenes = new ArrayList();
    private ArrayList<Button> listaBotones = new ArrayList();
    private LinearLayout lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_orders_driver);
        Model model = new Model();

        Cursor cursor = model.selectOrdenesPendientes(this);
        if (cursor.getCount() > 0){
            Toast.makeText(this, "Lista de Ordenes Disponibles", Toast.LENGTH_SHORT).show();
        }
        cursor.moveToFirst();



        while (!cursor.isAfterLast()){

            Orden orden = new Orden();
            int index;

            index = cursor.getColumnIndexOrThrow("RestauranteID");
            orden.setRestauranteID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("DireccionID");
            orden.setDireccionID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("ClienteID");
            orden.setClienteID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("costoTotal");
            orden.setCostoTotal(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("id");
            orden.setOrdenID(String.valueOf(cursor.getInt(index)));

            ordenes.add(orden);
            cursor.moveToNext();
        }

        lista = (LinearLayout) findViewById(R.id.orders_list);

        for (Orden o : ordenes){

            Button button = new Button(this);

            button.setText("Orden " + o.getOrdenID());
            lista.addView(button);
            listaBotones.add(button);

        }



        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = listaBotones.indexOf(v);
                Intent next = new Intent(getApplicationContext(), OrderDetailsDriver.class);
                Orden orden = ordenes.get(index);

                Cursor cur = model.selectDireccion(getApplicationContext(), orden.getDireccionID());
                cur.moveToFirst();
                int idx = cur.getColumnIndexOrThrow("Descripcion");

                Cursor cur2 = model.selectUsuarioID(getApplicationContext(), orden.getClienteID());
                cur2.moveToFirst();
                int idx2 = cur2.getColumnIndexOrThrow("Nombre");

                Cursor cur3 = model.selectRestauranteID(getApplicationContext(), orden.getRestauranteID());
                cur3.moveToFirst();
                int idx3 = cur3.getColumnIndexOrThrow("Nombre");

                Object repartidor = getIntent().getStringExtra("userID");

                String detalles = "\n\nOrden # " + orden.getOrdenID() + "\n\nCliente: " + cur2.getString(idx2) +
                        "\n\nRestaurante: " + cur3.getString(idx3) + "\n\nCosto total: " + orden.getCostoTotal() +
                        "\n\nDireccion de entrega: " + cur.getString(idx);
                next.putExtra("detail", detalles);
                next.putExtra("direc", cur.getString(idx));
                next.putExtra("orden", orden.getOrdenID());
                next.putExtra("repartidor", repartidor.toString());
                startActivity(next);
                }
            };

        for (Button b : listaBotones) {
            b.setOnClickListener(listener);
            b.setBackground(getResources().getDrawable(R.drawable.custom_button));
            b.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        }


    }
}