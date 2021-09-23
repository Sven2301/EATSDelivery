package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.Orden;
import com.example.eatsdelivery.SQLite.Tables.Plato;
import com.example.eatsdelivery.SQLite.Tables.Restaurante;
import com.example.eatsdelivery.SQLite.Tables.Tarjeta;
import com.example.eatsdelivery.SQLite.Tables.Usuario;

import java.util.ArrayList;

public  class  Cart extends AppCompatActivity {

    ListView listView;
    private Model model = new Model();
    TextView total;
    String msgFactura;
    Button confirm;
    Usuario client;
    Tarjeta tarjeta;
    Direccion direccionClient;
    Restaurante rest;
    Orden orden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cart);
        listView = findViewById(R.id.listViewRests);
        total = findViewById(R.id.totalPrice);
        confirm = findViewById(R.id.confirmCarrito);
        //Crea mensaje de factura
        Object restId = getIntent().getStringExtra("RestID");
        Object restName = getIntent().getStringExtra("nameRest");
        Object clientID =  getIntent().getStringExtra("clientID");
        msgFactura = "Tu pedido en "+ restName +"se ha realizado.\n" ;

        // Iguala objeto restaurante pedido y usuario

        //Usuario
        Cursor cursor = model.selectProductosXRestaurante(this, clientID.toString());


        cursor.moveToFirst();
        Usuario newUser = new Usuario();
        while(!cursor.isAfterLast()){

            int index;

            index = cursor.getColumnIndexOrThrow("id");
            newUser.setUsuarioID(String.valueOf(cursor.getInt(index)));
            index = cursor.getColumnIndexOrThrow("Nombre");
            newUser.setNombre(cursor.getString(index));
            index = cursor.getColumnIndexOrThrow("Apellido");
            newUser.setApellido(String.valueOf(cursor.getInt(index)));
            index = cursor.getColumnIndexOrThrow("Correo");
            newUser.setCorreo(cursor.getString(index));
            index = cursor.getColumnIndexOrThrow("TarjetaID");
            newUser.setTarjetaID(cursor.getString(index));
            index = cursor.getColumnIndexOrThrow("Telefono");
            newUser.setTelefono(cursor.getString(index));
            cursor.moveToNext();

        }

        client = newUser;

        // Tarjeta

        Cursor cursor1 = model.selectInfoTarjeta(this, clientID.toString());


        cursor.moveToFirst();
        Tarjeta newTarjeta = new Tarjeta();
        while(!cursor.isAfterLast()){


            int index;

            index = cursor1.getColumnIndexOrThrow("id");
            newTarjeta.setTarjetaID(String.valueOf(cursor1.getInt(index)));
            index = cursor1.getColumnIndexOrThrow("NombrePropietario");
            newTarjeta.setNombrePropietario(cursor1.getString(index));
            index = cursor1.getColumnIndexOrThrow("NumeroTarjeta");
            newTarjeta.setNumero(String.valueOf(cursor1.getInt(index)));
            index = cursor1.getColumnIndexOrThrow("CCV");
            newTarjeta.setCcv(cursor1.getString(index));
            index = cursor1.getColumnIndexOrThrow("FechaVencimiento");
            newTarjeta.setFechaVencimiento(cursor1.getString(index));

            cursor.moveToNext();

        }
        tarjeta = newTarjeta;

        //Restaurante

        Cursor cursor2 = model.selectRestauranteID(this, restId.toString());


        cursor.moveToFirst();
        Restaurante newRest = new Restaurante();
        while(!cursor.isAfterLast()){


            int index;

            index = cursor2.getColumnIndexOrThrow("id");
            newRest.setRestauranteID(String.valueOf(cursor2.getInt(index)));
            index = cursor2.getColumnIndexOrThrow("Nombre");
            newRest.setNombre(cursor2.getString(index));
            index = cursor2.getColumnIndexOrThrow("Telefono");
            newRest.setTelefono(String.valueOf(cursor2.getInt(index)));
            index = cursor2.getColumnIndexOrThrow("Correo");
            newRest.setCorreo(cursor2.getString(index));
            index = cursor2.getColumnIndexOrThrow("EncargadoID");
            newRest.setEncargadoID(cursor2.getString(index));
            index = cursor2.getColumnIndexOrThrow("DireccionID");
            newRest.setDireccionID(cursor2.getString(index));

            cursor.moveToNext();

        }
        rest = newRest;

        //Direccion


        cursor.moveToFirst();
        Direccion newDir = new Direccion();



        //For para ver la lista de platos
        ArrayList<Plato> platos = TempCart.platos;

        // We make custom adapter
        PlatoAdapter platoAdapter = new PlatoAdapter(this,R.layout.list_row, platos);

        //Create adapter
        listView.setAdapter(platoAdapter);

        //Pone el precio total
        total.setText(TempCart.getTotalPrice());

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Agregar correo y realizar pedido
                Toast.makeText(Cart.this,"Pedido realizado!",Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void back(View view){

        Intent next = new Intent(this, RestaurantMenu.class);
        startActivity(next);
    }

    public void confirmOrder(View view){

        Intent next = new Intent(this, OrderWaitScreen.class);
        startActivity(next);
    }
}