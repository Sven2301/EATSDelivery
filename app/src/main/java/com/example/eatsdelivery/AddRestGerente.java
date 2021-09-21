package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.Restaurante;
import com.example.eatsdelivery.SQLite.Tables.RestauranteXGerente;

public class AddRestGerente extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rest_gerente);



    }

    public void confirm(View view){
        EditText restName = (EditText) findViewById(R.id.editTextRestName4);
        EditText direccion = (EditText) findViewById(R.id.editTextTextPostalAddress2);
        EditText telefono = (EditText) findViewById(R.id.editTextPhone2);
        EditText correo = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        Model model = new Model();

        Object gerenteid = getIntent().getStringExtra("gerente");
        Object encargadoid = getIntent().getStringExtra("encargado");
        String nombreRest = restName.getText().toString();
        String address = direccion.getText().toString();
        String phone = telefono.getText().toString();
        String email = correo.getText().toString();

        if (nombreRest != "" & address != "" & email != "" & phone != "") {

            Direccion dir = new Direccion();
            dir.setNombre(nombreRest);
            dir.setDescripcion(address);
            model.insertDireccion(getApplicationContext(), dir);

            Cursor curDir = model.selectDireccionNom(getApplicationContext(), nombreRest);
            curDir.moveToFirst();
            int index = curDir.getColumnIndexOrThrow("id");
            String dirId = String.valueOf(curDir.getInt(index));

            Restaurante rest = new Restaurante();
            rest.setNombre(nombreRest);
            rest.setDireccionID(dirId);
            rest.setTelefono(phone);
            rest.setCorreo(email);
            rest.setEncargadoID(encargadoid.toString());
            model.insertRestaurante(getApplicationContext(), rest);

            Cursor curRest = model.selectRestauranteNom(getApplicationContext(), nombreRest);
            curRest.moveToFirst();
            index = curRest.getColumnIndexOrThrow("id");
            String restId = String.valueOf(curRest.getInt(index));


            RestauranteXGerente rexger = new RestauranteXGerente();
            rexger.setUsuarioID(gerenteid.toString());
            rexger.setRestauranteID(restId);
            model.insertRestauranteXGerente(getApplicationContext(), rexger);

            Toast.makeText(getApplicationContext(), "Restaurante agregado con exito", Toast.LENGTH_SHORT).show();
            Intent next = new Intent(this, MenuGerente.class);
            next.putExtra("userID", gerenteid.toString());
            startActivity(next);
        }
        else{
            Toast.makeText(getApplicationContext(), "Debe llenar los espacios.", Toast.LENGTH_LONG).show();
        }
    }

}