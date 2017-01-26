package com.itcr.plantillaapk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
Esta clase es la encargada de mostrar la pantalla de presentación, es una nueva actividad que se
llama al iniciar la aplicación. Después de un determinado periodo de tiempo esta actividad se
finaliza y llama a otra actividad (reproductor) para que continué el flujo de la APP.
 */
public class PantallaPresentacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_presentacion);
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2500);
                    Intent intento = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intento);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
