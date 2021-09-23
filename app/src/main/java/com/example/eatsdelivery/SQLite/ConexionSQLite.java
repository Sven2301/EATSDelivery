package com.example.eatsdelivery.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLite extends SQLiteOpenHelper {


    final String SQL_CREATE_TipoAcceso = "CREATE TABLE TipoDeAcceso (id INTEGER PRIMARY KEY AUTOINCREMENT, Descripcion TEXT, Tipo INTEGER)";
    final String SQL_CREATE_Usuario    = "CREATE TABLE Usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, TipoAccesoID INTEGER, Usuario TEXT, Contrasenha TEXT, Nombre TEXT," +
            "Apellido TEXT, Telefono TEXT, Correo TEXT, TarjetaID INTEGER," +
            "FOREIGN KEY(TipoAccesoID) REFERENCES TipoDeAcceso(id), FOREIGN KEY(TarjetaID) REFERENCES Tarjeta(id))";
    final String SQL_CREATE_TipoComida = "CREATE TABLE TipoDeComida (id INTEGER PRIMARY KEY AUTOINCREMENT, Descripcion TEXT)";
    final String SQL_CREATE_Tarjeta = "CREATE TABLE Tarjeta (id INTEGER PRIMARY KEY AUTOINCREMENT, NombrePropietario TEXT, " +
            "NumeroTarjeta INTEGER, CCV INTEGER, FechaVencimiento TEXT)";
    final String SQL_CREATE_Plato = "CREATE TABLE Plato (id INTEGER PRIMARY KEY AUTOINCREMENT, Nombre TEXT, Costo INTEGER, Descripcion TEXT, TipoComidaID INTEGER, ImagenID TEXT," +
            "FOREIGN KEY(TipoComidaID) REFERENCES TipoComida(id))";
    final String SQL_CREATE_Direccion = "CREATE TABLE Direccion (id INTEGER PRIMARY KEY AUTOINCREMENT, Nombre TEXT, Descripcion TEXT, Activo TEXT)";
    final String SQL_CREATE_Restaurante = "CREATE TABLE Restaurante (id INTEGER PRIMARY KEY AUTOINCREMENT, DireccionID INTEGER," +
            " Nombre TEXT, Activo INTEGER, UsuarioID INTEGER, Telefono TEXT, Correo TEXT, FOREIGN KEY(DireccionID) REFERENCES Direccion(id))";
    final String SQL_CREATE_ResxGer = "CREATE TABLE RestauranteXGerente (id INTEGER PRIMARY KEY AUTOINCREMENT, RestauranteID INTEGER," +
            " UsuarioID INTEGER," +
            " FOREIGN KEY(RestauranteID) REFERENCES Restaurante(id), FOREIGN KEY(UsuarioID) REFERENCES Usuario(id))";
    final String SQL_CREATE_Orden = "CREATE TABLE Orden (id INTEGER PRIMARY KEY AUTOINCREMENT, ClienteID INTEGER," +
            " RepartidorID INTEGER, RestauranteID INTEGER, DireccionID INTEGER, costoTotal INTEGER, enCamino INTEGER, Factura TEXT," +
            " FOREIGN KEY(ClienteID) REFERENCES Usuario(id), FOREIGN KEY(RepartidorID) REFERENCES Usuario(id)," +
            "FOREIGN KEY(RestauranteID) REFERENCES Restaurante(id), FOREIGN KEY(DireccionID) REFERENCES Direccion(id))";
    final String SQL_CREATE_Menu = "CREATE TABLE Menu (id INTEGER PRIMARY KEY AUTOINCREMENT, RestauranteID INTEGER," +
            " PlatoID INTEGER, CantidadDisponible TEXT, FOREIGN KEY(RestauranteID) REFERENCES Restaurante(id)," +
            "FOREIGN KEY(PlatoID) REFERENCES Plato(id))";
    final String SQL_CREATE_LineaFactura = "CREATE TABLE LineaFactura (id INTEGER PRIMARY KEY AUTOINCREMENT, PlatoID INTEGER," +
            " OrdenID INTEGER, Cantidad INTEGER, FOREIGN KEY(OrdenID) REFERENCES Orden(id)," +
            "FOREIGN KEY(PlatoID) REFERENCES Plato(id))";
    final String SQL_CREATE_DirXClient = "CREATE TABLE DireccionXCliente (id INTEGER PRIMARY KEY AUTOINCREMENT, DireccionID INTEGER," +
            " UsuarioID INTEGER, FOREIGN KEY(DireccionID) REFERENCES Direccion(id)," +
            "FOREIGN KEY(UsuarioID) REFERENCES Usuario(id))";



    public ConexionSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TipoAcceso);
        sqLiteDatabase.execSQL(SQL_CREATE_Tarjeta);
        sqLiteDatabase.execSQL(SQL_CREATE_Usuario);
        sqLiteDatabase.execSQL(SQL_CREATE_TipoComida);
        sqLiteDatabase.execSQL(SQL_CREATE_Plato);
        sqLiteDatabase.execSQL(SQL_CREATE_Direccion);
        sqLiteDatabase.execSQL(SQL_CREATE_Restaurante);
        sqLiteDatabase.execSQL(SQL_CREATE_ResxGer);
        sqLiteDatabase.execSQL(SQL_CREATE_Orden);
        sqLiteDatabase.execSQL(SQL_CREATE_Menu);
        sqLiteDatabase.execSQL(SQL_CREATE_LineaFactura);
        sqLiteDatabase.execSQL(SQL_CREATE_DirXClient);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
