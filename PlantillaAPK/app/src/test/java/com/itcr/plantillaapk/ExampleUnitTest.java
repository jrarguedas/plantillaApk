package com.itcr.plantillaapk;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.PowerManager;
import android.test.InstrumentationTestCase;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest extends InstrumentationTestCase {


    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public void testPlayAudio() throws Exception {

        MediaPlayer mediaPlayer = new MediaPlayer();
        String url ="http://stream.codigosur.org/espikafm.mp3";
        Stream st = Stream.construirStream(url,getInstrumentation().getContext());
        Uri uri = Uri.parse(url);

        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(getInstrumentation().getContext(), uri);
            mediaPlayer.prepare();

        } finally {
            mediaPlayer.release();
        }

    }
}