package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddRestGerente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rest_gerente);
    }

    public void addEncargado(View view){
        Intent next = new Intent(this, SelectEncargadoRest.class);
        startActivity(next);
    }

}