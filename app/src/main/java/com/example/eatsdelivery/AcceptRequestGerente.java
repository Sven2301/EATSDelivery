package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;

public class AcceptRequestGerente extends AppCompatActivity {

    Model model = new Model();

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
        Object idrest = getIntent().getStringExtra("idrest");
        Intent next = new Intent(getApplicationContext(), MenuGerente.class);
        int res = model.updateRestActive(getApplicationContext(),  "0", idrest.toString());

        if (res == 1){
            Toast.makeText(getApplicationContext(), "Solicitud aceptada con éxito", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(getApplicationContext(), "Hubo un error", Toast.LENGTH_SHORT).show();
        }
        next.putExtra("userID", id.toString());
        startActivity(next);
    }
}