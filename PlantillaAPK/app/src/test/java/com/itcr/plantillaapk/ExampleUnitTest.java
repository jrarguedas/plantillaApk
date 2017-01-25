package com.itcr.plantillaapk;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.PowerManager;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public void testPlayAudio() throws Exception {

        MediaPlayer mediaPlayer = new MediaPlayer();
        Stream st = new Stream();
        Uri uri = Uri.parse("http://stream.codigosur.org/espikafm.mp3");

        try {
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(context, uri);
            mediaPlayer.prepare();

        } finally {
            mediaPlayer.release();
        }

    }