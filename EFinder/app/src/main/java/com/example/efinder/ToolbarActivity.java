package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

public class ToolbarActivity extends AppCompatActivity {

    protected String query="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_layout);

        setToolbarOnClicks();
    }

    public void mostrarDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una opción")
                .setItems(new CharSequence[]{"Búsqueda por localización", "Búsqueda por actividad"}, (dialog, which) -> {
                    if (which == 0) {
                        startActivity(new Intent(this, BusquedaLocalizacionActivity.class));
                    } else if (which == 1) {
                        startActivity(new Intent(this, BusquedaActividadActivity.class));
                    }
                })
                .show();
    }

    public void setToolbarOnClicks() {
        // Botón de inicio
        ImageButton homeButton = findViewById(R.id.imageButtonHome);
        if (homeButton != null) {
            homeButton.setOnClickListener(v -> startActivity(new Intent(this, HomeActivity.class)));
        }

        // Botón del calendario
        ImageButton calendarButton = findViewById(R.id.imageButtonCalendar);
        if (calendarButton != null) {
            calendarButton.setOnClickListener(v -> startActivity(new Intent(this, AgendaActivity.class)));
        }

        // Botón de la tienda
        ImageButton shopButton = findViewById(R.id.imageButtonShop);
        if (shopButton != null) {
            shopButton.setOnClickListener(v -> startActivity(new Intent(this, BusquedaArticuloActivity.class)));
        }

        // Botón del perfil
        ImageButton profileButton = findViewById(R.id.imageButtonProfile);
        if (profileButton != null) {
            profileButton.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        }

        // Botón para mostrar el diálogo
        ImageButton planButton = findViewById(R.id.imageButtonPlan);
        if (planButton != null) {
            planButton.setOnClickListener(v -> mostrarDialogo());
        }
    }

}

