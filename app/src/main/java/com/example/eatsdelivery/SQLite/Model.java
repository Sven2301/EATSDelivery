package com.example.eatsdelivery.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class Model {

    public static final int VERSION = 1;
    public static final String DB_NAME = "DBEATSDelivery";

    public SQLiteDatabase getConn(Context context){
        ConexionSQLite conn = new ConexionSQLite(context, DB_NAME, null, VERSION);
        SQLiteDatabase db = conn.getWritableDatabase();
        return db;
    }

    public SQLiteDatabase getConnRead(Context context){
        ConexionSQLite conn = new ConexionSQLite(context, DB_NAME, null, VERSION);
        SQLiteDatabase db = conn.getReadableDatabase();
        return db;
    }

    public int insertTipoAcceso(Context context, TipoDeAcceso tda){
        int res = 0;
        String sql = "INSERT INTO TipoDeAcceso (id, Descripcion, Tipo) VALUES ('"+tda.getId()+"', '"+tda.getDescripcion()+"', '"+tda.getTipo()+"')";
        SQLiteDatabase db = this.getConn(context);
        try {
            db.execSQL(sql);
            res = 1;
        }
        catch (Exception e){

        }
        return res;
    }

    public Cursor getTipoAcceso(Context context){

        SQLiteDatabase db = getConnRead(context);

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                "id",
                "Descripcion",
                "Tipo"
        };


        Cursor cursor = db.query(
                "TipoDeAcceso",   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,         // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        return cursor;
    }
}
