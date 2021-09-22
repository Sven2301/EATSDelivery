package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;

public class EliminateDireccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminate_direccion);

        Model model = new Model();
        Object userid = getIntent().getStringExtra("user");
        Object direcName = getIntent().getStringExtra("direcName");
        Object direcID = getIntent().getStringExtra("direcID");
        Object direcDescrip = getIntent().getStringExtra("direcDescrip");
        TextView detalles = (TextView) findViewById(R.id.detalles_pedidoElim);
        TextView nombreDir = (TextView) findViewById(R.id.name);
        Button eliminate_btn = (Button) findViewById(R.id.eli_dir);


        detalles.setText(direcDescrip.toString());
        nombreDir.setText(direcName.toString());

        eliminate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Agregar más opciones como eliminar de bd
                int status = model.updateDirActive(getApplicationContext(), "0", direcID.toString());
                if (status == 1){
                    Toast.makeText(getApplicationContext(), "Direccion eliminada con exito", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Hubo un error", Toast.LENGTH_SHORT).show();
                }
                Intent next = new Intent(getApplicationContext(), MainMenu.class);
                next.putExtra("userID", userid.toString());
                startActivity(next);
            }
        });


    }
}