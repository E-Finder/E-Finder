package com.example.efinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class InformacionArticuloActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_articulo_layout);

        setToolbarOnClicks();

        TextView textViewNombre = findViewById(R.id.textViewNombreText);
        TextView textViewDescripcion = findViewById(R.id.textViewDescripcionText);
        TextView textViewTipo = findViewById(R.id.textViewTipoText);
        TextView textViewPrecio = findViewById(R.id.textViewPrecioText);
        ImageView imageView = findViewById(R.id.imageView);

        // Recuperar los datos del Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String descripcion = intent.getStringExtra("descripcion");
        String tipo = intent.getStringExtra("tipo");
        double precio = intent.getDoubleExtra("precio", 0.0);
        String imagen = intent.getStringExtra("imagen");

        // Actualizar las vistas con los datos recuperados
        textViewNombre.setText(nombre);
        textViewDescripcion.setText(descripcion);
        textViewTipo.setText(tipo);
        textViewPrecio.setText(String.format("$%.2f", precio));

        // Llamar al método para cargar la imagen desde el nombre del recurso
        cargarImagenDesdeNombre(imageView, imagen);

        // Logs para depuración
        Log.d("InfoArticulo", "Nombre: " + nombre);
        Log.d("InfoArticulo", "Descripcion: " + descripcion);
        Log.d("InfoArticulo", "Tipo: " + tipo);
        Log.d("InfoArticulo", "Precio: $" + precio);
        Log.d("InfoArticulo", "Imagen: " + imagen);
    }

    private void cargarImagenDesdeNombre(ImageView imageView, String nombreImagen) {
        if (nombreImagen != null && !nombreImagen.isEmpty()) {
            // Eliminar la extensión .jpg o .png y el símbolo $ si existen
            String nombreLimpio = nombreImagen.replaceAll("\\.jpg|\\.png", "").replace("$", "").toLowerCase();
            int idRecurso = getResources().getIdentifier(nombreLimpio, "drawable", getPackageName());

            if (idRecurso != 0) {
                imageView.setImageResource(idRecurso);
            } else {
                imageView.setImageResource(R.drawable.fotoerror); // Imagen por defecto si el recurso no se encuentra
            }
        } else {
            imageView.setImageResource(R.drawable.fotoerror); // Imagen por defecto si el nombre es null o vacío
        }
    }
}
