package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import Adapter.EventoAdapter;
import DAO.EventoDAO;
import Model.Evento;

import java.util.List;

public class ResultadoLocalizacionActivity extends ToolbarActivity {

    private RecyclerView recyclerView;
    private EventoAdapter adapter;
    private List<Evento> eventos;
    private EventoDAO eventoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_localizacion);

        inicializarRecyclerView();

        setToolbarOnClicks();
    }

    private List<Evento> obtenerEventos(String filtroUbicacion) {
        eventoDAO = new EventoDAO(this);

        // Filtrar los eventos según la ubicación proporcionada
        if (filtroUbicacion == null || filtroUbicacion.isEmpty()) {
            // Si no se proporciona una ubicación, devolver todos los eventos
            return eventoDAO.listarEventos();
        } else {
            // Si se proporciona una ubicación, devolver los eventos filtrados
            return eventoDAO.buscarEventosPorUbicacion(filtroUbicacion);
        }
    }

    private void inicializarRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);

        // Obtener la consulta de búsqueda de la actividad anterior
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");

        // Obtener los eventos filtrados por ubicación
        eventos = obtenerEventos(query);

        // Configurar el RecyclerView
        adapter = new EventoAdapter(this, eventos); // Asegúrate de que el constructor de EventoAdapter acepte un Context como primer parámetro
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}