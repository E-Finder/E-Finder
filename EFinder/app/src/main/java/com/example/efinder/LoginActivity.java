package com.example.efinder;

import android.content.SharedPreferences;

public class LoginActivity extends ToolbarActivity {
    // Otros métodos y variables de tu actividad de inicio de sesión

    private void iniciarSesion(String usuario, String contraseña) {
        // Lógica de inicio de sesión...
        // Supongamos que obtienes el ID del usuario como resultado del inicio de sesión exitoso
        int idUsuario = 123; // Ejemplo de ID de usuario obtenido
        guardarIdUsuarioActual(idUsuario);
        // Continuar con la navegación a la siguiente actividad después del inicio de sesión
    }

    public void guardarIdUsuarioActual(int idUsuario) {
        SharedPreferences prefs = getSharedPreferences("PreferenciasUsuario", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("idUsuario", idUsuario);
        editor.apply();
    }
}