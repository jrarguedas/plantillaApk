package com.itcr.plantillaapk;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Toast;
import java.io.IOException;


/**
 * *************************************************************
 * Implementado por: José Arguedas, Denis Quesada, Jean Umaña. *
 * *****************************************************+*******
 **/


/*
La clase stream maneja la lógica correspondiente al control del streaming que se escucha la
aplicación, los botones que se encuentran en la pantalla de Reproductor, llaman a los métodos
que se encuentran en esta clase.
Esta clase también sigue el patrón de diseño Singleton.
 */
public class Stream  extends Activity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener {

    private static Stream stream;
    //Se utiliza el reproductor que provee android para poder realizar la reproducción del streaming.
    private MediaPlayer mediaPlayer;
    private Uri uri;
    private Context contextoMain;
    private String urlRadio;
    private boolean pause;

    /*
    Al crear un nuevo objeto stream, se pasa como parámetro la url donde se está reproduciendo el
    streaming y el contexto en el que se encuentre.
     */
    private Stream(String url, Context nuevocontexto) throws IOException {
        this.urlRadio = url;
        this.contextoMain = nuevocontexto;
        inicializar();
        this.pause = false;
    }

    /*
    Este método se encarga de mantener el patrón de diseño Singleton, se necesita este patrón ya
    que la información que se encuentra dentro del objeto de esta clase es requerida en otras.
     */
    public static Stream construirStream(String url, Context nuevocontexto) throws IOException {
        /*
        Si no se ha creado un objeto stream, entonces se ejecuta su constructor creando así una
        instancia de esta clase, pero si ya se ha creado una instancia entonces es esa instancia
        la que se devuelve. Este es el patrón de diseño Singleton, gracias a esto se puede
        referenciar a un mismo objeto desde distintas clases.
         */
        if (stream == null){
            stream = new Stream(url, nuevocontexto);
        }
        return stream;
    }

    /*
    El método inicializar se encarga de realizar la conexión a la url desde donde se puede escuchar
    el streaming de la radio.
     */
    public void inicializar() throws IOException {
        mediaPlayer = new MediaPlayer();//Se utiliza un objeto MediaPlayer para la reproducción del streaming.
        uri = Uri.parse(urlRadio);

        try {
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//Se indica al mediaPlayer que se reproducirá un stream.
            mediaPlayer.setDataSource(contextoMain, uri);
            mediaPlayer.prepareAsync();
            Toast.makeText(contextoMain, "Cargando......", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            //Se muestra un mensaje en caso de que no sea posible realizar la reproducción del streaming.
            Toast.makeText(contextoMain, "La radio no está transmitiendo en este momento", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    /*
    El método play reinicia la reproducción del streaming en caso de que este se encontrara en
    estado de pausa, si el streaming se encuentra detenido se encarga de inicializar de nuevo el
    streaming y comenzar su reproducción.
    Este método se ejecuta cuando se presiona el botón de play en la pantalla de Reproductor.
     */
    public void play() throws IOException {
        if(pause==true){
            mediaPlayer.start();
            pause=false;
        }
        else if(!estado()){
            destruir();
            inicializar();
            mediaPlayer.start();
        }

    }

    /*
    Cuando se inicia el objeto mediaPlayer en el método inicializar, este debe prepararse para
    poder empezar la reproducción, se utiliza el método prepareAsync() para llevar a cabo la
    prepración del MediaPlayer, este método realiza la preparación con un hilo distinto al hilo
    principal, mejorando así la eficiencia del programa. Cuando la preparación se ha finalizado
    ese hilo secundario ejecuta este método onPrepared, aquí se muestra un mensaje indicando que ya
    se puede terminó de preparar el reproductor.
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        Toast.makeText(contextoMain, "Stream esta preparado", Toast.LENGTH_SHORT).show();
        mp.start();
    }

    /*
    El método pause se ejecuta cuando se presiona el botón de pausa de la pantalla Reproductor, si
    el streaming se encuentra reproduciendo, este se pausará.
     */
    public void pause(){
        if(mediaPlayer!=null && estado()){
            mediaPlayer.pause();
            pause=true;

        }
    }

    /*
    El método stop se ejecuta cuando se presiona el botón de stop de la pantalla Reproductor, si el
    streaming se encuentra reproduciendo, este se detendrá.
     */
    public void stop(){
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            reset();
        }
    }

    public void reset(){
        mediaPlayer.reset();
    }

    /*
    El método estado se utiliza para chequear si el streaming se encuentra reproduciendo o no.
     */
    public boolean estado(){
        if (mediaPlayer.isPlaying()){
            return true;
        }
        return false;
    }

    /*
    El método destruir se utiliza para finalizar el objeto mediaPlayer, liberando así recursos del
    dispositivo y evitando memoryleaks.
     */
    public void destruir(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Toast.makeText(contextoMain, "PlayerService onBufferingUpdate : " + percent + "%", Toast.LENGTH_LONG).show();
    }

}
