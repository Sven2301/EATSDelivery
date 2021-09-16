package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void seeRestaurantList(View view){

        Intent next = new Intent(this, RestaurantList.class);
        startActivity(next);
    }

    public void manageDirections(View view){

        Intent next = new Intent(this, AddDirectionsClient.class);
        startActivity(next);
    }

    public void editClientData(View view){

        Intent next = new Intent(this, EditInfoClient.class);
        startActivity(next);
    }

    public void checkHistory(View view){

        Intent next = new Intent(this, CheckHistoryClient.class);
        startActivity(next);
    }

    public void quit(View view){
        Intent next = new Intent(this, MainMenu.class);
        startActivity(next);
    }
}