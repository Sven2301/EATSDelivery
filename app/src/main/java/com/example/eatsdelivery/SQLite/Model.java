package com.example.eatsdelivery.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.DireccionXCliente;
import com.example.eatsdelivery.SQLite.Tables.InfoUsuario;
import com.example.eatsdelivery.SQLite.Tables.LineaFactura;
import com.example.eatsdelivery.SQLite.Tables.Menu;
import com.example.eatsdelivery.SQLite.Tables.Orden;
import com.example.eatsdelivery.SQLite.Tables.Plato;
import com.example.eatsdelivery.SQLite.Tables.Restaurante;
import com.example.eatsdelivery.SQLite.Tables.RestauranteXGerente;
import com.example.eatsdelivery.SQLite.Tables.Tarjeta;
import com.example.eatsdelivery.SQLite.Tables.TipoDeComida;
import com.example.eatsdelivery.SQLite.Tables.TipoDeAcceso;
import com.example.eatsdelivery.SQLite.Tables.Usuario;

public class Model {

    public static final int VERSION = 1;
    public static final String DB_NAME = "EATSDelivery.db";

    public SQLiteDatabase getConnWrite(Context context){
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
        String sql = "INSERT INTO TipoDeAcceso (Descripcion, Tipo) VALUES ('"+tda.getDescripcion()+"', '"+tda.getTipo()+"')";
        SQLiteDatabase db = this.getConnWrite(context);
        try {
            db.execSQL(sql);
            res = 1;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int insertDireccion(Context context, Direccion d) {
        int res = 0;
        String sql = "INSERT INTO Direccion (Nombre, Descripcion, DireccionExacta) VALUES ('"+d.getNombre()+"', '"+d.getDescripcion()+"', '"+d.getDireccionExacta()+"')";
        SQLiteDatabase db = this.getConnWrite(context);
        try {
            db.execSQL(sql);
            res = 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int insertDireccionXCliente(Context context, DireccionXCliente dxc) {
        int res = 0;
        String sql = "INSERT INTO DireccionXCliente (DireccionID, InfoUsuarioID) VALUES ('"+dxc.getDireccionID()+"', '"+dxc.getInfoUsuarioID()+"')";
        SQLiteDatabase db = this.getConnWrite(context);
        try {
            db.execSQL(sql);
            res = 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int insertInfoUsuario(Context context, InfoUsuario iu) {
        int res = 0;
        String sql = "INSERT INTO InfoUsuario(UsuarioID, TarjetaID, Nombre, Telefono, Correo, PlacaVehiculo) VALUES ('"+iu.getUsuarioID()+"', '"+iu.getTarjetaID()+"', '"+iu.getNombre()+"', '"+iu.getTelefono()+"', '"+iu.getCorreo()+"', '"+iu.getPlacaVehiculo()+"')";
        SQLiteDatabase db = this.getConnWrite(context);
        try {
            db.execSQL(sql);
            res = 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int insertLineaFactura(Context context, LineaFactura lf) {
        int res = 0;
        String sql = "INSERT INTO LineaFactura(PlatoID, OrdenID, Cantidad) VALUES ('"+"', '"+lf.getPlatoID()+"', '"+lf.getOrdenID()+"', '"+lf.getCantidad()+"')";
        SQLiteDatabase db = this.getConnWrite(context);
        try {
            db.execSQL(sql);
            res = 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int insertMenu(Context context, Menu m) {
        int res = 0;
        String sql = "INSERT INTO Menu(RestauranteID, PlatoID, CantidadDisponible) VALUES ('"+"', '"+m.getRestauranteID()+"', '"+m.getPlatoID()+"', '"+m.getCantidadDisponible()+"')";
        SQLiteDatabase db = this.getConnWrite(context);
        try {
            db.execSQL(sql);
            res = 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int insertOrden(Context context, Orden o) {
        int res = 0;
        String sql = "INSERT INTO Orden(ClienteID, RepartidorID, RestauranteID, DireccionID, costoTotal) VALUES ('"+"', '"+o.getClienteID()+"', '"+o.getRepartidorID()+"', '"+o.getRestauranteID()+"', '"+o.getDireccionID()+"', '"+o.getCostoTotal()+"')";
        SQLiteDatabase db = this.getConnWrite(context);
        try {
            db.execSQL(sql);
            res = 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int insertPlato(Context context, Plato p) {
        int res = 0;
        String sql = "INSERT INTO Plato(Nombre, Costo, Descripccion) VALUES ('"+"', '"+p.getNombre()+"', '"+p.getCosto()+"', '"+p.getDescripcion()+"')";
        SQLiteDatabase db = this.getConnWrite(context);
        try {
            db.execSQL(sql);
            res = 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int insertRestaurante(Context context, Restaurante r) {
        int res = 0;
        String sql = "INSERT INTO Restaurante(DireccionID, TipoComidaID, Nombre, Activo) VALUES ('"+r.getDireccionID()+"', '"+r.getTipoComidaID()+"', '"+r.getNombre()+"', '"+r.getDeshabilitar()+"')";
        SQLiteDatabase db = this.getConnWrite(context);
        try {
            db.execSQL(sql);
            res = 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int insertRestauranteXGerente(Context context, RestauranteXGerente rxg) {
        int res = 0;
        String sql = "INSERT INTO RestauranteXGerente(RestauranteID, InfoUsuarioID) VALUES ('"+rxg.getRestauranteID()+"', '"+rxg.getInfoUsuarioID()+"')";
        SQLiteDatabase db = this.getConnWrite(context);
        try {
            db.execSQL(sql);
            res = 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int insertTarjeta(Context context, Tarjeta t) {
        int res = 0;
        String sql = "INSERT INTO Tarjeta(NombrePropietario, NumeroTarjeta, CCV, FechaVencimiento) VALUES ('"+t.getNombrePropietario()+"', '"+t.getNumero()+"', '"+t.getCcv()+"', '"+t.getFechaVencimiento()+"')";
        SQLiteDatabase db = this.getConnWrite(context);
        try {
            db.execSQL(sql);
            res = 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int insertTipoDeComida(Context context, TipoDeComida tdc) {
        int res = 0;
        String sql = "INSERT INTO TipoDeComida(Descripcion) VALUES ('"+tdc.getDescripccion()+"')";
        SQLiteDatabase db = this.getConnWrite(context);
        try {
            db.execSQL(sql);
            res = 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int insertUsuario(Context context, Usuario u) {
        int res = 0;
        String sql = "INSERT INTO Usuario(TipoAccesoID, Usuario, Contrasenha) VALUES ('"+u.getTipoAccesoID()+"', '"+u.getUsuario()+"', '"+u.getContrasenna()+"')";
        SQLiteDatabase db = this.getConnWrite(context);
        try {
            db.execSQL(sql);
            res = 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
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

    public InfoUsuario validarUsuario(Context context, String usuario, String contrasenha) {
        SQLiteDatabase db = getConnRead(context);

        String query = "SELECT iu.* FROM InforUsuario iu INNER JOIN Usurio ON iu.UsuarioID = u.id WHERE u.Usuario = ? AND u.Contrasenha = ?";
        Cursor cursor = db.rawQuery(query, new String[]{usuario, contrasenha});

        InfoUsuario infoUsuario = null;

        if (cursor != null && cursor.getCount() > 0) {
            infoUsuario = new InfoUsuario();
            int index;

            index = cursor.getColumnIndexOrThrow("id");
            infoUsuario.setInfoUsuarioID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("UsuarioID");
            infoUsuario.setUsuarioID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("TarjetaID");
            infoUsuario.setTarjetaID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("Nombre");
            infoUsuario.setNombre(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Telefono");
            infoUsuario.setTelefono(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Correo");
            infoUsuario.setCorreo(cursor.getString(index));
        }
        return infoUsuario;
    }

}
