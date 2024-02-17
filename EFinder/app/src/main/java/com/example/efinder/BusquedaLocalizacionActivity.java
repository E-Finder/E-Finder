package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;

public class BusquedaLocalizacionActivity extends ToolbarActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_localizacion);

        setToolbarOnClicks();

        // Asegúrate de que el botón exista en tu layout con el ID correcto.
        Button buttonBack = findViewById(R.id.buttonBack);
        if (buttonBack != null) {
            buttonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Simplemente finaliza la actividad actual para volver a la anterior en la pila de actividades.
                    finish();
                }
            });
        } else {
            // Log para depuración en caso de que el botón sea null.
            Log.e("BusquedaLocalizacionActivity", "El botón de retorno es null. Revisa tu layout.");
        }

        searchView = findViewById(R.id.searchViewLocalizacion);
        if (searchView != null) {
            // Configurar el listener para el SearchView
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // Manejar la acción cuando se presiona Enter.
                    iniciarActividadResultados(query);
                    return true; // Indica que hemos manejado el evento de forma personalizada.
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // Opcional: manejar cambios de texto en tiempo real si es necesario.
                    return false;
                }
            });
        } else {
            // Log para depuración en caso de que el SearchView sea null.
            Log.e("BusquedaLocalizacionActivity", "El SearchView es null. Revisa tu layout.");
        }
    }

    private void iniciarActividadResultados(String query) {
        this.query = query;
        Intent intent = new Intent(this, ResultadoLocalizacionActivity.class);
        intent.putExtra("query", query); // Pasar la consulta de búsqueda a la siguiente actividad.
        startActivity(intent);
    }
}
