package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Plato;
import com.example.eatsdelivery.SQLite.Tables.Usuario;

import org.w3c.dom.Text;

public class ProductInfo extends AppCompatActivity {

    private Model model = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        TextView nombre = (TextView) findViewById(R.id.nombrePlato);
        TextView info = (TextView) findViewById(R.id.prodcut_Info);
        TextView costo = (TextView) findViewById(R.id.precio);
        ImageView imageView = (ImageView)  findViewById(R.id.image);
        TextView cant = (TextView) findViewById(R.id.cant);

        Object p = getIntent().getStringExtra("PlatoID");
        Cursor cursor = model.selectDishID(this,p.toString());
        Plato plato = new Plato();;

        if (cursor != null && cursor.getCount() > 0) {
            int index;
            cursor.moveToFirst();


            index = cursor.getColumnIndexOrThrow("Nombre");
            plato.setNombre(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Costo");
            plato.setCosto(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Descripcion");
            plato.setDescripcion(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("ImagenID");
            plato.setImage(String.valueOf(cursor.getInt(index)));

            nombre.setText(plato.getNombre());
            info.setText(plato.getDescripcion());
            costo.setText(plato.getCosto());
            imageView.setImageResource(Integer.parseInt(plato.getImage()));

        }



    }

    public void back(View view){

        Intent next = new Intent(this, RestaurantMenu.class);
        startActivity(next);
    }
}