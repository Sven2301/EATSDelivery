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
import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.DireccionXCliente;
import com.example.eatsdelivery.SQLite.Tables.InfoUsuario;
import com.example.eatsdelivery.SQLite.Tables.TipoDeAcceso;
import com.example.eatsdelivery.SQLite.Tables.Usuario;

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

        Direccion dirU1 = new Direccion();
        dirU1.setDescripcion("Heredia, San Josecito de San Rafael. Residencial Villa Sole");
        dirU1.setNombre("Direccion 1");
        dirU1.setDireccionID("Residencial Villa Sole casa #1 de porton negro.");

        Usuario user1 = new Usuario();
        user1.setContrasenna("contra1");
        user1.setTipoAccesoID(tda.getTipoAccesoID());
        user1.setUsuario("revv");
        InfoUsuario infoUsuario1 = new InfoUsuario();
        infoUsuario1.setNombre("Marco Reveiz");
        infoUsuario1.setCorreo("revvace@gmail.com");
        infoUsuario1.setTelefono("85769188");


        DireccionXCliente dirUser1 = new DireccionXCliente();
        dirUser1.setInfoUsuarioID(user1.getUsuarioID());
        dirUser1.setDireccionID(dirU1.getDireccionID());

        Usuario user2 = new Usuario();
        user2.setNombre("Steven Granados");
        user2.setContrasenna("contra1");
        user2.setTipoAccesoID(tda.getTipoAccesoID());
        user2.setUsuario("sven01");
        InfoUsuario infoUsuario2 = new InfoUsuario();
        infoUsuario1.setNombre("Steven Granados");
        infoUsuario1.setCorreo("sven01@gmail.com");
        infoUsuario1.setTelefono("85769122");


        Usuario user3 = new Usuario();
        user3.setNombre("Fish Lopez");
        user3.setContrasenna("contra1");
        user3.setTipoAccesoID(tda.getTipoAccesoID());
        user3.setUsuario("fish");
        InfoUsuario infoUsuario3 = new InfoUsuario();
        infoUsuario1.setNombre("Fish Lopez");
        infoUsuario1.setCorreo("fishlopez@gmail.com");
        infoUsuario1.setTelefono("88885469");

        int resInsert = model.insertTipoAcceso(this, tda);
        if(resInsert == 1){
            Toast.makeText(this, "Insercion con exito", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Insercion fallida", Toast.LENGTH_SHORT).show();
        }

        int resInsert1 = model.insertDireccion(this,dirU1);
        if(resInsert1 == 1){
            Toast.makeText(this, "Insercion con exito1", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Insercion fallida1", Toast.LENGTH_SHORT).show();
        }

        int resInsert2 = model.insertUsuario(this,user1);
        int resInsert2_2 = model.insertInfoUsuario(this,infoUsuario1);
        if(resInsert2 == 1){
            Toast.makeText(this, "Insercion con exito2", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Insercion fallida2", Toast.LENGTH_SHORT).show();
        }

        int resInsert3 = model.insertDireccionXCliente(this,dirUser1);
        if(resInsert3 == 1){
            Toast.makeText(this, "Insercion con exito3", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Insercion fallida3", Toast.LENGTH_SHORT).show();
        }

        int resInsert4 = model.insertUsuario(this,user2);
        int resInsert4_2 = model.insertInfoUsuario(this,infoUsuario2);
        int resInsert5 = model.insertUsuario(this,user3);
        int resInsert5_2 = model.insertInfoUsuario(this,infoUsuario3);

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
        Intent next = new Intent(this, MenuGerente.class);
        startActivity(next);
    }


}