package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Tarjeta;

public class AddPaymentMethodActivity extends AppCompatActivity {

    private EditText cardNumber;
    private EditText nameClient;
    private EditText expDate;
    private EditText ccv;
    public Button btn_Confirm;

    private Tarjeta tarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_method);

        cardNumber = (EditText) findViewById(R.id.editTextCardNumber);
        nameClient =  (EditText) findViewById(R.id.editTextTextPersonName);
        expDate = (EditText)  findViewById(R.id.editTextDate);
        ccv = (EditText)  findViewById(R.id.editTextCCV);
        btn_Confirm = (Button) findViewById(R.id.button3);
        Model model = new Model();


        btn_Confirm.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {


                String number = cardNumber.getText().toString();
                String cardHolder = nameClient.getText().toString();
                String ccvn = ccv.getText().toString();
                String date = expDate.getText().toString();

                Tarjeta card = new Tarjeta();
                card.setNumero(number);
                card.setFechaVencimiento(date);
                card.setCcv(ccvn);
                card.setNombrePropietario(cardHolder);
                int status = model.insertTarjeta(view.getContext(), card);

                Toast.makeText(view.getContext(), String.valueOf(status), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goBack(View view){

        Intent next = new Intent(this, RegisterActivity.class);
        startActivity(next);
    }

    public void confirm (View view){

    }
}