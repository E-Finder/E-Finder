package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Adapter.EventoAdapter;
import DAO.EventoDAO;
import Model.Evento;

public class ResultadoLocalizacionActivity extends ToolbarActivity {

    private RecyclerView recyclerView;
    private EventoAdapter adapter;
    private List<Evento> eventos;
    private EventoDAO eventoDAO;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_localizacion);

        setToolbarOnClicks();

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchViewLocalizacion);

        eventoDAO = new EventoDAO(this);

        eventos = obtenerEventos("");
        adapter = new EventoAdapter(eventos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Configurar el listener para el SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // No es necesario implementar esta función
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                eventos = obtenerEventos(newText); // Filtrar la lista de eventos
                adapter.setEventos(eventos);
                adapter.notifyDataSetChanged();
                if (eventos.isEmpty()) {
                    Toast.makeText(ResultadoLocalizacionActivity.this, "No se encontraron resultados para la ubicación buscada", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    private List<Evento> obtenerEventos(String filtroUbicacion) {
        // Obtener eventos filtrados por ubicación desde la base de datos
        if (filtroUbicacion.isEmpty()) {
            return eventoDAO.listarEventos(); // Obtener todos los eventos si el filtro está vacío
        } else {
            return eventoDAO.buscarEventosPorUbicacion(filtroUbicacion); // Obtener eventos filtrados por ubicación
        }
    }

}
