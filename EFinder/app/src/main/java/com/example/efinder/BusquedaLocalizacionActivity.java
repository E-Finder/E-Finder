package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;

public class BusquedaLocalizacionActivity extends ToolbarActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_localizacion);

        setToolbarOnClicks();

        searchView = findViewById(R.id.searchViewLocalizacion);

        // Configurar el listener para el SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Aquí manejas la acción cuando se presiona Enter
                // Por ejemplo, iniciar una nueva actividad con los resultados de la búsqueda
                iniciarActividadResultados(query);
                return true; // Indica que hemos manejado el evento de forma personalizada
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Aquí puedes manejar los cambios de texto en el SearchView si es necesario
                return false;
            }
        });
    }

    private void iniciarActividadResultados(String query) {
        Intent intent = new Intent(this, ResultadoLocalizacionActivity.class);
        //intent.putExtra("query", query); // Puedes pasar la consulta de búsqueda a la siguiente actividad si es necesario
        startActivity(intent);
    }

    }
