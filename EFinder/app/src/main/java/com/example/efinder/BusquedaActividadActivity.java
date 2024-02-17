package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.SearchView;

public class BusquedaActividadActivity extends ToolbarActivity {

    private SearchView searchViewArticulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_articulo_layout); // Asegúrate de tener este layout con un SearchView cuyo id sea searchViewArticulo


        searchViewArticulo = findViewById(R.id.searchViewArticulo);

        searchViewArticulo.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                iniciarActividadResultadosArticulos(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Manejar cambios de texto si es necesario
                return false;
            }
        });

        Button button2 = findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para ir a la nueva actividad
                Intent intent = new Intent(BusquedaActividadActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void iniciarActividadResultadosArticulos(String query) {
        Intent intent = new Intent(this, ResultadoArticuloActivity.class);
        intent.putExtra("query", query); // Pasar la consulta de búsqueda
        startActivity(intent);
    }
}
