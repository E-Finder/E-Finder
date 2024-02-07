package com.example.efinder;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

public class ToolbarActivity extends AppCompatActivity {

    private ImageButton imageButtonHome;
    private ImageButton imageButtonCalendar;
    private ImageButton imageButtonShop;
    private ImageButton imageButtonProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.toolbar_layout);


        imageButtonHome = findViewById(R.id.imageButtonHome);
        imageButtonCalendar = findViewById(R.id.imageButtonCalendar);
        imageButtonShop = findViewById(R.id.imageButtonShop);
        imageButtonProfile = findViewById(R.id.imageButtonProfile);

        imageButtonHome.setOnClickListener(v -> {
            // Iniciar la actividad correspondiente al botón Home
            startActivity(new Intent(ToolbarActivity.this, MainActivity2.class));
            System.out.println("aoinaga");
        });
        /**
        imageButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al botón Calendar
                startActivity(new Intent(ToolbarActivity.this, CalendarActivity.class));
            }
        });

        imageButtonShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al botón Shop
                startActivity(new Intent(ToolbarActivity.this, ShopActivity.class));
            }
        });

        imageButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al botón Profile
                startActivity(new Intent(ToolbarActivity.this, ProfileActivity.class));
            }
        });

        */
    }



    public void mostrarDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una opción")
                .setItems(new CharSequence[]{"Búsqueda por localización", "Búsqueda por actividad"}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            Intent intentLocalization = new Intent(this, BusquedaLocalizacionActivity.class);
                            startActivity(intentLocalization);
                            break;
                        case 1:
                            Intent intentActivity = new Intent(this, BusquedaActividadActivity.class);
                            startActivity(intentActivity);
                            break;
                    }
                })
                .show();
    }
}
