package com.example.eatsdelivery.SQLite.Tables;

public class Direccion {

    private String direccionID, nombre, descripcion, direccionExacta;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccionExacta() {
        return direccionExacta;
    }

    public void setDireccionExacta(String direccionExacta) {
        this.direccionExacta = direccionExacta;
    }
}
