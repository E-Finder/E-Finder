package com.example.efinder;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import DAO.UsuarioDAO;
import Model.Usuario;

public class ProfileActivity extends ToolbarActivity {
    Button buttonNombre, buttonNumero, buttonCorreo;
    ImageButton buttonSettings;
    TextView nombreUsuarioTextView; // Suponiendo que tienes este TextView para mostrar el nombre

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setToolbarOnClicks();

        buttonNombre = findViewById(R.id.buttonNombre);
        buttonNumero = findViewById(R.id.buttonNumero);
        buttonCorreo = findViewById(R.id.buttonCorreo);
        buttonSettings = findViewById(R.id.imageButtonSettings);
        nombreUsuarioTextView = findViewById(R.id.nombreUsuarioTextView); // Inicializa tu TextView aquí

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

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(intent);
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
        int usuarioId = obtenerIdUsuarioActual(); // Necesitas implementar este método
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);

        switch (campo) {
            case "nombre":
                usuarioDAO.cambiarNombreUsuario(usuarioId, nuevoValor);
                break;
            case "telefono":
                usuarioDAO.cambiarTelefonoUsuario(usuarioId, nuevoValor);
                break;
            case "correo":
                usuarioDAO.cambiarCorreoUsuario(usuarioId, nuevoValor);
                break;
            // Agrega casos para otros campos según sea necesario
        }

        // Después de actualizar, recargar los datos
        recargarDatosUsuario();
    }

    private void recargarDatosUsuario() {
        // Suponiendo que tengas una forma de obtener los detalles del usuario actual.
        int usuarioId = obtenerIdUsuarioActual(); // Necesitas implementar este método
        Usuario usuarioActual = new UsuarioDAO(this).obtenerDetallesUsuario(usuarioId); // Necesitas implementar este método en UsuarioDAO

        // Aquí, actualizas tu UI con los nuevos datos
        nombreUsuarioTextView.setText(usuarioActual.getNombre());

        // Haz algo similar para otros detalles que necesites actualizar en la UI
    }

    private int obtenerIdUsuarioActual() {
        SharedPreferences prefs = getSharedPreferences("PreferenciasUsuario", MODE_PRIVATE);
        return prefs.getInt("idUsuario", 0); // Asume 0 o cualquier valor adecuado como ID por defecto
    }

    @Override
    protected void onResume() {
        super.onResume();
        recargarDatosUsuario();
    }
}