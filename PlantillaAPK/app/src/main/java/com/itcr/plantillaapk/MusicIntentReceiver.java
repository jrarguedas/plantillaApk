package com.itcr.plantillaapk;


import android.content.Context;
import android.content.Intent;


public class MusicIntentReceiver extends android.content.BroadcastReceiver {

    Stream stream;

    public MusicIntentReceiver(Stream newStream){
        stream = newStream;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(
                android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY)) {
            stream.stop();
        }
    }
}
