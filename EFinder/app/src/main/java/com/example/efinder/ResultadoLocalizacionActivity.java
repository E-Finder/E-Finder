package com.example.efinder;

import android.os.Bundle;
<<<<<<< HEAD
import DAO.EventoDAO;
import Model.Evento;

import android.util.Log;
import android.widget.LinearLayout;

=======
>>>>>>> 28509d479cbee805b8fe3b54c264ea7aa107c680
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Adapter.EventoAdapter;
import DAO.EventoDAO;
import Model.Evento;

public class ResultadoLocalizacionActivity extends ToolbarActivity {

<<<<<<< HEAD
    private RecyclerView recyclerView;
    private EventoAdapter adapter;
    private List<Evento> eventos;
    private EventoDAO eventoDAO;

=======
>>>>>>> 28509d479cbee805b8fe3b54c264ea7aa107c680
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_localizacion);

        setToolbarOnClicks();

<<<<<<< HEAD
        // Inicializar la lista de eventos
        eventos = new ArrayList<>();

        // Configurar el RecyclerView y el adaptador
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventoAdapter(eventos); // Pasar la lista vacía al adaptador por ahora
=======
        // Convertido a variable local ya que no se requiere acceso a estos campos fuera de onCreate
        List<Evento> eventos = obtenerEventos();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EventoAdapter adapter = new EventoAdapter(eventos);
>>>>>>> 28509d479cbee805b8fe3b54c264ea7aa107c680
        recyclerView.setAdapter(adapter);

        // Inicializar el DAO de Evento
        eventoDAO = new EventoDAO(this);

        // Recuperar eventos de la base de datos
        eventos = obtenerEventos();

        // Actualizar el adaptador con la lista de eventos recuperados
        adapter.setEventos(eventos);
        adapter.notifyDataSetChanged();
    }

    private List<Evento> obtenerEventos() {
        return eventoDAO.listarEventos();
    }
}
