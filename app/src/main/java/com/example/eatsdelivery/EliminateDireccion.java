package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EliminateDireccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminate_direccion);

        Object direcName = getIntent().getStringExtra("direcName");
        Object direcID = getIntent().getStringExtra("direcID");
        Object direcDescrip = getIntent().getStringExtra("direcDescrip");
        TextView detalles = (TextView) findViewById(R.id.detalles_pedidoElim);
        TextView nombreDir = (TextView) findViewById(R.id.name);
        Button eliminate_btn = (Button) findViewById(R.id.eli_dir);
        Button quit_btn = (Button) findViewById(R.id.quit_btn2);

        detalles.setText(direcDescrip.toString());
        nombreDir.setText(direcName.toString());

        eliminate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Agregar más opciones como eliminar de bd
                Intent next = new Intent(getApplicationContext(), MainMenu.class);

                startActivity(next);
            }
        });


    }
}