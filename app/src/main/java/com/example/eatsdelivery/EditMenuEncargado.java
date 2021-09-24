package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Plato;

import java.util.ArrayList;

public class EditMenuEncargado extends AppCompatActivity {
    Object idEncargado;

    ListView listaProductosEncargado;
    Button add_product;

    int idRes;
    ArrayList<Plato> platos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu_encargado);

        this.idEncargado = getIntent().getStringExtra("idEncargado");

        this.listaProductosEncargado = (ListView) findViewById(R.id.listProductsE);
        this.add_product = (Button) findViewById(R.id.add_product);

        Model model = new Model();
        this.idRes = MenuEncargado.getRestaurantID(this, idEncargado.toString());

        System.out.println(idRes);
        Cursor cursor = model.selectProductosXRestaurante(this, String.valueOf(idRes));
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

        PlatoAdapter platoAdapter = new PlatoAdapter(getApplicationContext(),R.layout.list_row, platos);
        listaProductosEncargado.setAdapter(platoAdapter);

        listaProductosEncargado.setClickable(true);

        listaProductosEncargado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent next = new Intent(view.getContext(), EditProductEncargado.class);
                next.putExtra("PlatoID", platos.get(i).getPlatoID());
                next.putExtra("RestID", String.valueOf(idRes));
                startActivity(next);
            }
        });

        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProductWindow(view);
            }
        });
    }

    public void addProductWindow(View view){
        Intent next = new Intent(this, AddDishEncargado.class);
        next.putExtra("idRes", String.valueOf(idRes));
        startActivity(next);
    }



}