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
import com.example.eatsdelivery.SQLite.Tables.DireccionXCliente;
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
        admin.setNombre("Lucia");
        admin.setApellido("Herrera");
        admin.setUsuario("AdmLu2021");
        admin.setContrasenha("lu");
        admin.setCorreo("admin@gmail.com");
        admin.setTelefono("22646251");
        admin.setTipoAccesoID("5");
        model.insertUsuario(this, admin);

        //Agregar cliente 1
        Tarjeta card = new Tarjeta();
        card.setCcv("123");
        card.setFechaVencimiento("10/24");
        card.setNombrePropietario("Marco Reveiz R.");
        card.setNumero("123456789");
        int statusClient1 = model.insertTarjeta(this, card);

        Usuario user = new Usuario();
        user.setNombre("Marco");
        user.setApellido("Reveiz");
        user.setUsuario("revvro");
        user.setContrasenha("rev2021");
        user.setCorreo("revvace@gmail.com");
        user.setTelefono("85769213");
        user.setTipoAccesoID("1");
        user.setTarjetaID("1");
        model.insertUsuario(this, user);


        //Agregar cliente 2
        Tarjeta card2 = new Tarjeta();
        card2.setCcv("178");
        card2.setFechaVencimiento("10/23");
        card2.setNombrePropietario("Sebastian Lopez H.");
        card2.setNumero("987654321");
        int statusClient2 = model.insertTarjeta(this, card2);

        Usuario user2 = new Usuario();
        user2.setNombre("Sebastian");
        user2.setApellido("Lopez");
        user2.setUsuario("FISH");
        user2.setContrasenha("fishlopez");
        user2.setCorreo("sebastianlopezherrera@gmail.com");
        user2.setTelefono("22683196");
        user2.setTipoAccesoID("1");
        user2.setTarjetaID("2");
        model.insertUsuario(this, user2);


        //Agregar repartidor
        Tarjeta card3 = new Tarjeta();
        card3.setCcv("475");
        card3.setFechaVencimiento("09/22");
        card3.setNombrePropietario("Nathan Stockton");
        card3.setNumero("6969656521");
        int statusClient3 = model.insertTarjeta(this, card3);

        Usuario user3 = new Usuario();
        user3.setNombre("Nathan");
        user3.setApellido("Stockton");
        user3.setUsuario("Nath56");
        user3.setContrasenha("contraseña");
        user3.setCorreo("cuentadediositoparalaprogra@gmail.com");
        user3.setTelefono("87875569");
        user3.setTipoAccesoID("2");
        user3.setTarjetaID("3");
        model.insertUsuario(this, user3);

        //Agregar encargado 1
        Tarjeta card4 = new Tarjeta();
        card4.setCcv("255");
        card4.setFechaVencimiento("09/26");
        card4.setNombrePropietario("Thomas Shelby");
        card4.setNumero("56565622114");
        int statusClient4 = model.insertTarjeta(this, card4);

        Usuario user4 = new Usuario();
        user4.setNombre("Thomas");
        user4.setApellido("Shelby");
        user4.setUsuario("TShelby");
        user4.setContrasenha("eatstshelby");
        user4.setCorreo("pablomuevaesasnalgas@gmail.com");
        user4.setTelefono("77778885");
        user4.setTipoAccesoID("4");
        user4.setTarjetaID("4");
        model.insertUsuario(this, user4);

        //Agregar encargado 2
        Tarjeta card5 = new Tarjeta();
        card5.setCcv("255");
        card5.setFechaVencimiento("09/26");
        card5.setNombrePropietario("Juan Mora");
        card5.setNumero("56565622114");
        int statusClient5 = model.insertTarjeta(this, card5);

        Usuario user5 = new Usuario();
        user5.setNombre("Juan");
        user5.setApellido("Mora");
        user5.setUsuario("JuaniMo");
        user5.setContrasenha("eatsjuanimo");
        user5.setCorreo("juanimo@gmail.com");   // Correo falso
        user5.setTelefono("77778885");
        user5.setTipoAccesoID("4");
        user5.setTarjetaID("5");
        model.insertUsuario(this, user5);

        //Agregar encargado 3
        Tarjeta card6 = new Tarjeta();
        card6.setCcv("070");
        card6.setFechaVencimiento("09/26");
        card6.setNombrePropietario("Pedro Solorzano");
        card6.setNumero("56565622114");
        int statusClient6 = model.insertTarjeta(this, card6);

        Usuario user6 = new Usuario();
        user6.setNombre("Pedro");
        user6.setApellido("Solorzano");
        user6.setUsuario("PSol");
        user6.setContrasenha("eatspsol");
        user6.setCorreo("psol@gmail.com");  // Correo falso
        user6.setTelefono("77778885");
        user6.setTipoAccesoID("4");
        user6.setTarjetaID("6");
        model.insertUsuario(this, user6);

        //Agregar gerente
        Tarjeta card7 = new Tarjeta();
        card7.setCcv("255");
        card7.setFechaVencimiento("12/09/26");
        card7.setNombrePropietario("Thomas Shelby");
        card7.setNumero("56565622114");
        int statusClient7 = model.insertTarjeta(this, card7);

        Usuario user7 = new Usuario();
        user7.setNombre("Diego");
        user7.setApellido("Mora");
        user7.setUsuario("Chayanne");
        user7.setContrasenha("pinguinosEnLaCama");
        user7.setCorreo("chayanne@gmail.com");
        user7.setTelefono("77778885");
        user7.setTipoAccesoID("3");
        user7.setTarjetaID("7");
        model.insertUsuario(this, user7);

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
        dirRest4.setNombre("Casa Marco");
        dirRest4.setDescripcion("A la par del AutoMercado Alajuela en una callejon de mala muerte");

        Direccion dirRest5 = new Direccion();
        dirRest5.setNombre("Casa Sebas");
        dirRest5.setDescripcion("Frente al restaurante Max Pollos");

        //Agrega direcciones
        model.insertDireccion(this, dirRest1);
        model.insertDireccion(this, dirRest2);
        model.insertDireccion(this, dirRest3);
        model.insertDireccion(this, dirRest4);
        model.insertDireccion(this, dirRest5);

        //Agregar Direcciones a Clientes
        DireccionXCliente direccionXCliente1 = new DireccionXCliente();
        direccionXCliente1.setDireccionID("4");
        direccionXCliente1.setUsuarioID("2");
        model.insertDireccionXCliente(this, direccionXCliente1);

        direccionXCliente1.setDireccionID("5");
        direccionXCliente1.setUsuarioID("3");
        model.insertDireccionXCliente(this, direccionXCliente1);

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
        rest3.setEncargadoID("6");
        rest3.setImageID("heredia");
        rest3.setTelefono("888888888");
        rest3.setCorreo("eats@gmail.com");

        model.insertRestaurante(this, rest1);
        model.insertRestaurante(this, rest2);
        model.insertRestaurante(this, rest3);

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
        p3.setTipoComidaID("1");
        p3.setDescripcion("Quesadilla hecha de una combinacion de quesos especiales y acompañada de una salsa de queso de la casa.");
        model.insertPlato(this,p3);

        Plato p4 = new Plato();
        p4.setNombre("Sandwich de la Casa");
        p4.setCosto("3500");
        p4.setDescripcion("Emparedado de pollo frito especial de la casa acompañado de nuestra mayonesa especial.");
        p4.setImage("sandwich.jpg");
        p4.setTipoComidaID("1");
        model.insertPlato(this,p4);

        Plato p5 = new Plato();
        p5.setNombre("Perro de la Casa");
        p5.setCosto("3500");
        p5.setDescripcion("Perro caliente con salchicha frita especial de la casa acompañado de nuestra mayonesa especial.");
        p5.setImage("perro");
        p5.setTipoComidaID("1");
        model.insertPlato(this,p5);

        Plato p6 = new Plato();
        p6.setNombre("Helado de chocolate");
        p6.setCosto("1250");
        p6.setDescripcion("2 bolas de helado de chocolate.");
        p6.setImage("helado");
        p6.setTipoComidaID("2");
        model.insertPlato(this,p6);

        Plato p7 = new Plato();
        p7.setNombre("Helado de caramelo");
        p7.setCosto("1250");
        p7.setDescripcion("2 bolas de helado de caramelo.");
        p7.setImage("helado");
        p7.setTipoComidaID("2");
        model.insertPlato(this,p7);

        Plato p8 = new Plato();
        p8.setNombre("Coca 750ml");
        p8.setCosto("1500");
        p8.setDescripcion("Coca de 750ml");
        p8.setImage("coca");
        p8.setTipoComidaID("3");
        model.insertPlato(this,p8);

        Plato p9= new Plato();
        p9.setNombre("Coca 2L");
        p9.setCosto("2500");
        p9.setDescripcion("Coca de 2L");
        p9.setImage("coca");
        p9.setTipoComidaID("3");
        model.insertPlato(this,p9);

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

        Menu menu6 = new Menu();
        menu6.setRestauranteID("1");
        menu6.setPlatoID("6");
        menu6.setCantidadDisponible("100");
        model.insertMenu(this,menu6);

        Menu menu7 = new Menu();
        menu7.setRestauranteID("1");
        menu7.setPlatoID("7");
        menu7.setCantidadDisponible("100");
        model.insertMenu(this,menu7);

        Menu menu8 = new Menu();
        menu8.setRestauranteID("1");
        menu8.setPlatoID("8");
        menu8.setCantidadDisponible("100");
        model.insertMenu(this,menu8);

        Menu menu9 = new Menu();
        menu9.setRestauranteID("1");
        menu9.setPlatoID("9");
        menu9.setCantidadDisponible("100");
        model.insertMenu(this,menu9);

    }


}