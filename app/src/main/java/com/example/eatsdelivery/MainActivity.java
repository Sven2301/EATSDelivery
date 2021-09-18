package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.TipoDeAcceso;
import com.example.eatsdelivery.SQLite.Tables.Usuario;


public class MainActivity extends AppCompatActivity{

    public static Usuario userChecked = null;
    private EditText username;
    private EditText password;
    public Button btn_login, btn_register;
    public static Model model;
    private Object conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        username = (EditText) findViewById(R.id.username_log);
        password =  (EditText) findViewById(R.id.password_log);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        model = new Model();

        TipoDeAcceso tda = new TipoDeAcceso();
        tda.setDescripcion("Cliente");
        tda.setTipo("1");
        model.insertTipoAcceso(this, tda);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();


                if (user.equals("") && pass.equals("")) {
                    Toast.makeText(MainActivity.this,"No has llenado los espacios",Toast.LENGTH_SHORT).show();
                }
                else{
                    Usuario checker = model.validarUsuario(MainActivity.this, user, pass);

                    if (checker != null){
                        userChecked = checker;
                    }
                    else{
                        Toast.makeText(MainActivity.this,"El usuario no existe. Debes registrarte",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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