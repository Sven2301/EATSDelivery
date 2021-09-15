package com.example.eatsdelivery.SQLite.Tables;

public class InfoUsuario {

    private String infoUsuarioID, usuarioID, nombre, telefono, correo, tarjetaID, placaVehiculo;

    public String getInfoUsuarioID() {
        return infoUsuarioID;
    }

    public void setInfoUsuarioID(String infoUsuarioID) {
        this.infoUsuarioID = infoUsuarioID;
    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTarjetaID() {
        return tarjetaID;
    }

    public void setTarjetaID(String tarjetaID) {
        this.tarjetaID = tarjetaID;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }
}
