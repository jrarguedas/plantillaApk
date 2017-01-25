package com.itcr.plantillaapk;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Toast;
import java.io.IOException;

public class Stream  extends Activity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener {

    private static Stream stream;
    private MediaPlayer mediaPlayer;
    private Uri uri;
    private Context contextoMain;
    private String urlRadio;
    private boolean pause;

    private Stream(String url, Context nuevocontexto) throws IOException {
        this.urlRadio = url;
        this.contextoMain = nuevocontexto;
        inicializar();
        this.pause = false;
    }

    public static Stream construirStream(String url, Context nuevocontexto) throws IOException {
        if (stream == null){
            stream = new Stream(url, nuevocontexto);
        }
        return stream;
    }

    public void inicializar() throws IOException {
        mediaPlayer = new MediaPlayer();
        uri = Uri.parse(urlRadio);

        try {
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(contextoMain, uri);
            mediaPlayer.prepareAsync();
            Toast.makeText(contextoMain, "Cargando......", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(contextoMain, "La radio no esta transmitiendo", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

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

    @Override
    public void onPrepared(MediaPlayer mp) {
        Toast.makeText(contextoMain, "Stream esta preparado", Toast.LENGTH_SHORT).show();
        mp.start();
    }

    public void pause(){
        if(mediaPlayer!=null && estado()){
            mediaPlayer.pause();
            pause=true;

        }
    }

    public void stop(){
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            reset();
        }
    }


    public void reset(){
        mediaPlayer.reset();
    }

    public boolean estado(){
        if (mediaPlayer.isPlaying()){
            return true;
        }
        return false;
    }

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
