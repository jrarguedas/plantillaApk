package com.itcr.plantillaapk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/*
Esta clase se encarga de crear la pantalla de información de la aplicación.
 */
public class Informacion extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static Radio radio;

    /*
    El constructor obtiene la información de la radio, esto es posible gracias a que la clase radio sigue
    el patrón de diseño Singleton.
     */
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

    /*
    Este método es el encagado de crear la vista de la pantalla de Información, en caso de que se modifique el contenido
    que posee esta pantalla es necesario modificar este método para mostrar dicha nueva información.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaRaiz = inflater.inflate(R.layout.fragment_pantalla_informacion, container, false);

        TextView vistaTexto = (TextView) vistaRaiz.findViewById(R.id.textoInformacionLink);
        vistaTexto.setMovementMethod(LinkMovementMethod.getInstance());

        TextView descripcion = (TextView) vistaRaiz.findViewById(R.id.textoDescripcion);
        TextView urlRadio = (TextView) vistaRaiz.findViewById(R.id.textoInformacionLink);

        descripcion.setText(radio.getDescripcion());

        /*
        Esta validación se realiza para chequear si el usuario, al momento de llenar el formulario ingresó la url
        de su propia página. En caso de que si la ingresó, se crea un hipervínculo hacia esa url, en caso de no la
        ingresó el hipervínculo no se muestra.
         */
        if(!radio.getUrlPagina().equals("")){
            urlRadio.setText(Html.fromHtml("<a href=\""+ radio.getUrlPagina() + "\"> Visitanos en </a>"));
            urlRadio.setClickable(true);
            urlRadio.setMovementMethod (LinkMovementMethod.getInstance());
        }
        else{
            urlRadio.setVisibility(View.INVISIBLE);
        }

        return vistaRaiz;
    }
}
