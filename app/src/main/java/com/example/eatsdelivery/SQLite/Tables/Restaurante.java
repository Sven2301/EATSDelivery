package com.example.eatsdelivery.SQLite.Tables;

public class Restaurante {

    private String restauranteID, direccionID, nombre, tipoComidaID, deshabilitar;

    public String getRestauranteID() {
        return restauranteID;
    }

    public void setRestauranteID(String restauranteID) {
        this.restauranteID = restauranteID;
    }

    public String getDireccionID() {
        return direccionID;
    }

    public void setDireccionID(String direccionID) {
        this.direccionID = direccionID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoComidaID() {
        return tipoComidaID;
    }

    public void setTipoComidaID(String tipoComidaID) {
        this.tipoComidaID = tipoComidaID;
    }

    public String getDeshabilitar() {
        return deshabilitar;
    }

    public void setDeshabilitar(String deshabilitar) {
        this.deshabilitar = deshabilitar;
    }
}
