package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.Menu;
import com.example.eatsdelivery.SQLite.Tables.Orden;
import com.example.eatsdelivery.SQLite.Tables.Plato;
import com.example.eatsdelivery.SQLite.Tables.Restaurante;
import com.example.eatsdelivery.SQLite.Tables.Tarjeta;
import com.example.eatsdelivery.SQLite.Tables.TipoDeAcceso;
import com.example.eatsdelivery.SQLite.Tables.TipoDeComida;
import com.example.eatsdelivery.SQLite.Tables.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity{

    public static Usuario userChecked = null;
    private EditText username;
    private EditText password;
    public Button btn_login, btn_register;
    public static Model model = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username_log);
        password =  (EditText) findViewById(R.id.password_log);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);


        // Objeto para guardar un dato que permite saber si es la primera vez que se ejcuta la app
        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String exec = preferences.getString("firstTime", "");

        if (exec.equals("false")){
            // La base de datos ya existe entonces no debe crearla otra vez
        }
        else{
            //Si entra aqui significa que es la primera vez entonces debe crear la base de datos.
            //Ademas de guardar el dato que indique la proxima vez que no es la primera ejecucion del programa
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("firstTime", "false");
            editor.commit();
            createDB();
        }


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
                            next.putExtra("userID",checker.getUsuarioID());
                            startActivity(next);

                        }
                        //Detecta si el usuario es un repartidor
                        if (checker.getTipoAccesoID().equals("2")){
                            Intent next = new Intent(getApplicationContext(), SelectOrdersDriver.class);
                            next.putExtra("userID",checker.getUsuarioID());
                            startActivity(next);
                        }
                        //Detecta si el usuario es un gerente
                        if (checker.getTipoAccesoID().equals("3")){
                            Intent next = new Intent(getApplicationContext(), MenuGerente.class);
                            next.putExtra("userID",checker.getUsuarioID());
                            startActivity(next);
                        }
                        //Detecta si el usuario es un encargado
                        if (checker.getTipoAccesoID().equals("4")){
                            Intent next = new Intent(getApplicationContext(), MenuEncargado.class);
                            next.putExtra("userID",checker.getUsuarioID());
                            startActivity(next);
                        }

                        //Detecta si el usuario es el administrador
                        if (checker.getTipoAccesoID().equals("5")){
                            Intent next = new Intent(getApplicationContext(), AdminRegisterUser.class);
                            next.putExtra("userID",checker.getUsuarioID());
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

        //TIPO ACCESO 5: ADMIN
        tda.setDescripcion("Admin");
        tda.setTipo("5");
        model.insertTipoAcceso(this, tda);


        //Agregar Admin
        Usuario admin = new Usuario();
        admin.setNombre("Admin");
        admin.setApellido("Admin");
        admin.setUsuario("admin");
        admin.setContrasenha("admin");
        admin.setCorreo("admin@gmail.com");
        admin.setTelefono("12345678");
        admin.setTipoAccesoID("5");
        model.insertUsuario(this, admin);



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
        rest1.setEncargadoID("4");
        rest1.setImageID("sanjoaquin");
        rest1.setTelefono("888888888");
        rest1.setCorreo("eats@gmail.com");


        Restaurante rest2 = new Restaurante();
        rest2.setNombre(dirRest2.getNombre());
        rest2.setDireccionID("2");
        rest2.setEncargadoID("5");
        rest2.setImageID("paraiso");
        rest2.setTelefono("888888888");
        rest2.setCorreo("eats@gmail.com");

        Restaurante rest3 = new Restaurante();
        rest3.setNombre(dirRest3.getNombre());
        rest3.setDireccionID("3");
        rest3.setEncargadoID("4");
        rest3.setImageID("heredia");
        rest3.setTelefono("888888888");
        rest3.setCorreo("eats@gmail.com");

        Restaurante rest4 = new Restaurante();
        rest4.setNombre(dirRest4.getNombre());
        rest4.setDireccionID("4");
        rest4.setEncargadoID("4");
        rest4.setImageID("puntarenas");
        rest4.setTelefono("888888888");
        rest4.setCorreo("eats@gmail.com");

        Restaurante rest5 = new Restaurante();
        rest5.setNombre(dirRest5.getNombre());
        rest5.setDireccionID("5");
        rest5.setEncargadoID("4");
        rest5.setImageID("sanrafael");
        rest5.setTelefono("888888888");
        rest5.setCorreo("eats@gmail.com");

        Restaurante rest6 = new Restaurante();
        rest6.setNombre(dirRest6.getNombre());
        rest6.setDireccionID("6");
        rest6.setImageID("coyol");
        rest6.setEncargadoID("4");
        rest6.setTelefono("888888888");
        rest6.setCorreo("eats@gmail.com");

        model.insertRestaurante(this, rest1);
        model.insertRestaurante(this, rest2);
        model.insertRestaurante(this, rest3);
        model.insertRestaurante(this, rest4);
        model.insertRestaurante(this, rest5);
        model.insertRestaurante(this, rest6);


        TipoDeComida tdc = new TipoDeComida();
        tdc.setDescripccion("Comida Rapida");
        model.insertTipoDeComida(this,tdc);

        tdc.setDescripccion("Postre");
        model.insertTipoDeComida(this,tdc);

        tdc.setDescripccion("Bebidas");
        model.insertTipoDeComida(this,tdc);

        Plato p1 = new Plato();
        p1.setNombre("Hamburguesa");
        p1.setCosto("3000");
        p1.setDescripcion("Hamburguesa angus de 1/4 de libre con queso. Hecha a la plancha y servida con queso de la casa y salsa especial");
        p1.setImage("hamburguesa");
        p1.setTipoComidaID("1");
        model.insertPlato(this,p1);

        Plato p2 = new Plato();
        p2.setNombre("Papas Supremas");
        p2.setCosto("2500");
        p2.setDescripcion("Papas a la francesa, fritas con nuestro aceite especial y servidas con nuestras deliciosas salsas y queso.");
        p2.setImage("papas");
        p2.setTipoComidaID("1");
        model.insertPlato(this,p2);

        Plato p3 = new Plato();
        p3.setNombre("Quesadilla de la Casa");
        p3.setCosto("3500");
        p3.setImage("quesadilla");
        p3.setTipoComidaID("2");
        p3.setDescripcion("Quesadilla hecha de una combinacion de quesos especiales y acompañada de una salsa de queso de la casa.");
        model.insertPlato(this,p3);

        Plato p4 = new Plato();
        p4.setNombre("Sandwich de la Casa");
        p4.setCosto("3500");
        p4.setDescripcion("Emparedado de pollo frito especial de la casa acompañado de nuestra mayonesa especial.");
        p4.setImage("sandwich.jpg");
        p4.setTipoComidaID("2");
        model.insertPlato(this,p4);

        Plato p5 = new Plato();
        p5.setNombre("Perro de la Casa");
        p5.setCosto("3500");
        p5.setDescripcion("Perro caliente con salchicha frita especial de la casa acompañado de nuestra mayonesa especial.");
        p5.setImage("perro");
        p5.setTipoComidaID("3");
        model.insertPlato(this,p5);

        Menu menu = new Menu();
        menu.setRestauranteID("1");
        menu.setPlatoID("1");
        menu.setCantidadDisponible("100");
        model.insertMenu(this,menu);

        Menu menu2 = new Menu();
        menu2.setRestauranteID("1");
        menu2.setPlatoID("2");
        menu2.setCantidadDisponible("100");
        model.insertMenu(this,menu2);

        Menu menu3 = new Menu();
        menu2.setRestauranteID("1");
        menu2.setPlatoID("3");
        menu2.setCantidadDisponible("100");
        model.insertMenu(this,menu2);

        Menu menu4 = new Menu();
        menu2.setRestauranteID("1");
        menu2.setPlatoID("4");
        menu2.setCantidadDisponible("100");
        model.insertMenu(this,menu2);

        Menu menu5 = new Menu();
        menu2.setRestauranteID("1");
        menu2.setPlatoID("5");
        menu2.setCantidadDisponible("100");
        model.insertMenu(this,menu2);

    }


}