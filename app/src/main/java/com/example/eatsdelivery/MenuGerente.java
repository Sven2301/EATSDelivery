package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuGerente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gerente);

    }



    public void addRest(View view){
        Object  gerenteid = getIntent().getStringExtra("userID");
        Intent next = new Intent(this, SelectEncargadoRest.class);
        next.putExtra("gerente", gerenteid.toString());
        startActivity(next);
    }
    public void seeDelRequests(View view){
        Intent next = new Intent(this, RequestManageGerente.class);
        startActivity(next);
    }
    public void seeRests(View view){
        Intent next = new Intent(this, RestListGerente.class);
        startActivity(next);
    }
    public void quit(View view){
        Intent next = new Intent(this, MainActivity.class);
        startActivity(next);
    }
}