package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.DireccionXCliente;
import com.example.eatsdelivery.SQLite.Tables.InfoUsuario;
import com.example.eatsdelivery.SQLite.Tables.TipoDeAcceso;
import com.example.eatsdelivery.SQLite.Tables.Usuario;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameClient;
    private EditText usernameClient;
    private EditText numberClient;
    private EditText mailClient;
    private EditText passwordClient;
    private EditText confirmPass;
    private EditText directionClient;
    TipoDeAcceso tda;
    Direccion direccion;
    DireccionXCliente direccionXCliente;
    Usuario usuario;
    InfoUsuario infoUsuario;
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Crea un tipo de acceso
        tda = new TipoDeAcceso();
        tda.setDescripcion("Cliente");
        tda.setTipo("1");
        // Crea un objeto de direccion
        direccion = new Direccion();
        // Crea un direccion x cliente
        direccionXCliente = new DireccionXCliente();
        // Crea un usuario
        usuario = new Usuario();
        // Crea info de usuario
        infoUsuario = new InfoUsuario();

        nameClient = findViewById(R.id.name_reg);
        usernameClient = findViewById(R.id.username_reg);
        numberClient = findViewById(R.id.phone_reg);
        mailClient = findViewById(R.id.email_reg);
        passwordClient = findViewById(R.id.password_reg);
        confirmPass = findViewById(R.id.confpass_reg);
        directionClient = findViewById(R.id.direction_reg);

    }


    public void addPaymentMethod(View view){

        Intent next = new Intent(this, AddPaymentMethodActivity.class);
        startActivity(next);
    }


    public EditText getNameClient() {
        return nameClient;
    }

    public EditText getUsernameClient() {
        return usernameClient;
    }

    public EditText getNumberClient() {
        return numberClient;
    }

    public EditText getMailClient() {
        return mailClient;
    }

    public EditText getPasswordClient() {
        return passwordClient;
    }

    public EditText getConfirmPass() {
        return confirmPass;
    }

    public EditText getDirectionClient() {
        return directionClient;
    }
}