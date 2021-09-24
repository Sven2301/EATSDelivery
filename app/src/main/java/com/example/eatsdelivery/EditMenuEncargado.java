package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EditMenuEncargado extends AppCompatActivity {
    Object idEncargado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu_encargado);
        this.idEncargado = getIntent().getStringExtra("userID");
    }

    public void addProductWindow(View view){
        Intent next = new Intent(this, AddDishEncargado.class);
        startActivity(next);
    }



}