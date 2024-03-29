package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Plato;


public class ProductInfo extends AppCompatActivity {

    private Model model = new Model();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        Button addCarrito = (Button) findViewById(R.id.btnDelPE);
        TextView nombre = (TextView) findViewById(R.id.nombrePlato);
        TextView info = (TextView) findViewById(R.id.ltxtDescriptionPE);
        TextView costo = (TextView) findViewById(R.id.precio);
        ImageView imageView = (ImageView)  findViewById(R.id.fotoProductInfo);
        TextView cant = (TextView) findViewById(R.id.txtCantPE);

        Object clientID =  getIntent().getStringExtra("clientID");
        Object restId = getIntent().getStringExtra("RestID");

        Object platoID = getIntent().getStringExtra("PlatoID");
        Object p = getIntent().getStringExtra("PlatoID");
        Cursor cursor = model.selectDishID(this,p.toString());

        Plato plato = new Plato();

        if (cursor != null && cursor.getCount() > 0) {
            int index;
            cursor.moveToFirst();

            index = cursor.getColumnIndexOrThrow("id");
            plato.setPlatoID(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Nombre");
            plato.setNombre(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Costo");
            plato.setCosto(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Descripcion");
            plato.setDescripcion(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("ImagenID");
            plato.setImage(cursor.getString(index));

            System.out.println(cursor.getString(index));
            //Setea los texts y la imagen
            nombre.setText(plato.getNombre());

            info.setText(plato.getDescripcion());

            costo.setText("₡" + plato.getCosto());

            String uri = "@drawable/" + plato.getImage();

            int idD = this.getResources().getIdentifier(uri,null, this.getPackageName());
            imageView.setImageResource(idD);

        }

        addCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cant.getText().toString().equals("") || cant.getText().toString().equals("0")){

                    // No agrego producto al carrito
                    Toast.makeText(ProductInfo.this,"No has agregado nada al carrito",Toast.LENGTH_SHORT).show();
                    Intent next = new Intent(getApplicationContext(), RestaurantMenu.class);
                    next.putExtra("clientID",clientID.toString());
                    next.putExtra("platoID", platoID.toString());
                    next.putExtra("idRest", restId.toString());

                }
                else{
                    // Agregar producto a carrito
                    Intent next = new Intent(getApplicationContext(), RestaurantMenu.class);
                    plato.setCant(cant.getText().toString());

                    boolean found = false;
                    for (Plato p : TempCart.platos){

                        if (plato.getNombre().equals(p.getNombre())){
                            int index = TempCart.platos.indexOf(p);
                            TempCart.platos.get(index).setCant(plato.getCant());
                            found = true;
                        }
                    }
                    if(!found){
                        TempCart.platos.add(plato);
                    }
                    next.putExtra("clientID",clientID.toString());
                    next.putExtra("platoID", platoID.toString());
                    next.putExtra("idRest", restId.toString());
                    Toast.makeText(getApplicationContext(), "Producto agregado", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void back(View view){

        Intent next = new Intent(this, RestaurantMenu.class);
        startActivity(next);
    }
}