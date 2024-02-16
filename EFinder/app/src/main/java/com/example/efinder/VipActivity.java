package com.example.efinder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import DB.DatabaseHelper;
import Model.Usuario;

public class VipActivity extends ToolbarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vip);

        int idUsuarioActual = obtenerIdUsuarioActual();



        Button im= findViewById(R.id.buttonAtras);
        im.setOnClickListener(v -> {
            Intent intentVip = new Intent(this, HomeActivity.class);
            startActivity(intentVip);

        });


        if (esUsuarioVip(idUsuarioActual)) {
            // El usuario es VIP, abrir la actividad VIP
            Intent intentVip = new Intent(this, VipActivity.class);
            startActivity(intentVip);
            // finish();  // Opcional, dependiendo de si deseas cerrar la actividad actual
        } else {

            // El usuario no es VIP, abrir la actividad para darse de alta
            Intent intentDarAltaVip = new Intent(this, DarAltaVip.class);
            startActivity(intentDarAltaVip);
            finish();  // Opcional, dependiendo de si deseas cerrar la actividad actual
        }

    }

    private int obtenerIdUsuarioActual() {
        Usuario u = new Usuario();
        int idUsuarioActual = u.obtenerInstancia().getId();
        return idUsuarioActual;
    }


    @SuppressLint("Range")
    private boolean esUsuarioVip(int idUsuario) {
        // Reemplaza DatabaseHelperClass con el nombre de tu clase que extiende SQLiteOpenHelper
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Implementa la lógica para consultar la base de datos y determinar si el usuario es VIP
        // Retorna true si es VIP, false si no es VIP
        // Aquí deberías utilizar tu propia lógica y estructura de base de datos
        // Supongamos que tienes una columna 'es_vip' en tu tabla de usuarios
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columnas = {"vip"};
        String seleccion = "_id = ?";
        String[] seleccionArgs = {String.valueOf(idUsuario)};

        Cursor cursor = db.query("usuarios", columnas, seleccion, seleccionArgs, null, null, null);

        boolean esVip = false;

        if (cursor != null && cursor.moveToFirst()) {
            esVip = cursor.getInt(cursor.getColumnIndex("vip")) == 1;
            cursor.close();
        }

        return esVip;
    }


}
