package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.eatsdelivery.SQLite.Tables.Usuario;

public class MainMenu extends AppCompatActivity {

    Object userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        userID = getIntent().getStringExtra("userID");
    }

    public void seeRestaurantList(View view){
        Intent next = new Intent(this, RestaurantList.class);
        next.putExtra("userID",userID.toString());
        startActivity(next);
    }

    public void manageDirections(View view){

        Intent next = new Intent(this, AddDirectionsClient.class);
        next.putExtra("userID",userID.toString());
        startActivity(next);
    }

    public void cancelPedido(View view){
        Intent next = new Intent(this, CancelOrdenList.class);
        next.putExtra("userID",userID.toString());
        startActivity(next);
    }

    public void checkHistory(View view){
        Intent next = new Intent(this, CheckHistoryClient.class);
        next.putExtra("userID",userID.toString());
        startActivity(next);
    }

    public void checkDirections(View view){
        Intent next = new Intent(this, VerDirecciones.class);
        next.putExtra("userID",userID.toString());
        startActivity(next);
    }

    public void quit(View view){
        Intent next = new Intent(this, MainActivity.class);
        startActivity(next);
    }
}