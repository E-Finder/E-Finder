package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InformacionActivity extends ToolbarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_evento_layout);

        setToolbarOnClicks();

        TextView textViewNombre = findViewById(R.id.textViewNombreText);
        TextView textViewDescripcion = findViewById(R.id.textViewDescripcionText);
        TextView textViewTipo = findViewById(R.id.textViewTipoText);
        TextView textViewHorario = findViewById(R.id.textViewHorarioText);
        TextView textViewUbicacion = findViewById(R.id.textViewUbicacionText);
        TextView textViewValoracion = findViewById(R.id.textViewValoracionText);
        ImageView imageView = findViewById(R.id.imageView);

        // Recuperar los datos
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String descripcion = intent.getStringExtra("descripcion");
        String tipo = intent.getStringExtra("tipo");
        String horario = intent.getStringExtra("horario");
        String ubicacion = intent.getStringExtra("ubicacion");
        double valoracion = intent.getDoubleExtra("valoracion", 0.0);
        String imagen = intent.getStringExtra("imagen");

        // Actualizar la vista
        textViewNombre.setText(nombre);
        textViewDescripcion.setText(descripcion);
        textViewTipo.setText(tipo);
        textViewHorario.setText(horario);
        textViewUbicacion.setText(ubicacion);
        textViewValoracion.setText(String.format("%.2f", valoracion));

        // Cargar la imagen
        String nombreLimpio = imagen.toLowerCase().replaceAll("\\.jpg|\\.png", ""); // Elimina la extensión y convierte a minúsculas
        int idRecurso = getResources().getIdentifier(nombreLimpio, "drawable", getPackageName());
        if (idRecurso != 0) {
            imageView.setImageResource(idRecurso);
        } else {
            imageView.setImageResource(R.drawable.fotoerror); // Imagen por defecto
        }
    }

}