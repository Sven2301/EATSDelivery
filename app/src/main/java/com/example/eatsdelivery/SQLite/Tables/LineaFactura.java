package com.example.eatsdelivery.SQLite.Tables;

public class LineaFactura {

    private String lineaFacturaID, platoID, cantidad, ordenID;

    public String getLineaFacturaID() {
        return lineaFacturaID;
    }

    public void setLineaFacturaID(String lineaFacturaID) {
        this.lineaFacturaID = lineaFacturaID;
    }

    public String getPlatoID() {
        return platoID;
    }

    public void setPlatoID(String platoID) {
        this.platoID = platoID;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getOrdenID() {
        return ordenID;
    }

    public void setOrdenID(String ordenID) {
        this.ordenID = ordenID;
    }
}
