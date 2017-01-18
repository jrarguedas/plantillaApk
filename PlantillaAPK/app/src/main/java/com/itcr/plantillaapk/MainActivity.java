package com.itcr.plantillaapk;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private AdaptadorPagina adaptadorPagina;

    private ViewPager vistaPagina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*ASÍ ES COMO LO EJECUTE, PRUEBO LEYENDO EL ARCHIVO
          LUEGO A LA FUNCIÓN "obtenerDatos" LE PASO EL STRING
          QUE CONTIENE LO DEL ARCHIVO Y EL OBJETO TIPO RADIO
          Y SE LE SETEAN LOS VALORES, EL RESUNTADO SE MUESTRA
          EN CONSOLA*/
        
        /*Inicializador prueba = new Inicializador();
        Radio radio = new Radio();

        try {
            String archivo = prueba.obtenerJson(this);
            prueba.obtenerDatos(archivo, radio);

            System.out.println("Nombre Radio : " + radio.getNombre());
            System.out.println("Color APP: " + radio.getColor());
            System.out.println("Pagina de la radio: " + radio.getUrlPagina());
            System.out.println("URL del stream: " + radio.getStreamURL());
            System.out.println("Punto de montaje: " + radio.getPuntoMontaje());
            System.out.println("La descripcion: " + radio.getDescripcion());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        setContentView(R.layout.activity_main);

        Toolbar barraTareas = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(barraTareas);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        adaptadorPagina = new AdaptadorPagina(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        vistaPagina = (ViewPager) findViewById(R.id.container);
        vistaPagina.setAdapter(adaptadorPagina);

    }

    @Override
    public void onBackPressed() {
        new Alerta().alertaSalir(this);
    }
}
