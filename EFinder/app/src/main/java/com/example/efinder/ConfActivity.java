package com.example.efinder;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ConfActivity extends AppCompatActivity {
    ArrayList<ElementoLista> elem = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracion);

        // Datos de ejemplo
        // String[] datos = {"Cuenta", "Tema", "Localización", "Gestionar subscripción VIP", "Notificaciones", "Métodos de pago", "Desconectar"};


        elem.add(new ElementoLista("Cuenta"));
        elem.add(new ElementoLista("Tema"));
        elem.add(new ElementoLista("Localización"));
        elem.add(new ElementoLista("Gestionar subscripción VIP"));
        elem.add(new ElementoLista("Notificaciones"));
        elem.add(new ElementoLista("Métodos de pago"));
        elem.add(new ElementoLista("Desconectar"));


        AdaptadorPersonas adaptador = new AdaptadorPersonas(this);
        ListView lv1 = findViewById(R.id.list1);
        lv1.setAdapter(adaptador);
    }

    class AdaptadorPersonas extends ArrayAdapter<ElementoLista> {

        AppCompatActivity appCompatActivity;

        AdaptadorPersonas(AppCompatActivity context) {
            super(context, R.layout.elemento_lista, elem);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.elemento_lista, null);

            TextView textView1 = item.findViewById(R.id.text);
            textView1.setText(elem.get(position).getelemento());



            return(item);
        }
    }

}
