package com.itcr.plantillaapk;

import android.app.Activity;
import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * *************************************************************
 * Implementado por: José Arguedas, Denis Quesada, Jean Umaña. *
 * *****************************************************+*******
 **/


/*
Esta clase se encarga de parsear el JSON, le un archivo que contiene el JSON y lo procesa.
Además estos valores obtenidos son asignados a un objeto Radio.
*/
public class ParseadorJson extends Activity{
    private Radio radio;
    private String jsonParseado;

    public ParseadorJson(){
        radio = Radio.construirRadio();
    }

    /*
    En este método es donde se le el archivo que contiene el JSON, se procesa y se
    guarda en un variable tipo String.
    */
    public void obtenerJson(Context context) throws IOException {

        InputStream archivo = context.getResources().openRawResource(R.raw.datos_app);
        BufferedReader bufferArchivo = new BufferedReader(new InputStreamReader(archivo, "UTF-8"));

        jsonParseado = bufferArchivo.readLine();

        archivo.close();
        bufferArchivo.close();
    }

    /*
    En este método es donde se obtienen los datos del JSON, además estos valores son añadidos
    al objeto Radio.
    */
    public void obtenerAgregarDatos() throws JSONException {
        JSONObject jsonObj = new JSONObject(jsonParseado);

        String nombreRadio = jsonObj.getString("name");
        String colorApp = jsonObj.getString("color");
        String paginaRadio = jsonObj.getString("webpage");
        String streamUrl = jsonObj.getString("stream_url");
        String puntoMontaje = jsonObj.getString("mountpoint");
        String descripcion = jsonObj.getString("description");
        String logoUrl = jsonObj.getString("logo_url");

        if (!paginaRadio.startsWith("http://") && !paginaRadio.startsWith("https://") && !paginaRadio.equals("")){
            paginaRadio = "http://" + paginaRadio;
        }
        else if (!colorApp.equals("")) {
            radio.setColor(colorApp);
        }

        radio.setNombre(nombreRadio);
        radio.setColor(colorApp);
        radio.setUrlPagina(paginaRadio);
        radio.setStreamURL(streamUrl);
        radio.setPuntoMontaje(puntoMontaje);
        radio.setDescripcion(descripcion);
        radio.setLogo(logoUrl);
    }

}

