package com.example.efinder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    Button buttonNombre, buttonNumero, buttonCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        buttonNombre = findViewById(R.id.buttonNombre);
        buttonNumero = findViewById(R.id.buttonNumero);
        buttonCorreo = findViewById(R.id.buttonCorreo);

        buttonNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogActualizar("Actualizar Nombre", "nombre");
            }
        });

        buttonNumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogActualizar("Actualizar Teléfono", "telefono");
            }
        });

        buttonCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogActualizar("Actualizar Correo", "correo");
            }
        });
    }

    private void mostrarDialogActualizar(String titulo, final String campoActualizar) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nuevoValor = input.getText().toString();
                actualizarValorEnBaseDeDatos(campoActualizar, nuevoValor);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void actualizarValorEnBaseDeDatos(String campo, String nuevoValor) {
        // Implementa la lógica de actualización aquí.
        // Esto podría ser una actualización de SQLite, una llamada a Firebase, o cualquier otro mecanismo de persistencia que estés utilizando.
    }
}
