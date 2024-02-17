package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

public class BusquedaArticuloActivity extends ToolbarActivity {

    private SearchView searchViewArticulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_articulo_layout);

        setToolbarOnClicks();

        searchViewArticulo = findViewById(R.id.searchViewArticulo);

        searchViewArticulo.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Aquí manejas la acción cuando se presiona Enter
                // Por ejemplo, iniciar una nueva actividad con los resultados de la búsqueda
                iniciarActividadResultados(query);
                return true; // Indica que hemos manejado el evento de forma personalizada
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Opcional: puedes manejar cambios de texto en tiempo real si es necesario
                return false;
            }
        });
        Button button2 = findViewById(R.id.buttonBack);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para ir a la nueva actividad
                Intent intent = new Intent(BusquedaArticuloActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void iniciarActividadResultados(String query) {
        // Iniciar una actividad que muestre los resultados de la búsqueda
        Intent intent = new Intent(this, ResultadoArticuloActivity.class);
        intent.putExtra("query", query); // Pasar la consulta de búsqueda a la siguiente actividad
        startActivity(intent);
    }
}
