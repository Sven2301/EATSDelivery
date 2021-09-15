package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.TipoDeAcceso;

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


        Model model = new Model();

        TipoDeAcceso tda = new TipoDeAcceso();
        tda.setTipoAccesoID("1");
        tda.setDescripcion("Cliente");
        tda.setTipo("1");
        int resInsert = model.insertTipoAcceso(this, tda);

        if(resInsert == 1){
            Toast.makeText(this, "Insercion con exito", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Insercion fallida", Toast.LENGTH_SHORT).show();
        }


        /*
        List data = new ArrayList();
        Cursor cursor = model.getTipoAcceso(this);
        cursor.moveToFirst();
        int count = cursor.getCount();
        try {


            Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
        }
        catch(Exception ex){
            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
        }
        */

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