package com.itcr.plantillaapk;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import org.json.JSONException;
import java.io.IOException;

/*
Clase MainActivity de la aplicación, esta clase es el inicio del sistema y es aquí donde se crean los primeros elementos.
 */
public class MainActivity extends AppCompatActivity {
    private Notificacion notificacion;
    private AdaptadorPagina adaptadorPagina;
    private ViewPager vistaPagina;
    private Radio radio;
    private ShareActionProvider shareAction;

    /*
    El método onCreate se ejecuta al inicio de la aplicación, es aquí donde se inicializan todos los componentes necesarios
    para la funcionalidad del sistema.
    Es aquí donde se ejecuta el método para leer la información de la radio, además aquí es donde se crea el objeto
    adaptadorPágina que es el encargado de mostrar las distintas pantallas de la aplicación.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificacion = Notificacion.construirNotificacion(this);

        Toolbar barraTareas = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(barraTareas);

        ParseadorJson inicicializador = new ParseadorJson();

        try {
            inicicializador.obtenerJson(this);
            inicicializador.obtenerAgregarDatos();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        radio = Radio.construirRadio();
        adaptadorPagina = new AdaptadorPagina(getSupportFragmentManager());
        vistaPagina = (ViewPager) findViewById(R.id.container);
        vistaPagina.setAdapter(adaptadorPagina);
    }

    /*
    Este método se encarga de crear una instancia de Alerta mostrando el método alertaSalir cuando se preciona el botón
    "atrás" del dispositivo, esta alerta pide la confirmación del usuario para salir de la aplicación y termina la
    ejecución del sistema en caso de que el usuario lo confirme.
     */
    @Override
    public void onBackPressed() {
        new Alerta(this).alertaSalir();
    }

    /*
    Este método se encarga de crear una instancia de Notificación mostrando una notificación en la barra de tareas del
    dispositivo cuando la aplicación pasa a ejecutarse a segundo plano, esta notificación no se puede eliminar de la barra
    tareas para que el usuario no olvide que la aplicación se encuentra abierta. Cuando la aplicación se cierra por cualquier
    medio, la notificación desaparace puesto que ya no es necesaria.
     */
    public void onPause(){
        notificacion.nuevaNotificacion();
        super.onPause();
    }

    /*
    Este método se ejecuta cuando la aplicación se cierra por cualquier medio, desde este método se elimina la notificación
    de la barra de tareas que se muestra en caso de que el sistema estuviera en segundo plano, también se asegura que el
    objeto stream es destruido para así liberar los recursos utilizados por el MediaPlayer para evitar memoryleaks.
     */
    @Override
    protected void onDestroy() {
        notificacion.finalizarNotificacion();
        try {
            // Se destruye el stream evitando así memoryleaks.
            Stream.construirStream("", this).destruir();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    /*
    Este método se ejecuta cuando se recupera la aplicación desde segundo plano, la notificación que se muestra cuando
    la aplicación se encuentra en segundo plano es eliminada.
     */
    @Override
    protected void onRestart() {
        notificacion.finalizarNotificacion();
        super.onRestart();
    }

    /*
    Este método muestra los elementos que se encuentran en el layout menu.xml, en este caso muestra e inicia el botón
    de compartir que se encuentra en la esquina superior derecha de la aplicación.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.compartir);
        shareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        share();
        return true;
    }
    /*
    Método que inicializa el listener del botón de compartir, aquí se instancia el mensaje con el que se compartirá
    la emisora cuando el usuario de la aplicación así lo desee.
     */
    private void share(){
        if (shareAction != null) {
            String mensajeCompartir = "Escucha " + radio.getNombre() + " en " + radio.getStreamURL();
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, mensajeCompartir);
            shareIntent.setType("text/plain");
            shareAction.setShareIntent(shareIntent);
        }
    }
}

