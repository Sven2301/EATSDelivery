package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;

public class MenuEncargado extends AppCompatActivity {

    Object idEncargado;

    Button edit_menu;
    Button update_res_info;
    Button manage_orders;
    Button sol_deletion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_encargado);

        this.idEncargado = getIntent().getStringExtra("userID");

        this.edit_menu = (Button) findViewById(R.id.edit_menu);
        this.update_res_info = (Button) findViewById(R.id.update_res_info);
        this.manage_orders = (Button) findViewById(R.id.manage_orders);
        this.sol_deletion = (Button) findViewById(R.id.sol_deletion);

        edit_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMenuRest(view);
            }
        });

        update_res_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInfoRest(view);
            }
        });

        manage_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manageOrders(view);
            }
        });

        sol_deletion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestDeletetion(view);
            }
        });
    }

    public void editMenuRest(View view){    // LISTO
        Intent next = new Intent(this, EditMenuEncargado.class);
        next.putExtra("idEncargado", idEncargado.toString());
        startActivity(next);
    }

    public void updateInfoRest(View view){  // LISTO
        Intent next = new Intent(this, EditInfoRestEncargado.class);
        next.putExtra("idEncargado", idEncargado.toString());
        startActivity(next);
    }

    public void manageOrders(View view){    // LISTO
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