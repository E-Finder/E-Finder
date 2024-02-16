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
import java.util.Collections;
import java.util.Random;  

public class HomeActivity extends ToolbarActivity {
    private RecyclerView recyclerView;
    private EventoAdapter adapter;
    private List<Evento> eventos;
    private EventoDAO eventoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        setToolbarOnClicks();
        findViewById(R.id.imageButtonDestacada).setOnClickListener(v -> destacados());
        findViewById(R.id.imageButtonRuta).setOnClickListener(v -> valorados());

    }

    public void destacados() {
        // Inicializar el DAO de Evento
        eventoDAO = new EventoDAO(this);

        // Recuperar eventos de la base de datos
        eventos = eventoDAO.listarEventos(); // Asegúrate de que esta función retorna efectivamente una lista de eventos desde el DAO

        // Si la lista está vacía, inicialízala para evitar NullPointerException
        if (eventos == null) {
            eventos = new ArrayList<>();
        }

        // Mezclar aleatoriamente la lista de eventos
        Collections.shuffle(eventos, new Random());

        // Tomar los primeros 5 eventos
        List<Evento> eventosDestacados = eventos.subList(0, Math.min(eventos.size(), 5));

        // Configurar el RecyclerView y el adaptador con los eventos aleatorios
        recyclerView = findViewById(R.id.recyclerViewShow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventoAdapter(eventosDestacados);
        recyclerView.setAdapter(adapter);
    }

    public void valorados() {
        // Recuperar los 5 eventos con mayor valoración
        List<Evento> eventosDestacados = eventoDAO.buscarEventosPorValoracion(5);

        // Configurar el RecyclerView y el adaptador con los eventos destacados
        recyclerView = findViewById(R.id.recyclerViewShow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventoAdapter(eventosDestacados);
        recyclerView.setAdapter(adapter);
    }
}
