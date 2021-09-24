package com.example.eatsdelivery;

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
import com.example.eatsdelivery.SQLite.Tables.Orden;
import com.example.eatsdelivery.SQLite.Tables.Usuario;

import java.util.ArrayList;

public class SelectEncargadoRest extends AppCompatActivity {


    private ArrayList<Usuario> encargados = new ArrayList();
    private ArrayList<Button> listaBotones = new ArrayList();
    private LinearLayout lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_encargado_rest);

        Model model = new Model();
        Object gerenteid = getIntent().getStringExtra("gerente");
        Cursor cursor = model.selectEncargados(getApplicationContext());

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){

            Usuario encargado = new Usuario();
            int index;

            index = cursor.getColumnIndexOrThrow("TipoAccesoID");
            encargado.setTipoAccesoID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("Nombre");
            encargado.setNombre(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Apellido");
            encargado.setApellido(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("id");
            encargado.setUsuarioID(String.valueOf(cursor.getInt(index)));

            encargados.add(encargado);
            cursor.moveToNext();
        }

        lista = (LinearLayout) findViewById(R.id.lista_encargados);

        for (Usuario u : encargados){

            Button button = new Button(this);

            button.setText(u.getNombre());
            lista.addView(button);
            listaBotones.add(button);

        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = listaBotones.indexOf(v);
                Intent next = new Intent(getApplicationContext(), AddRestGerente.class);
                Usuario encargado = encargados.get(index);
                next.putExtra("gerente", gerenteid.toString());
                next.putExtra("encargado", encargado.getUsuarioID());
                Toast.makeText(getApplicationContext(), "Encargado seleccionado", Toast.LENGTH_SHORT).show();
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