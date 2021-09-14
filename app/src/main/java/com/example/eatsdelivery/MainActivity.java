package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity<button2> extends AppCompatActivity{

    private EditText username;
    private EditText password;
    private Object conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username_log);
        password = findViewById(R.id.password_log);
    }

    public void registrarse(View view){

        Intent next = new Intent(this, RegisterActivity.class);
        startActivity(next);
    }

    public void login(View view){

        Intent next = new Intent(this, MainMenu.class);
        startActivity(next);
    }


}