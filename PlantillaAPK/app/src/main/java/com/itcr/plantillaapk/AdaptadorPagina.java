package com.itcr.plantillaapk;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AdaptadorPagina extends FragmentPagerAdapter {
    Radio radio;

    public AdaptadorPagina(FragmentManager fm,Radio r) {
        super(fm);
        radio =r;
    }

    @Override
    public Fragment getItem(int posicion) {
        if (posicion == 1){
            return Informacion.newInstance(posicion + 1,radio);
        }
        else{
            return Reproductor.newInstance(posicion + 1,radio);
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

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