package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RestInfoGerente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_info_gerente);

        EditText nombre = (EditText) findViewById(R.id.editTextTextPersonName6);
        EditText nombreEnc = (EditText) findViewById(R.id.editTextTextPersonName7);
        EditText telefono = (EditText) findViewById(R.id.editTextPhone3);
        EditText correo = (EditText) findViewById(R.id.editTextTextEmailAddress3);
        EditText direccion = (EditText) findViewById(R.id.editTextTextPostalAddress3);

        nombre.setText(getIntent().getStringExtra("name"));
        nombreEnc.setText(getIntent().getStringExtra("nameEnc") + " " +
                getIntent().getStringExtra("lastEnc"));
        telefono.setText(getIntent().getStringExtra("phone"));
        correo.setText(getIntent().getStringExtra("email"));
        direccion.setText(getIntent().getStringExtra("direc"));
    }

    public void back (View view){
        Object gerente = getIntent().getStringExtra("gerente");
        Intent next = new Intent(getApplicationContext(), RestListGerente.class);
        next.putExtra("gerente", gerente.toString());
        startActivity(next);
    }
}