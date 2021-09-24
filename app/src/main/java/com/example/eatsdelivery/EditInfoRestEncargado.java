package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Restaurante;

public class EditInfoRestEncargado extends AppCompatActivity {
    Object idEncargado;

    EditText editTextTextPersonName3;
    EditText editTextPhone;
    EditText editTextTextEmailAddress;
    Button btnDireccion;
    Button quit_btn;

    int idRestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info_rest_encargado);

        this.idEncargado = getIntent().getStringExtra("idEncargado");

        this.editTextTextPersonName3 = (EditText) findViewById(R.id.editTextTextPersonName3);
        this.editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        this.editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        this.btnDireccion = (Button) findViewById(R.id.btnDireccion);
        this.quit_btn = (Button) findViewById(R.id.quit_btn);

        this.idRestaurante = MenuEncargado.getRestaurantID(this, idEncargado.toString());

        quit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeResInfo();
                //Intent next = new Intent(view.getContext(), MenuEncargado.class);
                //next.putExtra("userID", idEncargado.toString());
                //startActivity(next);
            }
        });

        btnDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeResInfo();
                Intent next = new Intent(view.getContext(), EditRestDirectionEncargado.class);
                next.putExtra("idRestaurante", idRestaurante);
                startActivity(next);
            }
        });
    }

    public boolean isEmpty() {
        return (editTextTextPersonName3.getText().toString().equals("") &&
                editTextPhone.getText().toString().equals("") &&
                editTextTextEmailAddress.getText().toString().equals(""));
    }

    public void changeResInfo() {
        Toast.makeText(getApplicationContext(), String.valueOf(this.idRestaurante), Toast.LENGTH_SHORT).show();
        if (this.idRestaurante != -1 && !isEmpty()) {
            Restaurante restaurante = new Restaurante();
            restaurante.setNombre(editTextTextPersonName3.getText().toString());
            restaurante.setTelefono(editTextPhone.getText().toString());
            restaurante.setCorreo(editTextTextEmailAddress.getText().toString());
            Model model = new Model();
            model.updateResInfo(this, restaurante, String.valueOf(idRestaurante));
            Toast.makeText(getApplicationContext(), "Informacion actualizada", Toast.LENGTH_SHORT).show();
        }
    }




}