package com.itcr.plantillaapk;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    NotificationManager mNotificationManager = null;
    Notificacion notificacion = new Notificacion();

    private AdaptadorPagina adaptadorPagina;

    private ViewPager vistaPagina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Inicializador prueba = new Inicializador();
        Radio radio = new Radio();

        try {
            String archivo = prueba.obtenerJson(this);
            radio = prueba.obtenerDatos(archivo);

            /*System.out.println("Nombre Radio : " + radio.getNombre());
            System.out.println("Color APP: " + radio.getColor());
            System.out.println("Pagina de la radio: " + radio.getUrlPagina());
            System.out.println("URL del stream: " + radio.getStreamURL());
            System.out.println("Punto de montaje: " + radio.getPuntoMontaje());
            System.out.println("La descripcion: " + radio.getDescripcion());*/

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
        new Alerta().alertaSalir(this, mNotificationManager);
    }

    public void onPause(){
        mNotificationManager = notificacion.notificacion(this);
        super.onPause();
    }
}

