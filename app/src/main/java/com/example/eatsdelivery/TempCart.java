package com.example.eatsdelivery;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.eatsdelivery.SQLite.Tables.Plato;

import java.util.ArrayList;

public class TempCart implements Parcelable {

    String idClient, idRest;

    ArrayList <Plato> platos;

    public String getTotalPrice(){

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

    public void addPlato(Plato plato){
        platos.add(plato);
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdRest() {
        return idRest;
    }

    public void setIdRest(String idRest) {
        this.idRest = idRest;
    }

    public ArrayList<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(ArrayList<Plato> platos) {
        this.platos = platos;
    }

    public static Creator<TempCart> getCREATOR() {
        return CREATOR;
    }


    protected  TempCart(){}
    protected TempCart(Parcel in) {


    }

    public static final Creator<TempCart> CREATOR = new Creator<TempCart>() {
        @Override
        public TempCart createFromParcel(Parcel in) {
            return new TempCart(in);
        }

        @Override
        public TempCart[] newArray(int size) {
            return new TempCart[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
