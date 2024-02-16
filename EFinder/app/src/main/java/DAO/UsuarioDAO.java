package DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import DB.DatabaseHelper;
import Model.Usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


@SuppressLint("Range")
public class UsuarioDAO {

    private DatabaseHelper dbHelper;

    public UsuarioDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean cambiarNombreUsuario(int id, String nuevoNombre) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nuevoNombre);
        int rowsAffected = db.update("usuario", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected > 0;
    }

    public boolean cambiarCorreoUsuario(int id, String nuevoCorreo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("correo", nuevoCorreo);
        int rowsAffected = db.update("usuario", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected > 0;
    }

    public boolean cambiarTelefonoUsuario(int id, String nuevoTelefono) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("telefono", nuevoTelefono);
        int rowsAffected = db.update("usuario", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected > 0;
    }
    public boolean borrarUsuario(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.delete("usuario", "id = ?", new String[] { String.valueOf(id) });
        db.close();
        return rowsAffected > 0;
    }
    public boolean anadirUsuario(Usuario usuario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", usuario.getNombre());
        values.put("alias", usuario.getAlias());
        values.put("correo", usuario.getCorreo());
        values.put("contrasena", usuario.getContrasena());
        values.put("telefono", usuario.getTelefono());
        values.put("ubicacion", usuario.getUbicacion());
        values.put("imagen", usuario.getImagen());
        values.put("vip", usuario.getVip() ? 1 : 0);

        long id = db.insert("usuario", null, values);
        db.close();
        return id != -1;
    }

    public boolean esUsuarioVIP(String nombreUsuario) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        boolean esVIP = false;

        String[] columns = {"vip"};
        String selection = "nombre = ?";
        String[] selectionArgs = {nombreUsuario};

        Cursor cursor = db.query("usuario", columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int vip = cursor.getInt(cursor.getColumnIndex("vip"));
            esVIP = (vip == 1);
            cursor.close();
        }

        return esVIP;
    }

    public void cambiarEstadoVIP(String nombreUsuario, boolean nuevoEstado) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("vip", nuevoEstado ? 1 : 0); // 1 para VIP, 0 para no VIP

        String selection = "nombre = ?";
        String[] selectionArgs = {nombreUsuario};

        db.update("usuario", values, selection, selectionArgs);
    }
}
