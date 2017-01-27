package com.itcr.plantillaapk;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * *************************************************************
 * Implementado por: José Arguedas, Denis Quesada, Jean Umaña. *
 * *****************************************************+*******
 **/


/*
Esta clase se encarga de devolver las pantallas que se muestran en la interfaz de la aplicación.
 */

public class AdaptadorPagina extends FragmentPagerAdapter {


    public AdaptadorPagina(FragmentManager fm) {
        super(fm);
    }

    /*
    Este método devuelve la pantalla correspondiente a la posición en la que se encuentre la
    interfaz, para agregar más pantallas a la aplicación:
    1- Cree la clase que corresponda (siga como ejemplo Informacion y Reproductor).
    2- Busque y modifique el método getCount() en esta clase y haga que devuelva el número de
    pantallas que tendrá la aplciación.
    3- Busque y modifique el método getPageTitle() agregando un nuevo caso devolviendo el nombre
    que tendrá la nueva pantalla.
    4- Devuelva una nueva instancia de la clase creada según la posición que le corresponda en la
    aplicación.
     */
    @Override
    public Fragment getItem(int posicion) {
        if (posicion == 1){
            return Informacion.newInstance(posicion + 1);
        }
        else{
            return Reproductor.newInstance(posicion + 1);
        }
    }

    /*
    Este método devuelve el número total de pantallas que posee la aplicación, como la plantilla de
    la aplicación contiene solo Información y Reproductor, este método devuelve un int con valor 2.
    Si se desea agregar más pantallas, se debe modificar el valor que devuelve este método.
     */
    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    /*
    Este método devuelve el nombre de cada una de las pantallas que posee la aplicación, como la
    plantilla de la aplicación contiene solo Información y Reproductor, este método solo tiene dos
    casos devolviendo esos nombres.
    Si se desea agregar más pantallas, se debe agregar un nuevo caso devolviendo un string que
    contiene el nombre de la nueva pantalla según la posición que posee en la aplicación.
     */
    @Override
    public CharSequence getPageTitle(int posicion) {
        switch (posicion) {
            case 0:
                return "Reproductor";
            case 1:
                return "Información";
        }
        return null;
    }
}