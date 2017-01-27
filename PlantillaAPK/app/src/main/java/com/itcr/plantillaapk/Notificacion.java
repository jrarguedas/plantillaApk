package com.itcr.plantillaapk;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.NotificationCompat;


/**
 * *************************************************************
 * Implementado por: José Arguedas, Denis Quesada, Jean Umaña. *
 * *****************************************************+*******
 **/


/*
Esta clase es la encargada de mostrar la notificación a la hora de minimizar la APP, además esta
sigue un patron de diseño Singleton debido a que solo se necesita un objeto de esta, y el mismo
objeto es requerido en distintas clases.
*/
public class Notificacion {

    private static final int NOTIF_ALERTA_ID = 1;
    private Context contextoMain;
    private static Notificacion notificacion;
    private NotificationManager administradorNotificaciones;

    private Notificacion(Context nuevoContexo){
        this.contextoMain = nuevoContexo;
    }

    public static Notificacion construirNotificacion(Context nuevoContexo) {
        if (notificacion == null){
            notificacion = new Notificacion(nuevoContexo);
        }

        return notificacion;
    }

    /*
    Este método es el  encargado de desplegar la notificación con los valores necesarios para este
    caso (titulo, icono, texto, prioridad, entre otros).
    */
    public void nuevaNotificacion(){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(contextoMain)
                        .setSmallIcon(android.R.drawable.presence_audio_online)
                        .setLargeIcon((((BitmapDrawable) contextoMain.getResources()
                                .getDrawable(R.drawable.ic_audiotrack)).getBitmap()))
                        .setContentTitle("Aplicación en curso")
                        .setContentText("La aplicación continua en ejecución")
                        .setTicker("Notificación!")
                        .setPriority(Notification.PRIORITY_LOW)
                        .setOngoing(true)
                        .setAutoCancel(true);

        Intent notIntent =
                new Intent(contextoMain, MainActivity.class);
        notIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent contIntent =
                PendingIntent.getActivity(
                        contextoMain, 0, notIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(contIntent);

        administradorNotificaciones =
                (NotificationManager) contextoMain.getSystemService(Context.NOTIFICATION_SERVICE);

        administradorNotificaciones.notify(NOTIF_ALERTA_ID, mBuilder.build());
    }

    /*
    Este método es el encargado de destruir la notificación cuando ya no es necesario que se muestre.
    */
    public void finalizarNotificacion(){
        if (administradorNotificaciones != null){
            administradorNotificaciones.cancel(NOTIF_ALERTA_ID);
        }
    }
}
