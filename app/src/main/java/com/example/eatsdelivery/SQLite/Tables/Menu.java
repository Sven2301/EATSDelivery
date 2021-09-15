package com.example.eatsdelivery.SQLite.Tables;

public class Menu {

    private String menuID, restauranteID, platoID, cantidadDisponible;

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getRestauranteID() {
        return restauranteID;
    }

    public void setRestauranteID(String restauranteID) {
        this.restauranteID = restauranteID;
    }

    public String getPlatoID() {
        return platoID;
    }

    public void setPlatoID(String platoID) {
        this.platoID = platoID;
    }

    public String getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(String cantidadDisponibl) {
        this.cantidadDisponible = cantidadDisponibl;
    }
}
