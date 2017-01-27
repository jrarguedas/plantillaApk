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


/**
 * *************************************************************
 * Implementado por: José Arguedas, Denis Quesada, Jean Umaña. *
 * *****************************************************+*******
 **/


/*
Esta clase se encarga de crear la pantalla de información de la aplicación.
 */
public class Reproductor extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static Radio radio;
    private Stream stream;
    private InterrupcionAudifonos interrupcionAudifonos;

    /*
    El constructor obtiene el objeto radio que se creó en la clase MainActivity, este objeto
    contiene toda la información que se leyó del archivo Json y son los datos correspondientes
    a la radio que se escuchará con la aplicación.
     */
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

    /*
    Este método es el encagado de crear la vista de la pantalla de Reproducción, en caso de que se
    modifique el contenido que posee esta pantalla es necesario modificar este método para mostrar
    dicha nueva información.
    Desde este método se crea el objeto stream que es el encargado de manejar la lógica
    correspondiente al reproductor, también es aquí donde se crea una instancia de la clase
    InterrupcionAudifonos que es la encargada de detener el stream cuando los audífonos son
    retirados del dispositivo, para así evitar posibles momentos vergonzos al usuario
    de la aplicación.
     */
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
            /*
            Se instancia el objeto stream para controlar la lógica del reproductor y el objeto de
            la clase IterrupcionAudifonos para controlar la interrupción correspondiente al retiro
            de los audífonos del dispositivo.
             */
            stream = Stream.construirStream(radio.getStreamURL(),context);
            interrupcionAudifonos = new InterrupcionAudifonos();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Aquí se inician el listener del botón play del reproductor, en caso de que estos botones
        sean presionados, se ejecuta el método correspondiente en la clase stream.
         */
        imgPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    stream.play();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        /*Aquí se inician los listeners de los botones pausa y stop del reproductor, en caso de que
        estos botones sean presionados, se ejecuta el método correspondiente en la clase stream.
         */
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