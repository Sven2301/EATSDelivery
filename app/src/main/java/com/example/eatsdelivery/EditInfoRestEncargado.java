package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditInfoRestEncargado extends AppCompatActivity {

    Object idEncargado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info_rest_encargado);

        this.idEncargado = getIntent().getStringExtra("userID");
    }
}