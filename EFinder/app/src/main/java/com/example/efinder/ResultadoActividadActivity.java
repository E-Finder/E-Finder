package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import Adapter.EventoAdapter;
import DAO.EventoDAO;
import Model.Evento;

public class ResultadoActividadActivity extends ToolbarActivity {

    private RecyclerView recyclerView;
    private EventoAdapter adapter;
    private List<Evento> eventos;
    private EventoDAO eventoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_actividad);

        setToolbarOnClicks();

        inicializarRecyclerView();


    }

    private List<Evento> obtenerEventos(String filtroTipo) {
        // Filtrar los eventos según la ubicación proporcionada
        if (filtroTipo == null || filtroTipo.isEmpty()) {
            // Si no se proporciona una ubicación, devolver todos los eventos
            return eventoDAO.listarEventos();
        } else {
            // Si se proporciona una ubicación, devolver los eventos filtrados
            return eventoDAO.buscarEventosPorTipo(filtroTipo);
        }
    }
    private void inicializarRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);

        eventoDAO = new EventoDAO(this);

        // Obtener la consulta de búsqueda de la actividad anterior
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");

        // Obtener los eventos filtrados por actividad
        eventos = obtenerEventos(query);

        // Configurar el RecyclerView
        adapter = new EventoAdapter(eventos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
