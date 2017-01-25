package com.itcr.plantillaapk;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.NotificationCompat;

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

    public void finalizarNotificacion(){
        if (administradorNotificaciones != null){
            administradorNotificaciones.cancel(NOTIF_ALERTA_ID);
        }
    }
}
