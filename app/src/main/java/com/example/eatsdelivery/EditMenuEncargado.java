package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EditMenuEncargado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu_encargado);
    }


    public void editMenuRest(View view){
        Intent next = new Intent(this, EditMenuEncargado.class);
        startActivity(next);
    }
    public void updateInfoRest(View view){
        Intent next = new Intent(this, EditInfoRestEncargado.class);
        startActivity(next);
    }
    public void manageOrders(View view){
        Intent next = new Intent(this, OrderManageEncargado.class);
        startActivity(next);
    }
    public void requestDeletetion(View view){
        Intent next = new Intent(this, DeleteRequestEncargado.class);
        startActivity(next);
    }
    public void quit(View view){
        Intent next = new Intent(this, MainMenu.class);
        startActivity(next);
    }
}