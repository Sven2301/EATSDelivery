package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OrderWaitScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_wait_screen);
    }

    public void toMenu(View view){

        Object id = getIntent().getStringExtra("userID");
        Intent next = new Intent(this, MainMenu.class);
        next.putExtra("userID", id.toString());
        startActivity(next);
    }

}