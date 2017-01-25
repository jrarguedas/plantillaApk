package com.itcr.plantillaapk;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Toast;
import java.io.IOException;

public class Stream  extends Activity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener {

    private MediaPlayer mediaPlayer;
    private Uri uri;
    private Context contextMain;
    private String urlRadio;
    private boolean pause;

    public Stream(String url, Context context) throws IOException {
        contextMain=context;
        urlRadio=url;
        inicializar();
        pause = false;
    }

    public void inicializar() throws IOException {
        mediaPlayer = new MediaPlayer();
        uri = Uri.parse(urlRadio);

        try {
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(contextMain, uri);
            mediaPlayer.prepareAsync();
            Toast.makeText(contextMain, "Cargando......", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(contextMain, "La radio no esta transmitiendo", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void play() throws IOException {
        if(pause==true){
            mediaPlayer.start();
            pause=false;
        }
        else if(!estado()){
            destruir(mediaPlayer);
            inicializar();
            mediaPlayer.start();
        }

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Toast.makeText(contextMain, "Stream esta preparado", Toast.LENGTH_SHORT).show();
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

    public void destruir(MediaPlayer mediaPlayer){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Toast.makeText(contextMain, "PlayerService onBufferingUpdate : " + percent + "%", Toast.LENGTH_LONG).show();
    }

}
