package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eatsdelivery.SQLite.Model;

public class DeleteRequestEncargado extends AppCompatActivity {
    Object idEncargado;
    Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_request_encargado);

        this.idEncargado = getIntent().getStringExtra("idEncargado");

        this.btnConfirmar = (Button) findViewById(R.id.btnConfirmar);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model();
                int idRes = MenuEncargado.getRestaurantID(view.getContext(), idEncargado.toString());
                if (idRes != -1)
                    model.updateRestActive(view.getContext(), "1", String.valueOf(idRes));
            }
        });
    }


    
}