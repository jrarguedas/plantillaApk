package com.itcr.plantillaapk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import java.io.IOException;


/**
 * *************************************************************
 * Implementado por: José Arguedas, Denis Quesada, Jean Umaña. *
 * *****************************************************+*******
 **/


/*
Esta clase se encarga de mostrar un popup a la hora de clickear el botón Back del celular para
confirmar si desea salir de la aplicación.
*/
public class Alerta extends AppCompatActivity {

    private Notificacion notificacion;
    private Context contextoMain;

    public Alerta(Context nuevoContexto){
        contextoMain = nuevoContexto;
        notificacion = Notificacion.construirNotificacion(nuevoContexto);
    }


    /*
    En este método es donde se crea el AlertDialog (la notificación) que se va a mostrar
    a la hora de presionar el botón Back, y si el usuario presiona el botón de confirmación
    del popup se procede a finalizar la APP y destruir el objeto stream para liberar memoria.
    */
    public void alertaSalir(){
        AlertDialog.Builder salirApp = new AlertDialog.Builder(contextoMain);
        salirApp.setTitle("Salir");
        salirApp.setIcon(R.drawable.ic_exit_to_app_black_24dp);
        salirApp.setMessage("¿Estás seguro que deseas salir?");
        salirApp.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                notificacion.finalizarNotificacion();
                try {
                    Stream.construirStream("", contextoMain).destruir();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });

        salirApp.setNegativeButton("Cancelar", null);
        salirApp.show();
    }
}
