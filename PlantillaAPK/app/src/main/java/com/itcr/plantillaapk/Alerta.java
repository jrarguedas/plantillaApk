package com.itcr.plantillaapk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import java.io.IOException;

public class Alerta extends AppCompatActivity {

    private Notificacion notificacion;
    private Context contextoMain;

    public Alerta(Context nuevoContexto){
        contextoMain = nuevoContexto;
        notificacion = Notificacion.construirNotificacion(nuevoContexto);
    }

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
