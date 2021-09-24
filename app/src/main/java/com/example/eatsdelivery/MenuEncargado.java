package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;

public class MenuEncargado extends AppCompatActivity {

    Object idEncargado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_encargado);

        this.idEncargado = getIntent().getStringExtra("userID");
    }

    public void editMenuRest(View view){    // 0/3
        Intent next = new Intent(this, EditMenuEncargado.class);
        next.putExtra("idEncargado", idEncargado.toString());
        startActivity(next);
    }

    public void updateInfoRest(View view){  // LISTO
        Intent next = new Intent(this, EditInfoRestEncargado.class);
        next.putExtra("idEncargado", idEncargado.toString());
        startActivity(next);
    }

    public void manageOrders(View view){    // 1/2
        Intent next = new Intent(this, OrderManageEncargado.class);
        next.putExtra("idEncargado", idEncargado.toString());
        startActivity(next);
    }

    public void requestDeletetion(View view){   // LISTO
        Intent next = new Intent(this, DeleteRequestEncargado.class);
        next.putExtra("idEncargado", idEncargado.toString());
        startActivity(next);
    }

    public void quit(View view){    // LISTO
        Intent next = new Intent(this, MainActivity.class);
        next.putExtra("idEncargado", idEncargado.toString());
        startActivity(next);
    }

    public static int getRestaurantID(Context context, String idEncargado) {
        Model model = new Model();
        Cursor cursor = model.selectRestauranteEncargado(context, idEncargado);
        int idRestaurante = -1;
        Toast.makeText(context.getApplicationContext(), "Count" + cursor.getCount(), Toast.LENGTH_SHORT).show();
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int index = cursor.getColumnIndexOrThrow("id");
            idRestaurante = cursor.getInt(index);
        }
        return idRestaurante;
    }


}