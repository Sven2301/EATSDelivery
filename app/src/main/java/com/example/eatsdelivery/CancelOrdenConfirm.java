package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CancelOrdenConfirm extends AppCompatActivity {

    private Model model = new Model();
    long orderDate = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_orden_confirm);

        TextView detalles = (TextView) findViewById(R.id.detalles_pedido_cancel);
        Button confirm = (Button) findViewById(R.id.confirmCancel_btn);

        Object info = getIntent().getStringExtra("detail");
        Object direc = getIntent().getStringExtra("direc");
        Object ordenid = getIntent().getStringExtra("orden");
        Object repartidorid = getIntent().getStringExtra("repartidor");
        long orderDate = getIntent().getLongExtra("fechaOrden", 0);


        detalles.setText(info.toString());

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 //currentTime
                long time = Calendar.getInstance().getTime().getTime();

                if (printDifference(orderDate, time)){
                    Toast.makeText(getApplicationContext(), "Cancelado con impuesto", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Cancelado sin impuesto", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public boolean printDifference(long startDate, long endDate) {
        //milliseconds
        long different = endDate - startDate;

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);

        if (elapsedSeconds > 10){
            return true;
        }
        else {
            return false;
        }
    }
}