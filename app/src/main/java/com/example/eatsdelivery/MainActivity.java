package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.Restaurante;
import com.example.eatsdelivery.SQLite.Tables.Tarjeta;
import com.example.eatsdelivery.SQLite.Tables.TipoDeAcceso;
import com.example.eatsdelivery.SQLite.Tables.Usuario;


public class MainActivity extends AppCompatActivity{

    public static Usuario userChecked = null;
    private EditText username;
    private EditText password;
    public Button btn_login, btn_register;
    public static Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username_log);
        password =  (EditText) findViewById(R.id.password_log);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);

        //createDB();
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

                        //Iguala usuario general
                        userChecked = checker;
                        //Detecta si el usuario es un cliente
                        if (checker.getTipoAccesoID().equals("1")){
                            Intent next = new Intent(getApplicationContext(), MainMenu.class);
                            startActivity(next);
                        }
                        //Detecta si el usuario es un repartidor
                        if (checker.getTipoAccesoID().equals("2")){
                            Intent next = new Intent(getApplicationContext(), SelectOrdersDriver.class);
                            startActivity(next);
                        }
                        //Detecta si el usuario es un gerente
                        if (checker.getTipoAccesoID().equals("3")){
                            Intent next = new Intent(getApplicationContext(), MenuGerente.class);
                            startActivity(next);
                        }
                        //Detecta si el usuario es un encargado
                        if (checker.getTipoAccesoID().equals("4")){
                            Intent next = new Intent(getApplicationContext(), MenuEncargado.class);
                            startActivity(next);
                        }

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
                Intent next = new Intent(view.getContext(), AddPaymentMethodActivity.class);
                startActivity(next);
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

        //Agregar cliente 1
        Tarjeta card = new Tarjeta();
        card.setCcv("123");
        card.setFechaVencimiento("20/10/24");
        card.setNombrePropietario("Thorfinn");
        card.setNumero("123456789");
        int statusClient1 = model.insertTarjeta(this, card);

        Usuario user = new Usuario();
        user.setNombre("Thorfinn");
        user.setApellido("Mora");
        user.setUsuario("Thor");
        user.setContrasenha("1234");
        user.setCorreo("thorfinn@fakemail.com");
        user.setTelefono("85769213");
        user.setTipoAccesoID("1");
        user.setTarjetaID("1");
        model.insertUsuario(this, user);


        //Agregar cliente 2
        Tarjeta card2 = new Tarjeta();
        card2.setCcv("222");
        card2.setFechaVencimiento("23/10/23");
        card2.setNombrePropietario("Arthur Shelby");
        card2.setNumero("987654321");
        int statusClient2 = model.insertTarjeta(this, card2);

        Usuario user2 = new Usuario();
        user2.setNombre("Arthur");
        user2.setApellido("Shelby");
        user2.setUsuario("arthur");
        user2.setContrasenha("1234");
        user2.setCorreo("arthShell@gmail.com");
        user2.setTelefono("8888696");
        user2.setTipoAccesoID("1");
        user2.setTarjetaID("2");
        model.insertUsuario(this, user2);


        //Agregar cliente 3
        Tarjeta card3 = new Tarjeta();
        card3.setCcv("475");
        card3.setFechaVencimiento("20/09/22");
        card3.setNombrePropietario("Nathan Stockton");
        card3.setNumero("6969656521");
        int statusClient3 = model.insertTarjeta(this, card2);

        Usuario user3 = new Usuario();
        user3.setNombre("Nathan");
        user3.setApellido("Stockton");
        user3.setUsuario("nathanstock");
        user3.setContrasenha("1234");
        user3.setCorreo("nathan@gmail.com");
        user3.setTelefono("8787556933");
        user3.setTipoAccesoID("1");
        user3.setTarjetaID("3");
        model.insertUsuario(this, user3);

        //Agregar cliente 4
        Tarjeta card4 = new Tarjeta();
        card4.setCcv("255");
        card4.setFechaVencimiento("12/09/26");
        card4.setNombrePropietario("Thomas Shelby");
        card4.setNumero("56565622114");
        int statusClient4 = model.insertTarjeta(this, card4);

        Usuario user4 = new Usuario();
        user4.setNombre("Thomas");
        user4.setApellido("Shelby");
        user4.setUsuario("thomas");
        user4.setContrasenha("1234");
        user4.setCorreo("thom@gmail.com");
        user4.setTelefono("777788855");
        user4.setTipoAccesoID("1");
        user4.setTarjetaID("4");
        model.insertUsuario(this, user4);



        // Agregar direcciones para restaurante

        Direccion dirRest1 = new Direccion();
        dirRest1.setNombre("EATS San Joaquin");
        dirRest1.setDescripcion("Frente al restaurante Max Pollos");

        Direccion dirRest2 = new Direccion();
        dirRest2.setNombre("EATS Paraíso");
        dirRest2.setDescripcion("Frente al costado este del Tecnológico de Costa Rica");


        Direccion dirRest3 = new Direccion();
        dirRest3.setNombre("EATS Heredia Centro");
        dirRest3.setDescripcion("Esquina donde estaba el antiguo Testy");

        Direccion dirRest4 = new Direccion();
        dirRest4.setNombre("EATS Puntarenas");
        dirRest4.setDescripcion("Frente al restaurante doña Leda.");

        Direccion dirRest5 = new Direccion();
        dirRest5.setNombre("EATS San Rafael de Heredia");
        dirRest5.setDescripcion("100 mts este del bar restaurante Barracos.");

        Direccion dirRest6 = new Direccion();
        dirRest6.setNombre("EATS Coyol");
        dirRest6.setDescripcion("100 mts sur del hotel de paso Eros, carretera Coyol.");

        //Agrega direcciones
        model.insertDireccion(this, dirRest1);
        model.insertDireccion(this, dirRest2);
        model.insertDireccion(this, dirRest3);
        model.insertDireccion(this, dirRest4);
        model.insertDireccion(this, dirRest5);
        model.insertDireccion(this, dirRest6);

        //Agregar Restaurantes

        Restaurante rest1 = new Restaurante();
        rest1.setNombre(dirRest1.getNombre());
        rest1.setDireccionID("1");
        rest1.setDeshabilitar("1");


        Restaurante rest2 = new Restaurante();
        rest2.setNombre(dirRest2.getNombre());
        rest2.setDireccionID("2");
        rest2.setDeshabilitar("1");

        Restaurante rest3 = new Restaurante();
        rest3.setNombre(dirRest3.getNombre());
        rest3.setDireccionID("3");
        rest3.setDeshabilitar("1");

        Restaurante rest4 = new Restaurante();
        rest4.setNombre(dirRest4.getNombre());
        rest4.setDireccionID("4");
        rest4.setDeshabilitar("1");

        Restaurante rest5 = new Restaurante();
        rest5.setNombre(dirRest5.getNombre());
        rest5.setDireccionID("5");
        rest5.setDeshabilitar("1");

        Restaurante rest6 = new Restaurante();
        rest6.setNombre(dirRest6.getNombre());
        rest6.setDireccionID("6");
        rest6.setDeshabilitar("1");

        model.insertRestaurante(this, rest1);
        model.insertRestaurante(this, rest2);
        model.insertRestaurante(this, rest3);
        model.insertRestaurante(this, rest4);
        model.insertRestaurante(this, rest5);
        model.insertRestaurante(this, rest6);


    }


}