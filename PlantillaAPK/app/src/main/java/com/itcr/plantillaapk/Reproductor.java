package com.itcr.plantillaapk;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

public class Reproductor extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static Radio radio;
    private Stream stream;
    private InterrupcionAudifonos interrupcionAudifonos;

    public Reproductor() {
        radio = Radio.construirRadio();
    }

    public static Reproductor newInstance(int sectionNumber) {
        Reproductor reproductor = new Reproductor();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        reproductor.setArguments(args);
        return reproductor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaRaiz = inflater.inflate(R.layout.fragment_pantalla_reproductor, container, false);
        TextView vistaTexto = (TextView) vistaRaiz.findViewById(R.id.textoSolicitud);
        vistaTexto.setMovementMethod(LinkMovementMethod.getInstance());

        ImageButton imgPlay = (ImageButton) vistaRaiz.findViewById(R.id.play);
        ImageButton imgStop = (ImageButton) vistaRaiz.findViewById(R.id.stop);
        ImageButton imgPausa = (ImageButton) vistaRaiz.findViewById(R.id.pause);
        final Context context = this.getContext();

        try {
            stream = Stream.construirStream(radio.getStreamURL(),context);
            interrupcionAudifonos = new InterrupcionAudifonos(context);

        } catch (IOException e) {
            e.printStackTrace();
        }

        imgPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    stream.play();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        imgPausa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                stream.pause();
            }
        });
        imgStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                stream.stop();
            }
        });

        return vistaRaiz;
    }



}