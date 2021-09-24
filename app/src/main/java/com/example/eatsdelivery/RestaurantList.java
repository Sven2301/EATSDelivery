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
import android.widget.SearchView;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Menu;
import com.example.eatsdelivery.SQLite.Tables.Orden;
import com.example.eatsdelivery.SQLite.Tables.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList extends AppCompatActivity {

    Object userID;
    ListView listView;
    SearchView search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        userID = getIntent().getStringExtra("userID");
        ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();
        listView = findViewById(R.id.rest_lisview);
        search = findViewById(R.id.searchView);

        model = new Model();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                restaurantes.clear();
                Cursor cursor = model.selectRestauranteSearch(getApplicationContext(), s);

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

                    index = cursor.getColumnIndexOrThrow("ImagenID");
                    newRest.setImageID(cursor.getString(index));


                    restaurantes.add(newRest);
                    cursor.moveToNext();
                }

                RestListAdapter restListAdapter = new RestListAdapter(getApplicationContext(), R.layout.list_row, restaurantes);
                listView.setAdapter(restListAdapter);
                listView.setClickable(true);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent next = new Intent(getApplicationContext(), RestaurantMenu.class);
                        next.putExtra("clientID",userID.toString());
                        TempCart.clearCart();
                        next.putExtra("idRest", restaurantes.get(i).getRestauranteID());
                        startActivity(next);
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if (s.equals("")){
                    restaurantes.clear();
                    Cursor cursor = model.selectRestaurantes(getApplicationContext());

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

                        index = cursor.getColumnIndexOrThrow("ImagenID");
                        newRest.setImageID(cursor.getString(index));


                        restaurantes.add(newRest);
                        cursor.moveToNext();
                    }

                    RestListAdapter restListAdapter = new RestListAdapter(getApplicationContext(), R.layout.list_row, restaurantes);
                    listView.setAdapter(restListAdapter);
                    listView.setClickable(true);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent next = new Intent(getApplicationContext(), RestaurantMenu.class);
                            next.putExtra("clientID",userID.toString());
                            TempCart.clearCart();
                            next.putExtra("idRest", restaurantes.get(i).getRestauranteID());
                            startActivity(next);
                        }
                    });
                }
                return false;
            }
        });

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

            index = cursor.getColumnIndexOrThrow("ImagenID");
            newRest.setImageID(cursor.getString(index));

            restaurantes.add(newRest);
            cursor.moveToNext();
        }

        RestListAdapter restListAdapter = new RestListAdapter(this, R.layout.list_row, restaurantes);
        listView.setAdapter(restListAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent next = new Intent(getApplicationContext(), SelectDireccion.class);
                next.putExtra("clientID",userID.toString());
                if (TempCart.platos != null){
                    TempCart.clearCart();
                }
                next.putExtra("idRest", restaurantes.get(i).getRestauranteID());
                startActivity(next);
            }
        });

    }

    public void quit(View view){
        Intent next = new Intent(this, MainMenu.class);
        startActivity(next);
    }


}