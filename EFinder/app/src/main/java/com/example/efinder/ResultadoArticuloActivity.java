package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import Adapter.ArticuloAdapter; // Asegúrate de tener este adaptador
import DAO.ArticuloDAO; // Asegúrate de tener esta clase DAO
import Model.Articulo; // Asegúrate de tener este modelo de datos

import java.util.List;

public class ResultadoArticuloActivity extends ToolbarActivity {

    private RecyclerView recyclerView;
    private ArticuloAdapter adapter;
    private List<Articulo> articulos;
    private ArticuloDAO articuloDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_articulo); // Asegúrate de tener este layout

        inicializarRecyclerView();

        setToolbarOnClicks();
    }

    private List<Articulo> obtenerArticulos(String nombre) {
        articuloDAO = new ArticuloDAO(this);

        // Filtrar los artículos según el filtro proporcionado
        if (nombre == null || nombre.isEmpty()) {
            // Si no se proporciona un filtro, devolver todos los artículos
            return articuloDAO.listarArticulos();
        } else {
            // Si se proporciona un filtro, devolver los artículos filtrados
            return articuloDAO.buscarArticulosPorNombre(nombre); // Asegúrate de implementar este método en ArticuloDAO
        }
    }

    private void inicializarRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView); // Asegúrate de que este ID esté en tu layout

        // Obtener el filtro de búsqueda de la actividad anterior
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");

        // Obtener los artículos filtrados
        articulos = obtenerArticulos(query);

        // Configurar el RecyclerView
        adapter = new ArticuloAdapter(this, articulos); // Asegúrate de que el constructor de ArticuloAdapter acepte un Context como primer parámetro
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}