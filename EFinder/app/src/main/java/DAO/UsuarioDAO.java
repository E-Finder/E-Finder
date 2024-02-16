package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import DB.DatabaseHelper;
import Model.Usuario;

public class UsuarioDAO {

    private DatabaseHelper dbHelper;
    private static final String TAG = "UsuarioDAO";

    public UsuarioDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean cambiarNombreUsuario(int id, String nuevoNombre) {
        return actualizarCampoUsuario(id, "nombre", nuevoNombre);
    }

    public boolean cambiarCorreoUsuario(int id, String nuevoCorreo) {
        return actualizarCampoUsuario(id, "correo", nuevoCorreo);
    }

    public boolean cambiarTelefonoUsuario(int id, String nuevoTelefono) {
        return actualizarCampoUsuario(id, "telefono", nuevoTelefono);
    }

    public boolean borrarUsuario(int id) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            int rowsAffected = db.delete("usuario", "id = ?", new String[]{String.valueOf(id)});
            return rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Error al borrar usuario", e);
            return false;
        }
    }

    public boolean anadirUsuario(Usuario usuario) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
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
            return id != -1;
        } catch (Exception e) {
            Log.e(TAG, "Error al añadir usuario", e);
            return false;
        }
    }

    public boolean esUsuarioVIP(String nombreUsuario) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        boolean esVIP = false;

        try (Cursor cursor = db.query("usuario", new String[]{"vip"}, "nombre = ?", new String[]{nombreUsuario}, null, null, null)) {
            int vipIndex = cursor.getColumnIndex("vip");
            if (vipIndex != -1 && cursor.moveToFirst()) {
                int vip = cursor.getInt(vipIndex);
                esVIP = (vip == 1);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error al verificar si el usuario es VIP", e);
        } finally {
            db.close();
        }

        return esVIP;
    }

    public void cambiarEstadoVIP(String nombreUsuario, boolean nuevoEstado) {
        actualizarCampoUsuario(nombreUsuario, "vip", nuevoEstado ? 1 : 0);
    }

    // Método privado para actualizar un campo específico de un usuario.
    private boolean actualizarCampoUsuario(int id, String campo, Object valor) {
        ContentValues values = new ContentValues();
        values.put(campo, valor.toString());

        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            int rowsAffected = db.update("usuario", values, "id = ?", new String[]{String.valueOf(id)});
            return rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Error al actualizar usuario", e);
            return false;
        }
    }

    // Sobrecarga del método para actualizar el campo 'vip' usando el nombre del usuario como selector.
    private void actualizarCampoUsuario(String nombreUsuario, String campo, int valor) {
        ContentValues values = new ContentValues();
        values.put(campo, valor);

        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            db.update("usuario", values, "nombre = ?", new String[]{nombreUsuario});
        } catch (Exception e) {
            Log.e(TAG, "Error al cambiar estado VIP", e);
        }
    }
}
