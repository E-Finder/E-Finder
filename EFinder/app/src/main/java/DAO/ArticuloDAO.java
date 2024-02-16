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

@SuppressLint("Range")
public class ArticuloDAO {

    List<Articulo> listaArticulos;

    private static DatabaseHelper dbHelper;

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

    @SuppressLint("Range")
    public static String obtenerRutaImagenDesdeBaseDeDatos(int idProducto) {
        // Realiza la consulta a la base de datos y obtén la ruta de la imagen
        // Adaptar según tu implementación de SQLiteOpenHelper o SQLiteDatabase
        String rutaImagen = "";

        // Ejemplo de consulta (asegúrate de cerrar el cursor al final)
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                "productos",
                new String[]{"imagen"},
                "_id = ?",
                new String[]{String.valueOf(idProducto)},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            rutaImagen = cursor.getString(cursor.getColumnIndex("imagen"));
            cursor.close();
        }

        return rutaImagen;
    }

    public static ArrayList<Articulo> obtenerProductosDesdeBaseDeDatos() {
        ArrayList<Articulo> listaProductos = new ArrayList<>();

        // Aquí deberías realizar la consulta a tu base de datos SQLite y obtener los resultados

        // Ejemplo: Obtener datos de la tabla "productos"
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columnas = {"_id", "nombre", "descripcion", "tipo", "precio", "imagen"};
        Cursor cursor = db.query("productos", columnas, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Obtener datos de cada fila y crear un objeto Articulo
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("_id"));
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                @SuppressLint("Range") String descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));
                @SuppressLint("Range") String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
                @SuppressLint("Range") double precio = cursor.getDouble(cursor.getColumnIndex("precio"));
                @SuppressLint("Range") String imagen = cursor.getString(cursor.getColumnIndex("imagen"));

                Articulo producto = new Articulo(nombre, descripcion, tipo, precio, imagen);
                listaProductos.add(producto);
            } while (cursor.moveToNext());

            // Cerrar el cursor después de usarlo
            cursor.close();
        }

        return listaProductos;
    }


}