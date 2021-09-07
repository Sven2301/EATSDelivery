package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SeleccionarRestaurante extends AppCompatActivity {

    private TextView selectRest;
    private ListView listRest;

    private String names [] = {"EATS Santa Barbara", "EATS Buenos Aires", "EATS Cartago", "EATS Heredia", "EATS Guanacaste",
                                "EATS Coyol", "EATS Barrio Amon"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_restaurante);

        selectRest = (TextView)findViewById(R.id.SelectRest);
        listRest = (ListView)findViewById(R.id.ListaRest);

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.activity_seleccionar_restaurante, names);
        listRest.setAdapter(adapter);

        listRest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectRest.setText("El restaurante que ha seleccionado es" + listRest.getItemAtPosition(i));
            }
        });

    }
}