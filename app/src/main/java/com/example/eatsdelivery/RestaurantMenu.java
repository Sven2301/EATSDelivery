package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Plato;


import java.util.ArrayList;

public class RestaurantMenu extends AppCompatActivity {

    ListView listView;
    private Model model = new Model();
    TempCart cart;
    Button verCarrito;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurant_menu);
        verCarrito = (Button) findViewById(R.id.verCarrito);
        Object restId = getIntent().getStringExtra("idRest");
        Object clientID =  getIntent().getStringExtra("clientID");

        listView = findViewById(R.id.listViewRests);

        // Iguala carrito
        cart = getIntent().getParcelableExtra("cart");

        Object  idRest = getIntent().getStringExtra("idRest");

        // Create data
        ArrayList<Plato> platos = new ArrayList<>();

        // Agregar select
        Cursor cursor = model.selectProductosXRestaurante(this, idRest.toString());

        // For para crear la lista de productos
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){

            Plato plato = new Plato();

            int index;

            index = cursor.getColumnIndexOrThrow("id");
            plato.setPlatoID(String.valueOf(cursor.getInt(index)));
            index = cursor.getColumnIndexOrThrow("Nombre");
            plato.setNombre(cursor.getString(index));
            index = cursor.getColumnIndexOrThrow("Costo");
            plato.setCosto(String.valueOf(cursor.getInt(index)));
            index = cursor.getColumnIndexOrThrow("Descripcion");
            plato.setDescripcion(cursor.getString(index));
            index = cursor.getColumnIndexOrThrow("ImagenID");
            plato.setImage(cursor.getString(index));

            platos.add(plato);
            cursor.moveToNext();

        }

        // We make custom adapter
        PlatoAdapter platoAdapter = new PlatoAdapter(this,R.layout.list_row, platos);

        //Create adapter
        listView.setAdapter(platoAdapter);

        //Set data
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent next = new Intent(getApplicationContext(), ProductInfo.class);
                next.putExtra("RestID", restId.toString());
                next.putExtra("PlatoID", platos.get(i).getPlatoID());
                next.putExtra("clientID",clientID.toString());
                next.putExtra("carrito", cart);
                startActivity(next);
            }
        });

        verCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // We make custom adapter
                if (!(cart.getPlatos() == null)) {
                    Intent next = new Intent(getApplicationContext(), Cart.class);
                    next.putExtra("clientID",clientID.toString());
                    next.putExtra("carrito", cart);
                    next.putExtra("RestID", restId.toString());
                    startActivity(next);

                }
                else{
                    Toast.makeText(RestaurantMenu.this,"Tu carrito esta vacio",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void seeCart(View view){

        Intent next = new Intent(this, Cart.class);
        startActivity(next);
    }
}