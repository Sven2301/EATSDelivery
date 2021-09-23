package com.example.eatsdelivery;

import static com.example.eatsdelivery.MainActivity.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.Orden;

import java.util.ArrayList;
import java.util.List;

public class CheckHistoryClient extends AppCompatActivity {

    Object userID;
    private List textosView = new ArrayList();
    private ArrayList<Orden> ordenes = new ArrayList();
    private ArrayList<Button> listaBotones = new ArrayList();
    private LinearLayout lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_history_client);
        userID = getIntent().getStringExtra("userID");

        Cursor cursor = model.selectOrdenesXCliente(this,userID.toString());

        if (cursor.getCount() > 0){
            Toast.makeText(this, "Ordenes del cliente", Toast.LENGTH_SHORT).show();
        }
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){

            Orden newOrder = new Orden();
            int index;

            index = cursor.getColumnIndexOrThrow("id");
            newOrder.setOrdenID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("ClienteID");
            newOrder.setClienteID(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("RestauranteID");
            newOrder.setRestauranteID(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("DireccionID");
            newOrder.setDireccionID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("RepartidorID");
            newOrder.setRepartidorID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("RepartidorID");
            newOrder.setRepartidorID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("costoTotal");
            newOrder.setCostoTotal(String.valueOf(cursor.getInt(index)));

            ordenes.add(newOrder);
            cursor.moveToNext();
        }

        lista = (LinearLayout) findViewById(R.id.orderDetailsHis);

        for (Orden o : ordenes){

            Button button = new Button(this);
            button.setText("Orden " + o.getOrdenID());
            lista.addView(button);
            listaBotones.add(button);;

        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = listaBotones.indexOf(v);
                Intent next = new Intent(getApplicationContext(), OrderInfoHistory.class);
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

                String detalles = "\n\nOrden # " + orden.getOrdenID() + "\n\nCliente: " + cur2.getString(idx2) +
                        "\n\nRestaurante: " + cur3.getString(idx3) + "\n\nCosto total: " + orden.getCostoTotal() +
                        "\n\nDireccion de entrega: " + cur.getString(idx);

                next.putExtra("detail", detalles);
                next.putExtra("direc", cur.getString(idx));
                next.putExtra("orden", orden.getOrdenID());
                startActivity(next);
                }
            };

        for (Button b : listaBotones) {
            b.setOnClickListener(listener);
            b.setBackground(getResources().getDrawable(R.drawable.custom_button));
            b.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        }

    }

    public void back(View view){

        Intent next = new Intent(this, MainMenu.class);
        next.putExtra("userID", userID.toString());
        startActivity(next);
    }
}