package com.example.eatsdelivery.SQLite.Tables;

public class Direccion {

    private String direccionID;
    private String nombre;
    private String descripcion;

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    private String activo;

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
}
