package com.itcr.plantillaapk;

import android.app.NotificationManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private NotificationManager mNotificationManager;
    private Notificacion notificacion;
    private AdaptadorPagina adaptadorPagina;
    private ViewPager vistaPagina;
    Radio radio;
    private ShareActionProvider shareAction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mNotificationManager = null;
        notificacion = new Notificacion();

        setContentView(R.layout.activity_main);
        Toolbar barraTareas = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(barraTareas);



        radio = new Radio();
        Inicializador inicicializador = new Inicializador(radio);

        try {
            inicicializador.obtenerDatos(inicicializador.obtenerJson(this));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        adaptadorPagina = new AdaptadorPagina(getSupportFragmentManager(),radio);

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

    @Override
    protected void onDestroy() {
        if (mNotificationManager != null){
            notificacion.finalizarNotificacion(mNotificationManager);
        }

        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        if (mNotificationManager != null){
            notificacion.finalizarNotificacion(mNotificationManager);
        }
        super.onRestart();
    }
}

