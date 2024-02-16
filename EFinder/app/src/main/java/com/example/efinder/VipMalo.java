package com.example.efinder;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import Model.Articulo;
import DAO.ArticuloDAO;

import java.util.ArrayList;

public class VipMalo extends ToolbarActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vip);

        // Obtén la lista de productos desde la base de datos
        ArrayList<Articulo> listaProductos = ArticuloDAO.obtenerProductosDesdeBaseDeDatos();

        // Obtén el layout lineal horizontal
        LinearLayout linearLayout = findViewById(R.id.recyclerView);

        // Añade botones dinámicamente según la cantidad de productos
        for (Articulo producto : listaProductos) {
            Button button = crearBotonProducto(producto);
            linearLayout.addView(button);
        }
    }

    private Button crearBotonProducto(Articulo producto) {
        // Crea un nuevo botón con el nombre y precio del producto
        Button button = new Button(this);

        // Crea un SpannableString para combinar texto e imagen
        SpannableString spannableString = new SpannableString(producto.getNombre() + "\n$" + producto.getPrecio());

        // Obtiene la ruta de la imagen desde la base de datos
        String rutaImagen = ArticuloDAO.obtenerRutaImagenDesdeBaseDeDatos(producto.getId());

        // Obtiene la imagen Drawable desde la ruta utilizando Glide
        Drawable imagenDrawable = obtenerDrawableDesdeRuta(rutaImagen);

        // Añade la imagen al principio del SpannableString
        imagenDrawable.setBounds(0, 0, imagenDrawable.getIntrinsicWidth(), imagenDrawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(imagenDrawable, ImageSpan.ALIGN_BASELINE);
        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        // Establece el texto en el botón
        button.setText(spannableString);

        // Configura el diseño del botón
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8, 0, 8, 0); // Márgenes izquierdo y derecho para separar los botones
        button.setLayoutParams(params);

        return button;
    }



    private Drawable obtenerDrawableDesdeRuta(String rutaImagen) {
        // Implementa la lógica para obtener el Drawable desde la ruta utilizando Glide u otra biblioteca
        // Retorna el Drawable obtenido
        // Ejemplo de implementación con Glide:
        try {
            return Glide.with(this)
                    .load(rutaImagen)
                    .submit()
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
