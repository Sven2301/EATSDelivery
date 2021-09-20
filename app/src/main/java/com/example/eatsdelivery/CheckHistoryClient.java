package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CheckHistoryClient extends AppCompatActivity {

    Object userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_history_client);

        userID = getIntent().getStringExtra("userID");

    }

    public void back(View view){

        Intent next = new Intent(this, MainMenu.class);
        startActivity(next);
    }
}