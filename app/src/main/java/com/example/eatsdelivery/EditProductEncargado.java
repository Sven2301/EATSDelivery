package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Plato;

public class EditProductEncargado extends AppCompatActivity {
    Object platoID;
    Object restID;

    EditText txtNombrePE;
    EditText txtCostPE;
    EditText txtCantPE;
    TextView ltxtDescriptionPE;
    Button btnDelPE;
    Button btnConfPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product_encargado);

        this.platoID = getIntent().getStringExtra("PlatoID");
        this.restID = getIntent().getStringExtra("RestID");

        this.txtNombrePE = (EditText) findViewById(R.id.txtNombrePE);
        this.txtCostPE = (EditText) findViewById(R.id.txtCostPE);
        this.txtCantPE = (EditText) findViewById(R.id.txtCantPE);
        this.ltxtDescriptionPE = (TextView) findViewById(R.id.ltxtDescriptionPE);
        this.btnConfPE = (Button) findViewById(R.id.btnConfPE);
        this.btnDelPE = (Button) findViewById(R.id.btnDelPE);

        btnConfPE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model();
                Plato plato = new Plato();

                plato.setNombre(txtNombrePE.getText().toString());
                plato.setCosto(txtCostPE.getText().toString());
                plato.setDescripcion(ltxtDescriptionPE.getText().toString());

                model.updateCantidadMenu(view.getContext(), txtCantPE.getText().toString(), restID.toString(), platoID.toString());
                model.updatePlatoInfo(view.getContext(), plato, platoID.toString());
            }
        });

        btnDelPE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model();
                model.updateCantidadMenu(view.getContext(), "-1", restID.toString(), platoID.toString());
            }
        });
    }


}