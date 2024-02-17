package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import Adapter.EventoAdapter;
import DAO.EventoDAO;
import Model.Evento;

public class HomeActivity extends ToolbarActivity {
    private RecyclerView recyclerView;
    private EventoAdapter adapter;
    private List<Evento> eventos;
    private EventoDAO eventoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        // Inicializar el DAO de Evento aquí para asegurarse de que esté disponible
        eventoDAO = new EventoDAO(this);

        setToolbarOnClicks();

        findViewById(R.id.imageButtonRecomendadas).setOnClickListener(v -> destacados());
        findViewById(R.id.imageButtonRuta).setOnClickListener(v -> valorados());
    }

    public void destacados() {
        eventos = eventoDAO.listarEventos();

        if (eventos == null || eventos.isEmpty()) {
            eventos = new ArrayList<>();
        }

        Collections.shuffle(eventos, new Random());

        List<Evento> eventosDestacados = eventos.subList(0, Math.min(eventos.size(), 5));

        setupRecyclerView(eventosDestacados);
    }

    public void valorados() {
        // Asegurarse de que eventoDAO ha sido inicializado
        if (eventoDAO != null) {
            List<Evento> eventosValorados = eventoDAO.buscarEventosPorValoracion(5);

            if (eventosValorados == null || eventosValorados.isEmpty()) {
                eventosValorados = new ArrayList<>();
            }

            setupRecyclerView(eventosValorados);
        }
    }

    private void setupRecyclerView(List<Evento> eventosList) {
        recyclerView = findViewById(R.id.recyclerViewShow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventoAdapter(this, eventosList);
        recyclerView.setAdapter(adapter);
    }
}
