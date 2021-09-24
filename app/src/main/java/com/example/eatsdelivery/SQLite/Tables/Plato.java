package com.example.eatsdelivery.SQLite.Tables;

public class Plato {

    private String platoID;
    private String nombre;
    private String costo;
    private String descripcion;
    private String cant = "0";
    private String image;
    private String tipoComidaID;

    public String getCant() {
        return cant;
    }

    public void setCant(String cant) {
        this.cant = cant;
    }

    public String getTipoComidaID() {
        return tipoComidaID;
    }

    public void setTipoComidaID(String tipoComidaID) {
        this.tipoComidaID = tipoComidaID;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


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
