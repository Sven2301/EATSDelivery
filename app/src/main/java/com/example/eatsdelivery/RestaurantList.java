package com.example.eatsdelivery;

import static com.example.eatsdelivery.MainActivity.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Menu;
import com.example.eatsdelivery.SQLite.Tables.Orden;
import com.example.eatsdelivery.SQLite.Tables.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList extends AppCompatActivity {

    Object userID;
    /*
    private List botones = new ArrayList();
    private ArrayList<Restaurante> restaurantes = new ArrayList();
    private ArrayList<Button> listaBotones = new ArrayList();
    private LinearLayout lista;
     */
    ListView listView;
    TempCart cart = new TempCart();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        userID = getIntent().getStringExtra("userID");
        ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();

        model = new Model();
        Cursor cursor = model.selectRestaurantes(this);

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

        //RestListAdapter restListAdapter = new RestListAdapter(this, R.this., restaurantes);

        //listView.setAdapter(restListAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent next = new Intent(getApplicationContext(), RestaurantMenu.class);
            }
        });



    }

    public void quit(View view){

        Intent next = new Intent(this, MainMenu.class);
        startActivity(next);
    }


}