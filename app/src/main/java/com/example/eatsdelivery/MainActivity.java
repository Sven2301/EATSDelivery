package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Tarjeta;
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

        createDB();
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

    public void createDB(){

        model = new Model();

        //AGREGA LOS TIPOS DE ACCESO
        //TIPO ACCESO 1: CLIENTE
        TipoDeAcceso tda = new TipoDeAcceso();
        tda.setDescripcion("Cliente");
        tda.setTipo("1");
        model.insertTipoAcceso(this, tda);

        //TIPO ACCESO 2: REPARTIDOR
        tda.setDescripcion("Repartidor");
        tda.setTipo("2");
        model.insertTipoAcceso(this, tda);

        //TIPO ACCESO 3: GERENTE
        tda.setDescripcion("Gerente");
        tda.setTipo("3");
        model.insertTipoAcceso(this, tda);

        //TIPO ACCESO 4: ENCARGADO
        tda.setDescripcion("Encargado");
        tda.setTipo("4");
        model.insertTipoAcceso(this, tda);

        Tarjeta card = new Tarjeta();
        card.setCcv("123");
        card.setFechaVencimiento("20/10/24");
        card.setNombrePropietario("Thorfinn");
        card.setNumero("123456789");
        int status = model.insertTarjeta(this, card);



        Usuario user = new Usuario();
        user.setNombre("Thorfinn");
        user.setApellido("Mora");
        user.setUsuario("Thor");
        user.setContrasenha("1234");
        user.setCorreo("thorfinn@fakemail.com");
        user.setTelefono("8888888888");
        user.setTipoAccesoID("1");
        user.setTarjetaID("1");
        model.insertUsuario(this, user);
    }


}