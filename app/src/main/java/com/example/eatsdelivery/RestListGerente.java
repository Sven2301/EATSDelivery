package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Orden;
import com.example.eatsdelivery.SQLite.Tables.Restaurante;

import java.util.ArrayList;

public class RestListGerente extends AppCompatActivity {

    private ArrayList<Restaurante> restaurantes = new ArrayList();
    private ArrayList<Button> listaBotones = new ArrayList();
    private LinearLayout lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_list_gerente);

        Object gerenteid = getIntent().getStringExtra("gerente");
        Model model = new Model();
        Cursor cursor = model.selectRestaurantes(getApplicationContext());

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            Restaurante rest = new Restaurante();
            int index;

            index = cursor.getColumnIndexOrThrow("id");
            rest.setRestauranteID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("DireccionID");
            rest.setDireccionID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("Nombre");
            rest.setNombre(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("UsuarioID");
            rest.setEncargadoID(String.valueOf(cursor.getString(index)));

            index = cursor.getColumnIndexOrThrow("Telefono");
            rest.setTelefono(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Correo");
            rest.setCorreo(cursor.getString(index));

            restaurantes.add(rest);
            cursor.moveToNext();
        }

        lista = (LinearLayout) findViewById(R.id.lista_rest_ger);

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
                Intent next = new Intent(getApplicationContext(), RestInfoGerente.class);
                Restaurante rest = restaurantes.get(index);

                Cursor cur = model.selectDireccion(getApplicationContext(), rest.getDireccionID());
                cur.moveToFirst();
                int idx = cur.getColumnIndexOrThrow("Descripcion");

                Cursor cur2 = model.selectUsuarioID(getApplicationContext(), rest.getEncargadoID());
                cur2.moveToFirst();
                int idx2 = cur2.getColumnIndexOrThrow("Nombre");
                int idx3 = cur2.getColumnIndexOrThrow("Apellido");

                next.putExtra("name", rest.getNombre());
                next.putExtra("phone", rest.getTelefono());
                next.putExtra("email", rest.getCorreo());
                next.putExtra("nameEnc", cur2.getString(idx2));
                next.putExtra("lastEnc", cur2.getString(idx3));
                next.putExtra("direc", cur.getString(idx));
                next.putExtra("gerente", gerenteid.toString());
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