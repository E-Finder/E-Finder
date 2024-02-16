package com.example.efinder;

import Model.Usuario;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BorrarCuentaActivity extends ToolbarActivity {
    Usuario usuario = new Usuario();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrar_cuenta_layout); // Asegúrate de usar el nombre correcto de tu layout

        Button buttonEliminar = findViewById(R.id.imageButtonEliminar);
        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarYEliminarCuenta();
            }
        });

        Button buttonCancelar = findViewById(R.id.buttonCancelar);
        buttonCancelar.setOnClickListener(v -> finish()); // Simplemente cierra la actividad
    }

    private void confirmarYEliminarCuenta() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Estás seguro que quieres eliminar tu cuenta? Esta acción no se puede deshacer.")
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eliminarCuentaUsuario();
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    private void eliminarCuentaUsuario() {
        int usuarioId = usuario.getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);

        if (usuarioDAO.borrarUsuario(usuarioId)) {
            // Opcional: Limpiar cualquier dato de sesión o preferencias
            limpiarDatosDeSesion();

            // Cerrar sesión o llevar al usuario a la pantalla de inicio de sesión/registro
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            // Mostrar mensaje de error si la eliminación falló
            Toast.makeText(this, "Error al eliminar la cuenta.", Toast.LENGTH_SHORT).show();
        }
    }
}
