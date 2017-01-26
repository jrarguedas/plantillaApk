package com.itcr.plantillaapk;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.test.InstrumentationTestCase;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class StreamTest extends InstrumentationTestCase implements MediaPlayer.OnPreparedListener {

    public StreamTest() throws IOException {
    }


    @Test
    public void inicializar() throws Exception {



    }

    @Test
    public void play() throws Exception {
        MediaPlayer mediaPlayer = new MediaPlayer();
        String url ="http://stream.codigosur.org/RadioUniversidad.mp3";
        Uri uri = Uri.parse(url);
        Stream stream = Stream.construirStream(url,getInstrumentation().getContext());
        mediaPlayer.setOnPreparedListener((MediaPlayer.OnPreparedListener) this);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(getInstrumentation().getContext(), uri);
        mediaPlayer.prepareAsync();
        stream.play();
        assertTrue(mediaPlayer.isPlaying());

    }

    @Test
    public void onPrepared() throws Exception {

    }

    @Test
    public void pause() throws Exception {

        MediaPlayer mediaPlayer = new MediaPlayer();
        String url ="http://stream.codigosur.org/RadioUniversidad.mp3";
        Uri uri = Uri.parse(url);
        Stream stream = Stream.construirStream(url,getInstrumentation().getContext());

        mediaPlayer.setOnPreparedListener((MediaPlayer.OnPreparedListener) this);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(getInstrumentation().getContext(), uri);
        mediaPlayer.prepare();
        stream.pause();
        assertFalse(mediaPlayer.isPlaying());
        stream.play();
        assertTrue(mediaPlayer.isPlaying());

    }

    @Test
    public void stop() throws Exception {
        MediaPlayer mediaPlayer = new MediaPlayer();
        String url ="http://stream.codigosur.org/RadioUniversidad.mp3";
        Uri uri = Uri.parse(url);
        Stream stream = Stream.construirStream(url,getInstrumentation().getContext());

        mediaPlayer.setOnPreparedListener((MediaPlayer.OnPreparedListener) this);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(getInstrumentation().getContext(), uri);
        mediaPlayer.prepare();
        mediaPlayer.start();

        stream.stop();
        stream.reset();
        mediaPlayer.prepare();
        assertFalse(mediaPlayer.isPlaying());
        stream.play();
        assertTrue(mediaPlayer.isPlaying());

    }

    @Test
    public void reset() throws Exception {

    }

    @Test
    public void estado() throws Exception {
        MediaPlayer mediaPlayer = new MediaPlayer();
        String url ="http://stream.codigosur.org/RadioUniversidad.mp3";
        Uri uri = Uri.parse(url);
        Stream stream = Stream.construirStream(url,getInstrumentation().getContext());

        mediaPlayer.setOnPreparedListener((MediaPlayer.OnPreparedListener) this);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(getInstrumentation().getContext(), uri);
        mediaPlayer.prepareAsync();
        stream.play();
        assertTrue(stream.estado());
        stream.stop();
        assertFalse(stream.estado());


    }

    @Test
    public void destruir() throws Exception {
        MediaPlayer mediaPlayer = new MediaPlayer();
        String url ="http://stream.codigosur.org/RadioUniversidad.mp3";
        Uri uri = Uri.parse(url);
        Stream stream = Stream.construirStream(url,getInstrumentation().getContext());

        mediaPlayer.setOnPreparedListener((MediaPlayer.OnPreparedListener) this);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(getInstrumentation().getContext(), uri);
        mediaPlayer.prepareAsync();
        stream.play();
        stream.stop();
        stream.destruir();
        assertTrue(mediaPlayer==null);

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}