package com.itcr.plantillaapk;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by jrarguedas on 14/01/17.
 */

public class Stream {
    MediaPlayer mediaPlayer;
    Uri radioUrl;

    public Stream(MediaPlayer mediaPlayer) {
        this.mediaPlayer = new MediaPlayer();
    }

    public void inicializar(MediaPlayer mediaPlayer, String url, Context context) throws IOException {
        radioUrl = Uri.parse(url);
        mediaPlayer.setDataSource(context, radioUrl);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.prepare();
    }

    public void play(MediaPlayer mediaPlayer){
        mediaPlayer.start();
    }

    public void pause(MediaPlayer mediaPlayer){
        mediaPlayer.pause();
    }

    public void stop(MediaPlayer mediaPlayer){
        mediaPlayer.stop();
    }

    public boolean estado(MediaPlayer mediaPlayer){
        if (mediaPlayer.isPlaying()){
            return true;
        }
        return false;
    }
}
