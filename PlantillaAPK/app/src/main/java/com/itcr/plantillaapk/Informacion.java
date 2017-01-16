package com.itcr.plantillaapk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by jrarguedas on 14/01/17.
 */

public class Informacion extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public Informacion() {

    }

    public static Informacion newInstance(int sectionNumber) {
        Informacion informacion = new Informacion();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        informacion.setArguments(args);
        return informacion;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaRaiz = inflater.inflate(R.layout.fragment_pantalla_informacion, container, false);
        ScrollView vistaScroll = (ScrollView) vistaRaiz.findViewById(R.id.informacionVistaScroll);
        ImageView vistaImagen = (ImageView) vistaRaiz.findViewById(R.id.imagenLogo);
        TextView vistaTexto = (TextView) vistaRaiz.findViewById(R.id.textoVisitanos);
        return vistaRaiz;
    }
}
