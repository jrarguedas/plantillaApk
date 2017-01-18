package com.itcr.plantillaapk;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class Stream {
    MediaPlayer mediaPlayer;
    Uri radioUrl;

    public Stream(String url, Context context) throws IOException {
        inicializar(url,context);

    }

    public void inicializar(String url, Context context) throws IOException {
        mediaPlayer = new MediaPlayer();
        radioUrl = Uri.parse(url);
        mediaPlayer.setDataSource(context, radioUrl);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.prepare();
    }

    public void destruir(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer =null;
        }
    }

    public void play(String url,Context context) throws IOException {
        if (!estado()){
            destruir();
            inicializar(url, context);
            mediaPlayer.start();
        }

    }

    public void pause(){
        if(mediaPlayer!=null && estado()){
            mediaPlayer.pause();
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
}
