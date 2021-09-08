package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RestaurantList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
    }

    public void quit(View view){

        Intent next = new Intent(this, MainMenu.class);
        startActivity(next);
    }

    public void seeRestaurant(View view){

        Intent next = new Intent(this, RestaurantMenu.class);
        startActivity(next);
    }
}