package com.itcr.plantillaapk;

import android.content.Context;
import android.content.Intent;
import java.io.IOException;

/*
Esta clase se realiza para contemplar el caso de que se retiren los audífonos mientras el stream se encuentra
reproduciendo, si esto sucede, el stream se detiene.
 */
public class InterrupcionAudifonos extends android.content.BroadcastReceiver {

    private Stream stream;

    public InterrupcionAudifonos() throws IOException {
        stream = Stream.construirStream("", null);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(
                android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY)) {
            stream.stop();
        }
    }


}