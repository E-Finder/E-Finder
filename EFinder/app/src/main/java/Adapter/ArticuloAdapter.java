package Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.efinder.InformacionArticuloActivity;
import com.example.efinder.R;
import com.example.efinder.InformacionArticuloActivity; // Asegúrate de tener esta actividad
import Model.Articulo;
import java.util.List;

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.ArticuloViewHolder> {

    private List<Articulo> articulos;
    private Context context;

    public ArticuloAdapter(Context context, List<Articulo> articulos) {
        this.context = context;
        this.articulos = articulos;
    }

    @NonNull
    @Override
    public ArticuloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.articulo_item_layout, parent, false);
        return new ArticuloViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticuloViewHolder holder, int position) {
        Articulo articulo = articulos.get(position);

        holder.nombreTextView.setText(articulo.getNombre());

        // No se necesita comprobar si es null, puesto que el precio es un tipo primitivo
        holder.precioTextView.setText(String.format("$%.2f", articulo.getPrecio()));

        cargarImagenDesdeRuta(holder.imageView, articulo.getImagen());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InformacionArticuloActivity.class);
                intent.putExtra("nombre", articulo.getNombre());
                intent.putExtra("descripcion", articulo.getDescripcion());
                intent.putExtra("tipo", articulo.getTipo());
                intent.putExtra("precio", articulo.getPrecio());
                intent.putExtra("imagen", articulo.getImagen());
                context.startActivity(intent);
            }
        });
    }


    private void cargarImagenDesdeRuta(ImageView imageView, String nombreImagen) {
        if (nombreImagen != null && !nombreImagen.isEmpty()) {
            // Eliminar el símbolo del dólar y la extensión .jpg o .png si existen
            String nombreLimpio = nombreImagen.replace("$", "").replaceAll("\\.jpg|\\.png", "").toLowerCase();
            int idRecurso = context.getResources().getIdentifier(nombreLimpio, "drawable", context.getPackageName());

            Log.d("InfoArticulo", "ID del recurso para la imagen: " + idRecurso); // Agrega este log para depuración

            if (idRecurso != 0) {
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageResource(idRecurso);
            } else {
                imageView.setImageResource(R.drawable.fotoerror); // Imagen por defecto si el recurso no se encuentra
            }
        } else {
            imageView.setImageResource(R.drawable.fotoerror); // Imagen por defecto si el nombre es null o vacío
        }
    }



    @Override
    public int getItemCount() {
        return articulos.size();
    }

    public static class ArticuloViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView nombreTextView;
        public TextView precioTextView;

        public ArticuloViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewArticulo);
            nombreTextView = itemView.findViewById(R.id.textViewNombreArticulo);
            precioTextView = itemView.findViewById(R.id.textViewPrecio);
        }
    }
}
