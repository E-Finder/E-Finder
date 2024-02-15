package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SettingsActivity extends ToolbarActivity {
    protected Button buttonAcercaDe;
    protected Button buttonSiguenos;
    protected Button buttonContactanos;
    protected Button buttonVIP;
    protected Button buttonNotificaciones;
    protected Button buttonCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        setToolbarOnClicks();
    }

    public void setToolbarOnClicks() {
        buttonAcercaDe = findViewById(R.id.buttonAcercaDe);
            buttonSiguenos = findViewById(R.id.buttonSiguenos);
        buttonContactanos = findViewById(R.id.buttonContactanos);
        buttonVIP = findViewById(R.id.buttonVIP);
        buttonNotificaciones = findViewById(R.id.buttonNotificaciones);
        buttonCerrarSesion = findViewById(R.id.buttonCerrarSesion);


        buttonAcercaDe.setOnClickListener(v -> {
            // Iniciar la actividad correspondiente al botón Acerca de
            startActivity(new Intent(this, AcercaDeActivity.class));
            System.out.println("aoinaga");
        });

        buttonSiguenos.setOnClickListener(v -> {
            // Iniciar la actividad correspondiente al botón Siguenos
            startActivity(new Intent(this, AgendaActivity.class));
        });

        buttonContactanos.setOnClickListener(v -> {
            // Iniciar la actividad correspondiente al botón Contactanos
            startActivity(new Intent(this, ContactanosActivity.class));
        });

        buttonVIP.setOnClickListener(v -> {
            // Iniciar la actividad correspondiente al botón Calendar
            startActivity(new Intent(this, AgendaActivity.class));
        });

        buttonNotificaciones.setOnClickListener(v -> {
            // Iniciar la actividad correspondiente al botón Calendar
            startActivity(new Intent(this, AgendaActivity.class));
        });

        buttonCerrarSesion.setOnClickListener(v -> {
            // Iniciar la actividad correspondiente al botón Calendar
            startActivity(new Intent(this, AgendaActivity.class));
        });
    }
}