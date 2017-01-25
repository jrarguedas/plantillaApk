package com.itcr.plantillaapk;

public class Radio {
    private String nombre, descripcion, streamURL, urlPagina, logo, color, puntoMontaje;
    private static Radio radio;

    private Radio() {}

    public static Radio construirRadio() {
        if (radio == null){
            radio = new Radio();
        }
        return radio;
    }

    public String getPuntoMontaje() {return puntoMontaje;}

    public void setPuntoMontaje(String puntoMontaje) {this.puntoMontaje = puntoMontaje;}

    public String getUrlPagina() {
        return urlPagina;
    }

    public void setUrlPagina(String urlPagina) {
        this.urlPagina = urlPagina;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getStreamURL() {
        return streamURL;
    }

    public void setStreamURL(String streamURL) {
        this.streamURL = streamURL;
    }
}
