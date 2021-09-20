package com.example.eatsdelivery;

import static com.example.eatsdelivery.MainActivity.model;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class VerDirecciones extends AppCompatActivity {

    private List botones = new ArrayList();
    private ArrayList<Direccion> restaurantes = new ArrayList();
    private ArrayList<Button> listaBotones = new ArrayList();
    private LinearLayout lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_direcciones);
        model = new Model();
        Cursor cursor = model.selectRestaurantes(this);
    }
}