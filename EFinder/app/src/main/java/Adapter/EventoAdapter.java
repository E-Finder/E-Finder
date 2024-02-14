package Adapter;

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
                .inflate(R.layout.event_layout, parent, false);
        return new EventoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        Evento evento = eventos.get(position);
        holder.nombreTextView.setText(evento.getNombre());
        holder.descripcionTextView.setText(evento.getDescripcion());
        // Aquí puedes configurar la imagen del evento si es necesario
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
            nombreTextView = itemView.findViewById(R.id.textViewNombre);
            descripcionTextView = itemView.findViewById(R.id.textViewDescripcion);
        }
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }



}
