package com.example.efinder;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Adapter.EventoAdapter;
import DAO.EventoDAO;
import Model.Evento;

public class ResultadoLocalizacionActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_localizacion);

        setToolbarOnClicks();

        // Convertido a variable local ya que no se requiere acceso a estos campos fuera de onCreate
        List<Evento> eventos = obtenerEventos();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EventoAdapter adapter = new EventoAdapter(eventos);
        recyclerView.setAdapter(adapter);

        System.out.println(eventos.toString());
    }

    private List<Evento> obtenerEventos() {
        EventoDAO eventoDAO = new EventoDAO(getApplicationContext()); // Obtén una instancia de EventoDAO
        return eventoDAO.listarEventos(); // Obtén todos los eventos de la base de datos y devuélvelos
    }

}
