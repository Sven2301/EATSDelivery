package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Direccion;

import java.util.ArrayList;
import java.util.List;

public class SelectDireccion extends AppCompatActivity {

    ListView dirs;
    private LinearLayout lista;
    Object userID;
    TempCart cart;
    Object restID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_direccion);


        cart = getIntent().getParcelableExtra("cart");
        ArrayList<Direccion> direccions = new ArrayList<>();
        userID = getIntent().getStringExtra("userID");
        restID = getIntent().getStringExtra("restId");

        Model model = new Model();

        Cursor cursor = model.selectDireccionXCliente(this,userID.toString());
        dirs = findViewById(R.id.dirsList);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            Direccion dir = new Direccion();
            int index;

            index = cursor.getColumnIndexOrThrow("id");
            dir.setDireccionID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("Descripcion");
            dir.setDescripcion(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("Nombre");
            dir.setNombre(String.valueOf(cursor.getString(index)));




            direccions.add(dir);
            cursor.moveToNext();
        }

        // We make custom adapter
        DireccionListAdapter disAdapter = new DireccionListAdapter(this, R.layout.direccion_list_row, direccions);

        //Create adapter
        dirs.setAdapter(disAdapter);

        //Set data
        dirs.setClickable(true);
        dirs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent next = new Intent(getApplicationContext(), RestaurantMenu.class);
                next.putExtra("cart", cart);
                next.putExtra("idRest", restID.toString());
                next.putExtra("clientID", userID.toString());
                startActivity(next);
            }
        });



    }
}