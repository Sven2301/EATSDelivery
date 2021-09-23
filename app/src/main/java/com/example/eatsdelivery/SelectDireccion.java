package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.eatsdelivery.SQLite.Tables.Direccion;

import java.util.ArrayList;
import java.util.List;

public class SelectDireccion extends AppCompatActivity {

    private List botones = new ArrayList();
    private ArrayList<Direccion> direccions = new ArrayList();
    private ArrayList<Button> listaBotones = new ArrayList();
    private LinearLayout lista;
    Object userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_direccion);
    }
}