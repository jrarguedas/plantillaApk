package com.itcr.plantillaapk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Reproductor extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public Reproductor() {}

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
        return vistaRaiz;
    }
}
