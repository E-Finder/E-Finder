package com.example.efinder;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;

public class SiguenosActivity extends ToolbarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactanos_layout);

        // Encuentra el botón por su ID en el layout
        Button openGoogleBtn = findViewById(R.id.imageViewTwitter); // Asegúrate de que este ID corresponda a un Button

        // Establece un OnClickListener para el botón usando una expresión lambda
        openGoogleBtn.setOnClickListener(view -> {
            // Define el URL que deseas abrir
            String url = "https://www.google.com";
            // Crea un Intent con la acción de ver (ACTION_VIEW) y el URL a abrir
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            // Inicia la actividad con el Intent creado
            startActivity(intent);
        });
    }
}