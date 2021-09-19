package com.example.eatsdelivery;

import static com.example.eatsdelivery.MainActivity.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.DireccionXCliente;
import com.example.eatsdelivery.SQLite.Tables.Usuario;

public class AddDirectionsClient extends AppCompatActivity {

    Usuario user;
    private EditText nameDir;
    private EditText descripDir;
    public Button add_dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_directions_client);
        user = MainActivity.userChecked;

        nameDir = (EditText) findViewById(R.id.dir_name1);
        descripDir =  (EditText) findViewById(R.id.descripDir);
        add_dir = (Button) findViewById(R.id.add_dir);

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
                    // Crea la relacion entre direccion y usuario (Falta)
                    DireccionXCliente newDirC = new DireccionXCliente();
                    newDirC.setUsuarioID(user.getUsuarioID());
                    //newDirC.setDireccionID();
                    int statusDirCli = model.insertDireccionXCliente(view.getContext(), newDirC);

                    if (statusDir == 1 && statusDirCli == 1)
                        Toast.makeText(AddDirectionsClient.this,"No se ha insertado correctamente la dirección",Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(AddDirectionsClient.this, "Dirección insertada correctamente", Toast.LENGTH_SHORT).show();
                        Intent next = new Intent(getApplicationContext(), MainMenu.class);
                        startActivity(next);
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