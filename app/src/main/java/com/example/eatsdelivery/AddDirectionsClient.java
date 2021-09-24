package com.example.eatsdelivery;

import static com.example.eatsdelivery.MainActivity.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.DireccionXCliente;

public class AddDirectionsClient extends AppCompatActivity {

    Object userID;
    private EditText nameDir;
    private EditText descripDir;
    public Button add_dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_directions_client);

        userID = getIntent().getStringExtra("userID");

        nameDir = (EditText) findViewById(R.id.edtTxtNombreDir);
        descripDir =  (EditText) findViewById(R.id.edtTxtDesc);
        add_dir = (Button) findViewById(R.id.btnCambiarDir);

        add_dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameDir.getText().toString();
                String descrip = descripDir.getText().toString();

                if (name.equals("") && descrip.equals("")) {
                    Toast.makeText(AddDirectionsClient.this,"No has llenado los espacios",Toast.LENGTH_SHORT).show();
                }
                else{

                    // Crea la nueva dirección
                    Direccion newDir = new Direccion();
                    newDir.setNombre(name);
                    newDir.setDescripcion(descrip);
                    int statusDir = model.insertDireccion(view.getContext(), newDir);

                    // Obtiene el ID de la direccion que recien fue insertada
                    Cursor cur = model.selectDireccionDesc(getApplicationContext(), descrip);
                    cur.moveToFirst();
                    int index = cur.getColumnIndexOrThrow("id");
                    String dirID = String.valueOf(cur.getInt(index));

                    // Crea la relacion entre direccion y usuario (Falta)
                    DireccionXCliente newDirC = new DireccionXCliente();
                    newDirC.setUsuarioID(userID.toString());
                    newDirC.setDireccionID(dirID);
                    int statusDirCli = model.insertDireccionXCliente(view.getContext(), newDirC);

                    if (statusDir == 1 && statusDirCli == 1) {
                        Toast.makeText(AddDirectionsClient.this, "Se ha insertado correctamente la dirección", Toast.LENGTH_SHORT).show();
                        Intent next = new Intent(getApplicationContext(), MainMenu.class);
                        next.putExtra("userID", userID.toString());
                        startActivity(next);
                    }
                    else {
                        Toast.makeText(AddDirectionsClient.this, "No se ha insertado la direccion hubo un error", Toast.LENGTH_SHORT).show();
                        Intent next2 = new Intent(getApplicationContext(), MainMenu.class);
                        next2.putExtra("userID", userID.toString());
                        startActivity(next2);
                    }
                }
            }
        });

    }

    public void quit(View view){

        Intent next = new Intent(this, MainMenu.class);
        startActivity(next);
    }
}