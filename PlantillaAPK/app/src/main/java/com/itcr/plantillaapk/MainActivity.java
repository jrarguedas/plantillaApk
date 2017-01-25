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

    private AdaptadorPagina adaptadorPagina;
    private ViewPager vistaPagina;
    private ShareActionProvider shareAction;

    NotificationManager mNotificationManager = null;
    MediaPlayer mediaPlayer = null;
    Notificacion notificacion = new Notificacion();
    Radio radio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.compartir, menu);
        MenuItem shareItem = menu.findItem(R.id.share);

        if (shareItem != null) {
           // shareAction = (ShareActionProvider) shareItem.getActionProvider();
        }

        // Create an Intent to share your content
        share();

        return true;
    }



        public void share(){
            if (shareAction != null) {

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Escucha: " + radio.getNombre() + "," + radio.getStreamURL());
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
                shareAction.setShareIntent(shareIntent);
            }

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

        try {
            Stream stream = new Stream("", this);
            mediaPlayer = stream.getMediaPlayer();
            stream.destruir(mediaPlayer);

        } catch (IOException e) {
            e.printStackTrace();
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

