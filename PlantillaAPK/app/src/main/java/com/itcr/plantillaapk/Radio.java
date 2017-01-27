package com.itcr.plantillaapk;


/**
 * *************************************************************
 * Implementado por: José Arguedas, Denis Quesada, Jean Umaña. *
 * *****************************************************+*******
 **/


/*
La clase radio es la que contiene la información que se leyó desde el archivo Json, son los datos
correspondientes a la radio que se puede escuchar con la aplicación.
Esta clase sigue el patrón de diseño Singleton para mejorar el proceso de ejecución del código de
la aplicación en general.
 */
public class Radio {
    /*
    Aquí se definen los datos que se desean almacenar.
     */
    private String nombre, descripcion, streamURL, urlPagina, logo, color, puntoMontaje;
    private static Radio radio;

    private Radio() {}
    /*
    Este método se encarga de implementar el patrón de diseño Singleton, de esta forma se puede
    utilizar el mismo objeto radio desde cualquier parte del código de la aplicación ya que
    devuelve el mismo objeto radio que se crea la primera vez que se llama este método.
     */
    public static Radio construirRadio() {
        if (radio == null){
            radio = new Radio();
        }
        return radio;
    }

    /*
    Aquí se definen los métodos getters y setters de los datos de la radio.
     */
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
