package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

public class ToolbarActivity extends AppCompatActivity {

    protected ImageButton imageButtonHome;
    protected ImageButton imageButtonCalendar;
    protected ImageButton imageButtonShop;
    protected ImageButton imageButtonProfile;
    protected ImageButton imageButtonPlan;

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

    public void setToolbarOnClicks(){
        imageButtonHome = findViewById(R.id.imageButtonHome);
        imageButtonCalendar=findViewById(R.id.imageButtonCalendar);
        imageButtonShop = findViewById(R.id.imageButtonShop);
        imageButtonProfile = findViewById(R.id.imageButtonProfile);
        imageButtonPlan=findViewById(R.id.imageButtonPlan);


         imageButtonHome.setOnClickListener(v -> {
         // Iniciar la actividad correspondiente al botón Home
         startActivity(new Intent(this, HomeActivity.class));
         System.out.println("aoinaga");
         });

         imageButtonCalendar.setOnClickListener(v -> {
         // Iniciar la actividad correspondiente al botón Calendar
         startActivity(new Intent(this, AgendaActivity.class));
         });

        imageButtonPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogo();
            }
        });/**

         imageButtonShop.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        // Iniciar la actividad correspondiente al botón Shop
        startActivity(new Intent(this, ShopActivity.class));
        }
        });

         imageButtonProfile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        // Iniciar la actividad correspondiente al botón Profile
        startActivity(new Intent(this, ProfileActivity.class));
        }
        });*/




    }
}
