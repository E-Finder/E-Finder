package com.example.efinder;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

public class ToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_layout);


    }



    public void mostrarDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una opción")
                .setItems(new CharSequence[]{"Búsqueda por localización", "Búsqueda por actividad"}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            Intent intent = new Intent(this, BusquedaLocalizacionActivity.class);
                            startActivity(intent);
                            break;
                        case 1:
                            // Acción para "Búsqueda por actividad"
                            break;
                    }
                })
                .show();
    }
}
