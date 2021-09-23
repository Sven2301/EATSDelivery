package com.example.eatsdelivery;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.eatsdelivery.SQLite.Tables.Plato;

import java.util.ArrayList;

public class TempCart {

    static String idClient, idRest;
    static ArrayList <Plato> platos = new ArrayList<>();

    public static String getTotalPrice(){

        String totalPrice = "";
        int res = 0;

        //Suma precios de platos
        for (Plato p : platos){

            res += Integer.parseInt(p.getCosto()) * Integer.parseInt(p.getCant());

        }
        //Transfroma resultado en string
        totalPrice = String.valueOf(res);

        return totalPrice;
    }

    public static void clearCart(){
        platos.clear();
    }
}
