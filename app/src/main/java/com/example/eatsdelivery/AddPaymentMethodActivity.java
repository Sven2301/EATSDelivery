package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.eatsdelivery.SQLite.Tables.Tarjeta;

public class AddPaymentMethodActivity extends AppCompatActivity {

    private EditText cardNumber;
    private EditText nameClient;
    private EditText expDate;
    private EditText cvc;

    private Tarjeta tarjeta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_method);
    }

    public void goBack(View view){

        Intent next = new Intent(this, RegisterActivity.class);
        startActivity(next);
    }
}