package com.example.eatsdelivery.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Model {

    public static final int VERSION = 1;
    public static final String DB_NAME = "EATSDeliveryDB.db";

    public SQLiteDatabase getConn(Context context){
        ConexionSQLite conn = new ConexionSQLite(context, DB_NAME, null, VERSION);
        SQLiteDatabase db = conn.getWritableDatabase();
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
}
