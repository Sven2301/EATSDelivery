package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuEncargado extends AppCompatActivity {

    Object idEncargado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_encargado);

        Object idEncargado = getIntent().getStringExtra("userID");
    }
    public void editMenuRest(View view){
        Intent next = new Intent(this, EditMenuEncargado.class);
        next.putExtra("idEncargado", idEncargado.toString());
        startActivity(next);
    }
    public void updateInfoRest(View view){
        Intent next = new Intent(this, EditInfoRestEncargado.class);
        next.putExtra("idEncargado", idEncargado.toString());
        startActivity(next);
    }
    public void manageOrders(View view){
        Intent next = new Intent(this, OrderManageEncargado.class);
        next.putExtra("idEncargado", idEncargado.toString());
        startActivity(next);
    }
    public void requestDeletetion(View view){
        Intent next = new Intent(this, DeleteRequestEncargado.class);
        next.putExtra("idEncargado", idEncargado.toString());
        startActivity(next);
    }
    public void quit(View view){
        Intent next = new Intent(this, MainActivity.class);
        next.putExtra("idEncargado", idEncargado.toString());
        startActivity(next);
    }
}