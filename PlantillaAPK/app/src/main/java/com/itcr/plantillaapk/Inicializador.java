package com.itcr.plantillaapk;


import android.app.Activity;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Inicializador extends Activity{
    Radio radio;


    public Inicializador(Radio r){
        radio=r;

    }

    public String obtenerJson(Context context) throws IOException {

        InputStream archivo = context.getResources().openRawResource(R.raw.datos_app);
        BufferedReader bufferArchivo = new BufferedReader(new InputStreamReader(archivo, "UTF-8"));

        String linea = bufferArchivo.readLine();

        archivo.close();
        bufferArchivo.close();
        return linea;
    }

    public Radio obtenerDatos(String jsonStr) throws JSONException {
        JSONObject jsonObj = new JSONObject(jsonStr);


        String nombreRadio = jsonObj.getString("name");
        String colorApp = jsonObj.getString("color");
        String paginaRadio = jsonObj.getString("webpage");
        String streamUrl = jsonObj.getString("stream_url");
        String puntoMontaje = jsonObj.getString("mountpoint");
        String descripcion = jsonObj.getString("description");

        /*if (!nombreRadio.equals("")){radio.setNombre(nombreRadio);}
        if (!colorApp.equals("")){radio.setColor(colorApp);}
        if (!paginaRadio.equals("")){radio.setUrlPagina(paginaRadio);}
        if (!streamUrl.equals("")){radio.setStreamURL(streamUrl);}
        if (!puntoMontaje.equals("")){radio.setPuntoMontaje(puntoMontaje);}
        if (!descripcion.equals("")){radio.setDescripcion(descripcion);}*/

        radio.setNombre(nombreRadio);
        radio.setColor(colorApp);
        radio.setUrlPagina(paginaRadio);
        radio.setStreamURL(streamUrl);
        radio.setPuntoMontaje(puntoMontaje);
        radio.setDescripcion(descripcion);

        return radio;
    }

}

