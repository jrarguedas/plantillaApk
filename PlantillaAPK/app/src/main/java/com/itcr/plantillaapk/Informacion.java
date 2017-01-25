package com.itcr.plantillaapk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

/*

 */
public class Informacion extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static Radio radio;

    public Informacion() {
        radio = Radio.construirRadio();

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

        TextView vistaTexto = (TextView) vistaRaiz.findViewById(R.id.textoInformacionLink);
        vistaTexto.setMovementMethod(LinkMovementMethod.getInstance());

        ScrollView vistaScroll = (ScrollView) vistaRaiz.findViewById(R.id.informacionVistaScroll);
        ImageView vistaImagen = (ImageView) vistaRaiz.findViewById(R.id.imagenInformacionLogo);

        TextView descripcion = (TextView) vistaRaiz.findViewById(R.id.textoDescripcion);
        TextView urlRadio = (TextView) vistaRaiz.findViewById(R.id.textoInformacionLink);

        descripcion.setText(radio.getDescripcion());

        if(radio.getUrlPagina().equals("")){
            urlRadio.setLinksClickable(false);
            urlRadio.setText("Visitanos en");
        }
        else{
            urlRadio.setText(Html.fromHtml("<a href=\""+ radio.getUrlPagina() + "\"> Visitanos en </a>"));
            urlRadio.setClickable(true);
            urlRadio.setMovementMethod (LinkMovementMethod.getInstance());
        }

        return vistaRaiz;
    }
}
