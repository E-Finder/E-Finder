package Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.efinder.R;

import java.util.List;

import Model.Articulo;

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.ArticuloViewHolder> {
    private List<Articulo> listaProductos;

    public ArticuloAdapter(List<Articulo> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ArticuloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.articulo_item_layout, parent, false);
        return new ArticuloViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticuloViewHolder holder, int position) {
        Articulo producto = listaProductos.get(position);
        holder.nombrePrecioTextView.setText(producto.getNombre() + " - $" + producto.getPrecio());

        // Cargar la imagen utilizando Glide (adaptar según tus necesidades)
        Glide.with(holder.itemView)
                .load(producto.getImagen())
                .into(holder.imageButton);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ArticuloViewHolder extends RecyclerView.ViewHolder {
        public ImageButton imageButton;
        public TextView nombrePrecioTextView;

        public ArticuloViewHolder(View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.imageButton);
            nombrePrecioTextView = itemView.findViewById(R.id.textViewPrecioArticulo);
        }
    }

    public void setListaProductos(List<Articulo> listaProductos) {
        this.listaProductos = listaProductos;
        notifyDataSetChanged();
    }
}

