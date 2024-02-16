package com.example.efinder;

import android.os.Bundle;

public class BusquedaActividadActivity extends ToolbarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_actividad);

        setToolbarOnClicks();
    }
}