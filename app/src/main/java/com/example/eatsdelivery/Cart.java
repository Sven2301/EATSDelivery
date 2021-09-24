package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    Restaurante rest;

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
        Object dirID = getIntent().getStringExtra("dirID");

        msgFactura = "Tu pedido en "+ restName +"se ha realizado.\n" ;

        // Iguala objeto restaurante pedido y usuario


        //Usuario
        Cursor cursor = model.selectUsuarioID(this, clientID.toString());


        cursor.moveToFirst();
        Usuario newUser = new Usuario();

        int index;

        index = cursor.getColumnIndexOrThrow("id");
        newUser.setUsuarioID(String.valueOf(cursor.getInt(index)));
        index = cursor.getColumnIndexOrThrow("Nombre");
        newUser.setNombre(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow("Correo");
        newUser.setCorreo(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow("TarjetaID");
        newUser.setTarjetaID(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow("Telefono");
        newUser.setTelefono(cursor.getString(index));
        cursor.moveToNext();

        client = newUser;

        // Tarjeta

        Cursor cursor1 = model.selectInfoTarjeta(this, clientID.toString());

        cursor1.moveToFirst();
        Tarjeta newTarjeta = new Tarjeta();


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


        tarjeta = newTarjeta;

        //Restaurante

        Cursor cursor2 = model.selectRestauranteID(this, restId.toString());

        cursor2.moveToFirst();
        Restaurante newRest = new Restaurante();

        index = cursor2.getColumnIndexOrThrow("id");
        newRest.setRestauranteID(String.valueOf(cursor2.getInt(index)));
        index = cursor2.getColumnIndexOrThrow("Nombre");
        newRest.setNombre(cursor2.getString(index));
        index = cursor2.getColumnIndexOrThrow("DireccionID");
        newRest.setDireccionID(cursor2.getString(index));

        rest = newRest;

        //Direccion Rest

        Cursor cursor3 = model.selectDireccion(this, dirID.toString());

        cursor3.moveToFirst();
        Direccion newDir = new Direccion();

        index = cursor3.getColumnIndexOrThrow("id");
        newDir.setDireccionID(String.valueOf(cursor3.getInt(index)));
        index = cursor3.getColumnIndexOrThrow("Nombre");
        newDir.setNombre(cursor3.getString(index));
        index = cursor3.getColumnIndexOrThrow("Descripcion");
        newDir.setDescripcion(cursor3.getString(index));

        //For para ver la lista de platos
        ArrayList<Plato> platos = TempCart.platos;

        // We make custom adapter
        CartListAdapter platoAdapter = new CartListAdapter(this,R.layout.cart_list_row, platos);

        //Create adapter
        listView.setAdapter(platoAdapter);

        //Pone el precio total
        total.setText("₡" + TempCart.getTotalPrice());

        //Set data
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                platos.remove(platos.get(i));
                CartListAdapter platoAdapter = new CartListAdapter(Cart.this,R.layout.cart_list_row, platos);
                listView.setAdapter(platoAdapter);
                total.setText("₡" +0);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String products = "";
                for (Plato p : platos){
                    products += p.getNombre() + " Costo " + p.getCosto() + " Cant " + p.getCant() + "\n";
                }
                //Agregar correo y realizar pedido
                String factura = "Nombre de dirección entrega: " + newDir.getNombre() +  "\n\n" +
                        "Señales: " + newDir.getDescripcion() + "\n\n" +
                        "ID de cliente: "+ client.getUsuarioID() +"\n\n" +
                        "Cliente: " + client.getNombre() + " "+ "\n\n" +
                        "Correo " + client.getCorreo() + " "+ "\n\n" +
                        "Numero de tarjeta: " + tarjeta.getNumero() + "\n\n" +
                        products + "\n" +
                        "Total a pagar: " + "₡" + TempCart.getTotalPrice();

                Toast.makeText(Cart.this,"Pedido realizado!",Toast.LENGTH_SHORT).show();

                Intent next = new Intent(getApplicationContext(), OrderWaitScreen.class);
                Orden orden = new Orden();
                orden.setCostoTotal(TempCart.getTotalPrice());
                orden.setClienteID(client.getUsuarioID());
                orden.setDireccionID(dirID.toString());
                orden.setFactura(factura);
                orden.setRestauranteID(rest.getRestauranteID());
                orden.setEnCamino("0");
                int status = model.insertOrden(getApplicationContext(), orden);

                if (status == 1){
                    Toast.makeText(getApplicationContext(), "Orden confirmada", Toast.LENGTH_SHORT).show();
                }
                System.out.println(factura);
                next.putExtra("factura", factura);
                startActivity(next);
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