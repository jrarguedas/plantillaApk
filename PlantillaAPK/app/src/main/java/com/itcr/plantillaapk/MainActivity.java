package com.itcr.plantillaapk;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;


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

    @Override
    protected void onDestroy() {
        if (mNotificationManager != null){
            notificacion.finalizarNotificacion(mNotificationManager);
        }
        super.onDestroy();
    }
}

