package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditInfoClient extends AppCompatActivity {
    Object userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info_client);

        userID = getIntent().getStringExtra("userID");
    }
}