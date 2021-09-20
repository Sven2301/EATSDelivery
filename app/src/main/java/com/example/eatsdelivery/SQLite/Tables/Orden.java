package com.example.eatsdelivery.SQLite.Tables;

public class Orden {

    private String ordenID, clienteID, repartidorID, restauranteID, direccionID, costoTotal, enCamino;

    public String getOrdenID() {
        return ordenID;
    }

    public void setOrdenID(String ordenID) {
        this.ordenID = ordenID;
    }

    public String getClienteID() {
        return clienteID;
    }

    public void setClienteID(String clienteID) {
        this.clienteID = clienteID;
    }

    public String getRepartidorID() {
        return repartidorID;
    }

    public void setRepartidorID(String repartidorID) {
        this.repartidorID = repartidorID;
    }

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

    public String getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(String costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getEnCamino() {
        return enCamino;
    }

    public void setEnCamino(String enCamino) {
        this.enCamino = enCamino;
    }
}
