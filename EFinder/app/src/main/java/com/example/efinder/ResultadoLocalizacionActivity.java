package com.example.efinder;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList; // Asegúrate de importar ArrayList
import java.util.List;

import Adapter.EventoAdapter;
import DAO.EventoDAO;
import Model.Evento;

public class ResultadoLocalizacionActivity extends ToolbarActivity {

    private RecyclerView recyclerView;
    private EventoAdapter adapter;
    private List<Evento> eventos;
    private EventoDAO eventoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_localizacion);

        setToolbarOnClicks();

        // Inicializar el DAO de Evento
        eventoDAO = new EventoDAO(this);

        // Recuperar eventos de la base de datos
        eventos = eventoDAO.listarEventos(); // Asegúrate de que esta función retorna efectivamente una lista de eventos desde el DAO

        // Si la lista está vacía, inicialízala para evitar NullPointerException
        if (eventos == null) {
            eventos = new ArrayList<>();
        }

        // Configurar el RecyclerView y el adaptador
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventoAdapter(eventos); // Pasar la lista de eventos recuperados al adaptador
        recyclerView.setAdapter(adapter);
    }
}
