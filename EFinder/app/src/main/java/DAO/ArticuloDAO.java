package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import DB.DatabaseHelper;
import Model.Articulo;

public class ArticuloDAO {

    private DatabaseHelper dbHelper;
    private static final String TAG = "ArticuloDAO";

    public ArticuloDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean anadirArticulo(Articulo articulo) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("nombre", articulo.getNombre());
            values.put("descripcion", articulo.getDescripcion());
            values.put("tipo", articulo.getTipo());
            values.put("precio", articulo.getPrecio());
            values.put("imagen", articulo.getImagen());

            long id = db.insertOrThrow("articulo", null, values);
            return id != -1;
        } catch (Exception e) {
            Log.e(TAG, "Error al añadir artículo", e);
            return false;
        }
    }

    public boolean actualizarArticulo(Articulo articulo) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("nombre", articulo.getNombre());
            values.put("descripcion", articulo.getDescripcion());
            values.put("tipo", articulo.getTipo());
            values.put("precio", articulo.getPrecio());
            values.put("imagen", articulo.getImagen());

            int rowsAffected = db.update("articulo", values, "id = ?", new String[]{String.valueOf(articulo.getId())});
            return rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Error al actualizar artículo", e);
            return false;
        }
    }

    public boolean borrarArticulo(int id) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            int rowsAffected = db.delete("articulo", "id = ?", new String[]{String.valueOf(id)});
            return rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Error al borrar artículo", e);
            return false;
        }
    }

    public List<Articulo> listarArticulos() {
        List<Articulo> listaArticulos = new ArrayList<>();
        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.rawQuery("SELECT * FROM articulo", null)) {
            while (cursor.moveToNext()) {
                listaArticulos.add(crearArticuloDesdeCursor(cursor));
            }
        } catch (Exception e) {
            Log.e(TAG, "Error al listar artículos", e);
        }
        return listaArticulos;
    }

    public List<Articulo> buscarArticulosPorNombre(String nombre) {
        List<Articulo> listaArticulos = new ArrayList<>();
        String selection = "nombre LIKE ?";
        String[] selectionArgs = new String[]{"%" + nombre + "%"};

        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.query("articulo", null, selection, selectionArgs, null, null, null)) {
            while (cursor.moveToNext()) {
                listaArticulos.add(crearArticuloDesdeCursor(cursor));
            }
        } catch (Exception e) {
            Log.e(TAG, "Error al buscar artículos por nombre", e);
        }
        return listaArticulos;
    }

    public List<Articulo> buscarArticulosPorTipo(String tipo) {
        List<Articulo> listaArticulos = new ArrayList<>();
        String selection = "tipo = ?";
        String[] selectionArgs = new String[]{tipo};

        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.query("articulo", null, selection, selectionArgs, null, null, null)) {
            while (cursor.moveToNext()) {
                listaArticulos.add(crearArticuloDesdeCursor(cursor));
            }
        } catch (Exception e) {
            Log.e(TAG, "Error al buscar artículos por tipo", e);
        }
        return listaArticulos;
    }

    private Articulo crearArticuloDesdeCursor(Cursor cursor) {
        Articulo articulo = new Articulo();
        articulo.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
        articulo.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
        articulo.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow("descripcion")));
        articulo.setTipo(cursor.getString(cursor.getColumnIndexOrThrow("tipo")));
        articulo.setPrecio(cursor.getDouble(cursor.getColumnIndexOrThrow("precio")));
        articulo.setImagen(cursor.getString(cursor.getColumnIndexOrThrow("imagen")));
        return articulo;
    }
}
