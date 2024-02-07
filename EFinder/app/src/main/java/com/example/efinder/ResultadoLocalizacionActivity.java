package com.example.efinder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ResultadoLocalizacionActivity extends ToolbarActivity {

    private LinearLayout linearLayoutResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_localizacion);

        ImageButton imageButtonPlan = findViewById(R.id.imageButtonPlan);
        imageButtonPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogo();
            }
        });
/**
        linearLayoutResultados = findViewById(R.id.linearLayoutResultados);

        // Supongamos que tienes una lista de objetos Resultado
        List<Resultado> resultados = obtenerResultadosDesdeBaseDeDatos();

        // Iterar sobre la lista de resultados y crear elementos visuales dinámicamente
        for (Resultado resultado : resultados) {
            // Crear ImageButton
            ImageButton imageButton = new ImageButton(this);
            imageButton.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            imageButton.setImageResource(resultado.getImagenResource());
            linearLayoutResultados.addView(imageButton);

            // Crear TextView
            TextView textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            textView.setText(resultado.getTexto());
            linearLayoutResultados.addView(textView);
        }
    }

    // Método ficticio para simular la obtención de resultados desde la base de datos
    private List<Resultado> obtenerResultadosDesdeBaseDeDatos() {
        List<Resultado> resultados = new ArrayList<>();

        // Simulando la obtención de resultados desde la base de datos
        // Agregamos algunos resultados ficticios para el ejemplo
        resultados.add(new Resultado("La Rioja", R.drawable.image1));
        resultados.add(new Resultado("Barcelona", R.drawable.image2));
        resultados.add(new Resultado("Madrid", R.drawable.image3));
        resultados.add(new Resultado("Sevilla", R.drawable.image4));
        resultados.add(new Resultado("Valencia", R.drawable.image5));

        return resultados;
    }*/
}
}
