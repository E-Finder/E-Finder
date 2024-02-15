package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class ProfileActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); // Asegúrate de que estás usando el layout correcto

        // Convertir imageButtonSettings en una variable local
        ImageButton imageButtonSettings = findViewById(R.id.imageButtonSettings);

        // Comprobar si el ImageButton es nulo
        if (imageButtonSettings != null) {
            imageButtonSettings.setOnClickListener(v -> {
                // Iniciar la actividad de configuración
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
            });
        } else {
            // Lanza un error o haz un log para depurar
            throw new RuntimeException("ImageButtonSettings no se ha encontrado en el layout.");
        }
    }
}
