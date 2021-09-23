package com.example.eatsdelivery;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Direccion;

public class EditRestDirectionEncargado extends AppCompatActivity {

    Object idRestaurante;

    EditText edtTxtNombreDir;
    EditText edtTxtDesc;
    Button btnCambiarDir;

    int idDir;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info_rest_encargado);

        this.idRestaurante = getIntent().getStringExtra("idRestaurante");

        this.edtTxtDesc = (EditText) findViewById(R.id.edtTxtDesc);
        this.edtTxtNombreDir = (EditText) findViewById(R.id.edtTxtNombreDir);
        this.btnCambiarDir = (Button) findViewById(R.id.btnCambiarDir);

        Model model = new Model();
        this.idDir = model.selectIDDirXRes(this, idRestaurante.toString());

        btnCambiarDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idDir != -1){
                    Direccion direccion = new Direccion();
                    direccion.setNombre(edtTxtNombreDir.getText().toString());
                    direccion.setDescripcion(edtTxtDesc.getText().toString());
                    model.updateDirecccion(view.getContext(), direccion, String.valueOf(idDir));
                }
            }
        });
    }




}
