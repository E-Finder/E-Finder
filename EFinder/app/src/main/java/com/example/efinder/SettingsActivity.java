package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends ToolbarActivity {
    private Button buttonAcercaDe;
    private Button buttonSiguenos;
    private Button buttonContactanos;
    private Button buttonVIP;
    private Button buttonNotificaciones;
    private Button buttonCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        inicializarBotones();
        setToolbarOnClicks();
    }

    private void inicializarBotones() {
        buttonAcercaDe = findViewById(R.id.buttonAcercaDe);
        buttonSiguenos = findViewById(R.id.buttonSiguenos);
        buttonContactanos = findViewById(R.id.buttonContactanos);
        buttonVIP = findViewById(R.id.buttonVIP);
        buttonNotificaciones = findViewById(R.id.buttonNotificaciones);
        buttonCerrarSesion = findViewById(R.id.buttonCerrarSesion);

        buttonAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, AcercaDeActivity.class);
                startActivity(intent);
            }
        });

        buttonSiguenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, SiguenosActivity.class);
                startActivity(intent);
            }
        });

        buttonContactanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ContactanosActivity.class);
                startActivity(intent);
            }
        });

        buttonVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(SettingsActivity.this, VIPActivity.class);
                //startActivity(intent);
            }
        });

        buttonNotificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(SettingsActivity.this, NotificacionesActivity.class);
                //startActivity(intent);
            }
        });

        buttonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(SettingsActivity.this, CerrarSesionActivity.class);
                //startActivity(intent);
            }
        });
    }

    // Asume que este método ya está implementado en ToolbarActivity o lo implementarás.
}
