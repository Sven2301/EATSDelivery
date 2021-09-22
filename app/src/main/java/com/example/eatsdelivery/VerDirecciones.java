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
import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class VerDirecciones extends AppCompatActivity {

    private List botones = new ArrayList();
    private ArrayList<Direccion> direccions = new ArrayList();
    private ArrayList<Button> listaBotones = new ArrayList();
    private LinearLayout lista;
    Object userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_direcciones);
        model = new Model();

        userID = getIntent().getStringExtra("userID");
        //Variables de datos de direccion si escoge


        //Agregar select de direccionXcliente
        Cursor cursor = model.selectDireccionXCliente(this,userID.toString());
        if (cursor.getCount() > 0){
            Toast.makeText(this, "Direcciones registradas", Toast.LENGTH_SHORT).show();
        }
        cursor.moveToFirst();


        while (!cursor.isAfterLast()){

            Direccion newDir = new Direccion();
            int index;

            index = cursor.getColumnIndexOrThrow("id");
            newDir.setDireccionID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("Nombre");
            newDir.setNombre(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Descripcion");
            newDir.setDescripcion(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Activo");
            newDir.setActivo(String.valueOf(cursor.getInt(index)));


            direccions.add(newDir);
            cursor.moveToNext();
        }

        lista = (LinearLayout) findViewById(R.id.direcccionesLL);

        for (Direccion dir : direccions){

            Button button = new Button(this);

            button.setText(dir.getNombre());
            lista.addView(button);
            listaBotones.add(button);

        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = listaBotones.indexOf(v);
                Intent next = new Intent(getApplicationContext(), EliminateDireccion.class);
                Direccion dir = direccions.get(index);
                // Agregar select
                /*
                Cursor cur = model.selectDireccionXCliente(getApplicationContext(),userID.toString());

                cur.moveToFirst();
                int nameIndex = cur.getColumnIndexOrThrow("Nombre");
                int descripIndex = cur.getColumnIndexOrThrow("Descripcion");
                int idDirIndex = cur.getColumnIndexOrThrow("id");

                 */

                next.putExtra("direcName", dir.getNombre());
                next.putExtra("direcID", dir.getDireccionID());
                next.putExtra("direcDescrip", dir.getDescripcion());
                next.putExtra("user", userID.toString());

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