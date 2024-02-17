package Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.efinder.InformacionActivity;
import com.example.efinder.MainActivity;
import com.example.efinder.R;
import java.util.List;
import Model.Evento;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {
    private List<Evento> eventos;
    private Context context; // Agrega el contexto como variable de instancia

    // Modifica el constructor para aceptar el contexto
    public EventoAdapter(Context context, List<Evento> eventos) {
        this.context = context;
        this.eventos = eventos;
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item_layout, parent, false);
        return new EventoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        Evento evento = eventos.get(position);
        holder.nombreTextView.setText(evento.getNombre());
        holder.descripcionTextView.setText(evento.getDescripcion());
        cargarImagenDesdeRuta(holder.imageButton, evento.getImagen());

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InformacionActivity.class);
                intent.putExtra("nombre", evento.getNombre());
                intent.putExtra("descripcion", evento.getDescripcion());
                intent.putExtra("tipo", evento.getTipo());
                intent.putExtra("horario", evento.getHorario());
                intent.putExtra("ubicacion", evento.getUbicacion());
                intent.putExtra("valoracion", evento.getValoracion());
                intent.putExtra("imagen", evento.getImagen()); // Asegúrate de que este sea solo el nombre del archivo sin extensión
                context.startActivity(intent);
            }
        });
    }

    // Ajusta el método para usar el contexto de la variable de instancia
    private void cargarImagenDesdeRuta(ImageButton imageButton, String rutaRelativa) {
        String trimImage = rutaRelativa.contains(".") ? rutaRelativa.substring(0, rutaRelativa.indexOf('.')) : rutaRelativa;
        int idRecurso = context.getResources().getIdentifier(trimImage, "drawable", context.getPackageName());
        if (idRecurso != 0) { // Recurso encontrado
            imageButton.setScaleType(ImageButton.ScaleType.CENTER_CROP); // O usar CENTER_INSIDE según la necesidad
            imageButton.setImageResource(idRecurso);
        } else { // Recurso no encontrado, usar imagen por defecto
            imageButton.setImageResource(R.drawable.fotoerror); // Asegúrate de tener una imagen por defecto en drawable
        }
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public static class EventoViewHolder extends RecyclerView.ViewHolder {
        public ImageButton imageButton;
        public TextView nombreTextView;
        public TextView descripcionTextView;

        public EventoViewHolder(View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.imageButton);
            nombreTextView = itemView.findViewById(R.id.textViewAcercaDe);
            descripcionTextView = itemView.findViewById(R.id.textViewDescripcion);
        }
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
        notifyDataSetChanged(); // Notifica cambios para actualizar la vista
    }
}
