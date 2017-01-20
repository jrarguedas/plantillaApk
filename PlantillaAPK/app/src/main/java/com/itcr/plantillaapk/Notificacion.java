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

    public NotificationManager notificacion(Context context){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(android.R.drawable.presence_audio_online)
                        .setLargeIcon((((BitmapDrawable)context.getResources()
                                .getDrawable(R.drawable.ic_audiotrack)).getBitmap()))
                        .setContentTitle("Aplicación en curso")
                        .setContentText("La aplicación continua en ejecución")

                        .setTicker("Notificación!")
                        .setPriority(Notification.PRIORITY_LOW)
                        .setOngoing(true)
                        .setAutoCancel(true);

        Intent notIntent =
                new Intent(context, MainActivity.class);
        notIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent contIntent =
                PendingIntent.getActivity(
                        context, 0, notIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(contIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(NOTIF_ALERTA_ID, mBuilder.build());

        return mNotificationManager;
    }

    public void finalizarNotificacion(NotificationManager mNotificationManager){
        mNotificationManager.cancel(NOTIF_ALERTA_ID);
    }
}
