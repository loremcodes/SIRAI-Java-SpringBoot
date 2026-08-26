package com.assignment.sirai.dto;

public class RespuestaLogin {
    private boolean exito;
    private String mensaje;
    private String nombreUsuario;

    public RespuestaLogin() {}

    public RespuestaLogin(boolean exito, String mensaje, String nombreUsuario){
        this.exito = exito;
        this.mensaje = mensaje;
        this.nombreUsuario = nombreUsuario;
    }

    public boolean isExito() { return exito; }
    public void setExito(boolean exito) { this.exito = exito; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

}
