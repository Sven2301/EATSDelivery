package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AcceptRequestGerente extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_request_gerente);

        EditText info = (EditText) findViewById(R.id.editTextTextRestInfo);


        String nombreRest = getIntent().getStringExtra("name");
        String nombreEnc = getIntent().getStringExtra("nameEnc") + " " + getIntent().getStringExtra("lastEnc");
        String telefono = getIntent().getStringExtra("phone");
        String correo = getIntent().getStringExtra("email");
        String direccion = getIntent().getStringExtra("direc");
        String detail = "\n\n" + nombreRest + "\n\nEncargado: " + nombreEnc +
                "\n\nDireccion: " + direccion + "\n\nTelefono: " + telefono +
                "\n\nCorreo: " + correo;

        info.setText(detail);
    }

    public void confirmRequest(View view){

        Object id = getIntent().getStringExtra("gerente");
        Intent next = new Intent(getApplicationContext(), MenuGerente.class);
        next.putExtra("userID", id.toString());
        startActivity(next);
    }
}