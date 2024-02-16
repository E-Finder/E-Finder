package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.efinder.R;

import java.util.List;

import Model.Evento;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {
    private List<Evento> eventos;

    public EventoAdapter(List<Evento> eventos) {
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
        Context context = holder.itemView.getContext();
        cargarImagenDesdeRuta(context, holder.imageButton, evento.getImagen());
    }

    private void cargarImagenDesdeRuta(Context context, ImageButton imageButton, String rutaRelativa) {
        int idRecurso = context.getResources().getIdentifier(rutaRelativa, "drawable", context.getPackageName());
        if (idRecurso != 0) { // Recurso encontrado
            imageButton.setImageResource(idRecurso);
        } else { // Recurso no encontrado, usar imagen por defecto
            imageButton.setImageResource(R.drawable.fotoerror);
        }
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class EventoViewHolder extends RecyclerView.ViewHolder {
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
    }



}
