package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Menu;
import com.example.eatsdelivery.SQLite.Tables.Plato;

public class AddDishEncargado extends AppCompatActivity {
    Object idRes;

    EditText product_name;
    EditText cost;
    EditText description;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish_encargado);

        this.idRes = getIntent().getStringExtra("idRes");

        this.product_name = (EditText) findViewById(R.id.product_name);
        this.cost = (EditText) findViewById(R.id.cost);
        this.description = (EditText) findViewById(R.id.description);
        this.confirm = (Button) findViewById(R.id.confirm);

        this.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model();
                Plato plato = new Plato();
                Menu menu = new Menu();

                plato.setNombre(product_name.getText().toString());
                plato.setCosto(cost.getText().toString());
                plato.setDescripcion(description.getText().toString());
                plato.setTipoComidaID("0");
                plato.setImage("");

                model.insertPlato(view.getContext(), plato);

                menu.setCantidadDisponible("0");
                menu.setPlatoID(String.valueOf(model.getLastID(view.getContext(), "Plato")));
                menu.setRestauranteID(idRes.toString());

                model.insertMenu(view.getContext(), menu);
            }
        });
    }
}