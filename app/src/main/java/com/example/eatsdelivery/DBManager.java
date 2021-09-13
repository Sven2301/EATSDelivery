package com.example.eatsdelivery;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import 	android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "EATSDelivery.db";

    public DBManager (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
