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
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Menu;
import com.example.eatsdelivery.SQLite.Tables.Orden;
import com.example.eatsdelivery.SQLite.Tables.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList extends AppCompatActivity {

    Object userID;
    private List botones = new ArrayList();
    private ArrayList<Restaurante> restaurantes = new ArrayList();
    private ArrayList<Button> listaBotones = new ArrayList();
    private LinearLayout lista;
    TempCart cart = new TempCart();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        userID = getIntent().getStringExtra("userID");

        model = new Model();
        Cursor cursor = model.selectRestaurantes(this);
        if (cursor.getCount() > 0){
            Toast.makeText(this, "Lista de Restaurantes Disponibles", Toast.LENGTH_SHORT).show();
        }
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){

            Restaurante newRest = new Restaurante();
            int index;

            index = cursor.getColumnIndexOrThrow("id");
            newRest.setRestauranteID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("DireccionID");
            newRest.setDireccionID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("Nombre");
            newRest.setNombre(String.valueOf(cursor.getString(index)));

            index = cursor.getColumnIndexOrThrow("Activo");
            newRest.setDeshabilitar(String.valueOf(cursor.getInt(index)));


            restaurantes.add(newRest);
            cursor.moveToNext();
        }

        lista = (LinearLayout) findViewById(R.id.restList_LL);

        for (Restaurante r : restaurantes){

            Button button = new Button(this);
            button.setText(r.getNombre());
            lista.addView(button);
            listaBotones.add(button);

        }


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = listaBotones.indexOf(v);
                Intent next = new Intent(getApplicationContext(), RestaurantMenu.class);
                Restaurante rest = restaurantes.get(index);

                Cursor cur = model.selectRestauranteID(getApplicationContext(), rest.getRestauranteID());

                cur.moveToFirst();
                int nameIndex = cur.getColumnIndexOrThrow("Nombre");
                int dirIndex = cur.getColumnIndexOrThrow("DireccionID");
                int idRestIndex = cur.getColumnIndexOrThrow("id");
                next.putExtra("clientID",userID.toString());
                next.putExtra("nameRest", cur.getString(nameIndex));
                next.putExtra("direcRest", cur.getString(dirIndex));
                next.putExtra("idRest", cur.getString(idRestIndex));
                next.putExtra("cart",cart);
                startActivity(next);
            }
        };

        for (Button b : listaBotones) {
            b.setOnClickListener(listener);
            b.setBackground(getResources().getDrawable(R.drawable.custom_button));
            b.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        }

    }

    public void quit(View view){

        Intent next = new Intent(this, MainMenu.class);
        startActivity(next);
    }


}