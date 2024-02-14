package com.example.efinder;

import android.os.Bundle;
import DAO.EventoDAO;
import Model.Evento;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapter.EventoAdapter;
import Model.Evento;

public class ResultadoLocalizacionActivity extends ToolbarActivity {

    private RecyclerView recyclerView;
    private EventoAdapter adapter;
    private List<Evento> eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_localizacion);

        setToolbarOnClicks();

        eventos = obtenerEventos();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventoAdapter(eventos);
        recyclerView.setAdapter(adapter);

        System.out.println(eventos.toString());
    }

    private List<Evento> obtenerEventos() {
        EventoDAO eventoDAO = new EventoDAO(getApplicationContext()); // Obtén una instancia de EventoDAO
        return eventoDAO.listarEventos(); // Obtén todos los eventos de la base de datos y devuélvelos
    }

}
