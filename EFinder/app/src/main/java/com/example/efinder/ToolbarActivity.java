package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
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
        findViewById(R.id.imageButtonHome).setOnClickListener(v -> startActivity(new Intent(this, HomeActivity.class)));
        findViewById(R.id.imageButtonCalendar).setOnClickListener(v -> startActivity(new Intent(this, AgendaActivity.class)));
        findViewById(R.id.imageButtonShop).setOnClickListener(v -> startActivity(new Intent(this, VipActivity.class)));
        findViewById(R.id.imageButtonProfile).setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
        findViewById(R.id.imageButtonPlan).setOnClickListener(v -> mostrarDialogo());
    }
}

