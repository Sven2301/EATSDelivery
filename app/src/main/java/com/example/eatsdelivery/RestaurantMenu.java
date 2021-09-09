package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RestaurantMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);
    }

    public void seeProductInfo(View view){

        Intent next = new Intent(this, ProductInfo.class);
        startActivity(next);
    }

    public void seeCart(View view){

        Intent next = new Intent(this, Cart.class);
        startActivity(next);
    }
}