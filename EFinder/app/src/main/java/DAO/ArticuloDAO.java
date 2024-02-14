package DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DB.DatabaseHelper;
import Model.Articulo;

public class ArticuloDAO {

    List<Articulo> listaArticulos;

    private DatabaseHelper dbHelper;

    public ArticuloDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean anadirArticulo(Articulo articulo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", articulo.getNombre());
        values.put("descripcion", articulo.getDescripcion());
        values.put("tipo", articulo.getTipo());
        values.put("precio", articulo.getPrecio());
        values.put("imagen", articulo.getImagen());

        long id = db.insert("articulo", null, values);
        db.close();
        return id != -1;
    }

    public boolean actualizarArticulo(Articulo articulo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", articulo.getNombre());
        values.put("descripcion", articulo.getDescripcion());
        values.put("tipo", articulo.getTipo());
        values.put("precio", articulo.getPrecio());
        values.put("imagen", articulo.getImagen());

        int rowsAffected = db.update("articulo", values, "id = ?", new String[]{String.valueOf(articulo.getId())});
        db.close();
        return rowsAffected > 0;
    }

    public boolean borrarArticulo(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.delete("articulo", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected > 0;
    }

    @SuppressLint("Range")
    public List<Articulo> listarArticulos() {
        listaArticulos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM articulo", null);

        if (cursor.moveToFirst()) {
            do {
                Articulo articulo = new Articulo();
                articulo.setId(cursor.getInt(cursor.getColumnIndex("id")));
                articulo.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                articulo.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                articulo.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                articulo.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                articulo.setImagen(cursor.getString(cursor.getColumnIndex("imagen")));
                listaArticulos.add(articulo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaArticulos;
    }
    @SuppressLint("Range")
    public List<Articulo> buscarArticulosPorNombre(String nombre) {
        List<Articulo> listaArticulos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("articulo",
                new String[]{"id", "nombre", "descripcion", "tipo", "precio", "imagen"},
                "nombre LIKE ?", new String[]{"%" + nombre + "%"}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Articulo articulo = new Articulo();
                articulo.setId(cursor.getInt(cursor.getColumnIndex("id")));
                articulo.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                articulo.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                articulo.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                articulo.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                articulo.setImagen(cursor.getString(cursor.getColumnIndex("imagen")));
                listaArticulos.add(articulo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaArticulos;
    }
    @SuppressLint("Range")
    public List<Articulo> buscarArticulosPorTipo(String tipo) {
        List<Articulo> listaArticulos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("articulo",
                new String[]{"id", "nombre", "descripcion", "tipo", "precio", "imagen"},
                "tipo = ?", new String[]{tipo}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Articulo articulo = new Articulo();
                articulo.setId(cursor.getInt(cursor.getColumnIndex("id")));
                articulo.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                articulo.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                articulo.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                articulo.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                articulo.setImagen(cursor.getString(cursor.getColumnIndex("imagen")));
                listaArticulos.add(articulo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaArticulos;
    }


}