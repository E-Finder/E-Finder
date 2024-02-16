package com.example.efinder;
import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
public class DbUsuario extends GestionarUsuario {
    Context context;
    public DbUsuario(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertarUsuario(Usuario usuario) {
        long id = 0;
        try {
            GestionarUsuario dbHelper = new GestionarUsuario(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("email", usuario.getEmail());
            values.put("password", usuario.getPassword());
            id = db.insert(TABLE_USUARIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }
    public Usuario mostrarUsuario(String correo) {
        GestionarUsuario dbHelper = new GestionarUsuario(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Usuario usuario = null;
        String query = "SELECT * FROM " + TABLE_USUARIOS + " WHERE email = ?";
        Cursor cursorUsuario = db.rawQuery(query, new String[]{correo});
        try {
            if (cursorUsuario.moveToFirst()) {
                usuario = new Usuario();
                int emailIndex = cursorUsuario.getColumnIndex("email");
                if (emailIndex != -1) {
                    usuario.setEmail(cursorUsuario.getString(emailIndex));
                }
                int passwordIndex = cursorUsuario.getColumnIndex("password");
                if (passwordIndex != -1) {
                    usuario.setPassword(cursorUsuario.getString(passwordIndex));
                }
            }
        } finally {
            if (cursorUsuario != null && !cursorUsuario.isClosed()) {
                cursorUsuario.close();
            }
        }
        return usuario;
    }
}

