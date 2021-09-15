package com.example.eatsdelivery.SQLite.Tables;

public class Plato {

    private String platoID, nombre, costo, descripcion;

    public String getPlatoID() {
        return platoID;
    }

    public void setPlatoID(String platoID) {
        this.platoID = platoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
