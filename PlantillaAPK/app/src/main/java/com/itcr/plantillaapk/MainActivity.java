package com.itcr.plantillaapk;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
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
    private Notificacion notificacion;
    private AdaptadorPagina adaptadorPagina;
    private ViewPager vistaPagina;
    private Radio radio;
    private ShareActionProvider shareAction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificacion = Notificacion.construirNotificacion(this);

        Toolbar barraTareas = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(barraTareas);

        Inicializador inicicializador = new Inicializador();

        try {
            inicicializador.obtenerDatos(inicicializador.obtenerJson(this));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        radio = Radio.construirRadio();


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        adaptadorPagina = new AdaptadorPagina(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        vistaPagina = (ViewPager) findViewById(R.id.container);
        vistaPagina.setAdapter(adaptadorPagina);

    }

    @Override
    public void onBackPressed() {
        new Alerta(this).alertaSalir();
    }


    public void onPause(){
        notificacion.nuevaNotificacion();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        notificacion.finalizarNotificacion();
        try {
            Stream.construirStream("", this).destruir();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        notificacion.finalizarNotificacion();
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.compartir);
        shareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        share();
        return true;
    }

    public void share(){
        if (shareAction != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Escucha: " + radio.getNombre() + "," + radio.getStreamURL());
            shareIntent.setType("text/plain");
            shareAction.setShareIntent(shareIntent);
        }
    }
}

