package com.example.eatsdelivery.SQLite.Tables;

public class Usuario {

    private String usuarioID, usuario, contrasenha, tipoAccesoID;

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getContrasenna() {
        return contrasenha;
    }

    public void setContrasenna(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getTipoAccesoID() {
        return tipoAccesoID;
    }

    public void setTipoAccesoID(String tipoAccesoID) {
        this.tipoAccesoID = tipoAccesoID;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


}
