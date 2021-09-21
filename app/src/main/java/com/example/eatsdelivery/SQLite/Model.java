package com.example.eatsdelivery.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.eatsdelivery.MainActivity;
import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.DireccionXCliente;
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
        String sql = "INSERT INTO Direccion (Nombre, Descripcion, Activo) VALUES ('"+d.getNombre()+"', '"+d.getDescripcion()+"', '"+1+"')";
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
        String sql = "INSERT INTO DireccionXCliente (DireccionID, InfoUsuarioID) VALUES ('"+dxc.getDireccionID()+"', '"+dxc.getUsuarioID()+"')";
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
        String sql = "INSERT INTO LineaFactura(PlatoID, OrdenID, Cantidad) VALUES ('"+lf.getPlatoID()+"', '"+lf.getOrdenID()+"', '"+lf.getCantidad()+"')";
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
        String sql = "INSERT INTO Orden(ClienteID, RepartidorID, RestauranteID, DireccionID, costoTotal, enCamino) VALUES ('"+o.getClienteID()+"', '"+o.getRepartidorID()+"', '"+o.getRestauranteID()+"', '"+o.getDireccionID()+"', '"+o.getCostoTotal()+"', '"+o.getEnCamino()+"')";
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
        String sql = "INSERT INTO Restaurante(DireccionID, Nombre, Activo, UsuarioID, Telefono, Correo) VALUES ('"+r.getDireccionID()+"', '"+r.getNombre()+"', '"+2+"', '"+r.getEncargadoID()+"'" +
                ", '"+r.getTelefono()+"', '"+r.getCorreo()+"')";
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
        String sql = "INSERT INTO RestauranteXGerente(RestauranteID, UsuarioID) VALUES ('"+rxg.getRestauranteID()+"', '"+rxg.getUsuarioID()+"')";
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
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
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
        String sql = "INSERT INTO Usuario(TipoAccesoID, Usuario, Contrasenha, Nombre, Apellido, Correo, Telefono, TarjetaID) VALUES ('"+u.getTipoAccesoID()+"', '"+u.getUsuario()+"', '"+u.getContrasenha()+"', " +
                "'"+u.getNombre()+"', '"+u.getApellido()+"', '"+u.getCorreo()+"', '"+u.getTelefono()+"', '"+u.getTarjetaID()+"')";
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

    public Usuario validarUsuario(Context context, String usuario, String contrasenha) {
        SQLiteDatabase db = getConnRead(context);

        String query = "SELECT * FROM Usuario u WHERE u.Usuario = ? AND u.Contrasenha = ?";
        Cursor cursor = db.rawQuery(query, new String[]{usuario, contrasenha});

        Usuario user = null;

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            user = new Usuario();
            int index;

            index = cursor.getColumnIndexOrThrow("id");
            user.setUsuarioID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("Nombre");
            user.setNombre(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("TipoAccesoID");
            user.setTipoAccesoID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("TarjetaID");
            user.setTarjetaID(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("Telefono");
            user.setTelefono(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Usuario");
            user.setUsuario(String.valueOf(cursor.getInt(index)));

            index = cursor.getColumnIndexOrThrow("Correo");
            user.setCorreo(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("Contrasenha");
            user.setContrasenha(cursor.getString(index));


        }

        return user;
    }

    // 2 Activo 1 Solicitud Pendinete 0 Inactivo

    public Cursor selectRestaurantes(Context context) {
        SQLiteDatabase db = getConnRead(context);
        String query = "SELECT * FROM Restaurante WHERE Activo > 0";
        return db.rawQuery(query, null);
    }

    public Cursor selectRestauranteID(Context context, String id) {
        SQLiteDatabase db = getConnRead(context);
        String query = "SELECT * FROM Restaurante WHERE id = ? AND Activo > 0";
        return db.rawQuery(query, new String[]{id});
    }

    public Cursor selectRestauranteNom(Context context, String name) {
        SQLiteDatabase db = getConnRead(context);
        String query = "SELECT * FROM Restaurante WHERE Nombre = ?";
        return db.rawQuery(query, new String[]{name});
    }

    public Cursor selectUsuarioID(Context context, String id) {
        SQLiteDatabase db = getConnRead(context);
        String query = "SELECT * FROM Usuario WHERE id = ?";
        return db.rawQuery(query, new String[]{id});
    }

    public Cursor selectOrdenesPendientes(Context context) {
        SQLiteDatabase db = getConnRead(context);
        String query = "SELECT * FROM Orden WHERE enCamino < 2";
        return db.rawQuery(query, null);
    }

    public Cursor selectEncargados(Context context) {
        SQLiteDatabase db = getConnRead(context);
        String query = "SELECT * FROM Usuario WHERE TipoAccesoID = 4";
        return db.rawQuery(query, null);
    }

    public Cursor selectDireccion(Context context, String id) {
        SQLiteDatabase db = getConnRead(context);
        String query = "SELECT * FROM Direccion WHERE id = ?";
        return db.rawQuery(query, new String[]{id});
    }

    public Cursor selectDireccionNom(Context context, String name) {
        SQLiteDatabase db = getConnRead(context);
        String query = "SELECT * FROM Direccion WHERE Nombre = ?";
        return db.rawQuery(query, new String[]{name});
    }

    public Cursor selectSolicitudesEliminacion(Context context) {
        SQLiteDatabase db = getConnRead(context);
        String query = "SELECT * FROM Restaurante WHERE Activo = 1";
        return db.rawQuery(query, null);
    }

    public Cursor selectRestaurantesXGerente(Context context, String idGerente) {   // Probar INNER JOIN
        SQLiteDatabase db = getConnRead(context);
        String query =
                "SELECT r.* FROM Restaurante r " +
                "INNER JOIN RestauranteXGerente rxg ON r.id = rxg.RestauranteID " +
                "WHERE rxg.UsuarioID = ? AND r.Activo > 0";
        return db.rawQuery(query, new String[]{idGerente});
    }

    public Cursor selectPedidosDeRestaurante(Context context, String idRestaurante) {
        SQLiteDatabase db = getConnRead(context);
        String query =
                "SELECT lf.*, u.Nombre FROM LineaFactura lf " +
                "INNER JOIN Orden o ON o.id = lf.OrdenID " +
                "INNER JOIN Usuario u ON u.id = o.ClienteID " +
                "WHERE o.RestauranteID = ?";
        return db.rawQuery(query, new String[]{idRestaurante});
    }

    public int updateOrdenEnCamino(Context context, String value, String id){

        SQLiteDatabase db = getConnRead(context);

        // New value for one column
        ContentValues values = new ContentValues();
        values.put("enCamino", value);

    // Which row to update, based on the title
        String selection = "id = ?";
        String[] selectionArgs = { id };

        int count = db.update(
                "Orden",
                values,
                selection,
                selectionArgs);

        return count;
    }


    public int updateOrdenRepartidorID(Context context, String value, String ordenid) {

        SQLiteDatabase db = getConnRead(context);

        // New value for one column
        ContentValues values = new ContentValues();
        values.put("RepartidorID", value);

        // Which row to update, based on the title
        String selection = "id = ?";
        String[] selectionArgs = {ordenid};

        int count = db.update(
                "Orden",
                values,
                selection,
                selectionArgs);

        return count;
    }

    public Cursor selectDireccionXCliente(Context context, String idCliente) {
        SQLiteDatabase db = getConnRead(context);
        String query =
                "SELECT d.* FROM Direccion d " +
                        "INNER JOIN DireccionXCliente dxc ON d.id = dxc.DireccionID " +
                        "WHERE dxc.UsuarioID = ?";
        return db.rawQuery(query, new String[]{idCliente});
    }

    public int getLastID(Context context, String table) {
        SQLiteDatabase db = getConnRead(context);
        String query = "SELECT MAX(id) FROM " + table;
        Cursor cursor = db.rawQuery(query, null);
        int maxID = -1;
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int index = cursor.getColumnIndexOrThrow("id");
            maxID = Math.max(maxID, cursor.getInt(index));
        }
        return maxID;
    }

    public Cursor selectOrdenesXCliente(Context context, String idCliente) {
        SQLiteDatabase db = getConnRead(context);
        String query =
                "SELECT o.* FROM Orden o " +
                        "INNER JOIN Usuario u ON u.id = o.ClienteID " +
                        "WHERE u.id = ?";
        return db.rawQuery(query, new String[]{idCliente});

    }
    
}
