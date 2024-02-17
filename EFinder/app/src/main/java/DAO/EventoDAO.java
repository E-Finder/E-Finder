package DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import DB.DatabaseHelper;
import Model.Evento;

public class EventoDAO {

    private DatabaseHelper dbHelper;
    private static final String TAG = "EventoDAO";

    public EventoDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean anadirEvento(Evento evento) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("nombre", evento.getNombre());
            values.put("descripcion", evento.getDescripcion());
            values.put("tipo", evento.getTipo());
            values.put("horario", evento.getHorario());
            values.put("ubicacion", evento.getUbicacion());
            values.put("imagen", evento.getImagen());
            values.put("valoracion", evento.getValoracion());

            long id = db.insertOrThrow("evento", null, values);
            return id != -1;
        } catch (Exception e) {
            Log.e(TAG, "Error al añadir evento", e);
            return false;
        }
    }

    public boolean actualizarEvento(Evento evento) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("nombre", evento.getNombre());
            values.put("descripcion", evento.getDescripcion());
            values.put("tipo", evento.getTipo());
            values.put("horario", evento.getHorario());
            values.put("ubicacion", evento.getUbicacion());
            values.put("imagen", evento.getImagen());
            values.put("valoracion", evento.getValoracion());

            int rowsAffected = db.update("evento", values, "id = ?", new String[]{String.valueOf(evento.getId())});
            return rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Error al actualizar evento", e);
            return false;
        }
    }

    public boolean borrarEvento(int id) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            int rowsAffected = db.delete("evento", "id = ?", new String[]{String.valueOf(id)});
            return rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Error al borrar evento", e);
            return false;
        }
    }

    public List<Evento> listarEventos() {
        List<Evento> listaEventos = new ArrayList<>();
        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.rawQuery("SELECT * FROM evento", null)) {
            while (cursor.moveToNext()) {
                listaEventos.add(crearEventoDesdeCursor(cursor));
            }
        } catch (Exception e) {
            Log.e(TAG, "Error al listar eventos", e);
        }
        return listaEventos;
    }


    @SuppressLint("Range")
    public List<Evento> buscarEventosPorTipo(String tipo) {
        List<Evento> listaEventos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("evento",
                new String[]{"id", "nombre", "descripcion", "tipo", "horario", "ubicacion", "imagen", "valoracion"},
                "tipo = ?", new String[]{tipo}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Evento evento = new Evento();
                evento.setId(cursor.getInt(cursor.getColumnIndex("id")));
                evento.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                evento.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                evento.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                evento.setHorario(cursor.getString(cursor.getColumnIndex("horario")));
                evento.setUbicacion(cursor.getString(cursor.getColumnIndex("ubicacion")));
                evento.setImagen(cursor.getString(cursor.getColumnIndex("imagen")));
                evento.setValoracion(cursor.getDouble(cursor.getColumnIndex("valoracion")));
                listaEventos.add(evento);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaEventos;
    }

    @SuppressLint("Range")
    public List<Evento> buscarEventosPorUbicacion(String ubicacion) {
        List<Evento> listaEventos = new ArrayList<>();
        // Uso del patrón try-with-resources para manejar automáticamente el cierre del Cursor y la DB
        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.query("evento",
                     new String[]{"id", "nombre", "descripcion", "tipo", "horario", "ubicacion", "imagen", "valoracion"},
                     "ubicacion = ?", new String[]{ubicacion}, null, null, null)) {

            if (cursor.moveToFirst()) {
                do {
                    Evento evento = new Evento();
                    evento.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    evento.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                    evento.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                    evento.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                    evento.setHorario(cursor.getString(cursor.getColumnIndex("horario")));
                    evento.setUbicacion(cursor.getString(cursor.getColumnIndex("ubicacion")));
                    evento.setImagen(cursor.getString(cursor.getColumnIndex("imagen")));
                    evento.setValoracion(cursor.getDouble(cursor.getColumnIndex("valoracion")));
                    listaEventos.add(evento);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            // Manejo de cualquier excepción que pueda ocurrir durante la consulta o el procesamiento del cursor
            Log.e("EventoDAO", "Error al buscar eventos por ubicación", e);
        }
        return listaEventos;
    }

    private Evento crearEventoDesdeCursor(Cursor cursor) {
        Evento evento = new Evento();
        int idIndex = cursor.getColumnIndex("id");
        int nombreIndex = cursor.getColumnIndex("nombre");
        int descripcionIndex = cursor.getColumnIndex("descripcion");
        int tipoIndex = cursor.getColumnIndex("tipo");
        int horarioIndex = cursor.getColumnIndex("horario");
        int ubicacionIndex = cursor.getColumnIndex("ubicacion");
        int imagenIndex = cursor.getColumnIndex("imagen");
        int valoracionIndex = cursor.getColumnIndex("valoracion");

        if (idIndex != -1) { evento.setId(cursor.getInt(idIndex)); }
        if (nombreIndex != -1) { evento.setNombre(cursor.getString(nombreIndex)); }
        if (descripcionIndex != -1) { evento.setDescripcion(cursor.getString(descripcionIndex)); }
        if (tipoIndex != -1) { evento.setTipo(cursor.getString(tipoIndex)); }
        if (horarioIndex != -1) { evento.setHorario(cursor.getString(horarioIndex)); }
        if (ubicacionIndex != -1) { evento.setUbicacion(cursor.getString(ubicacionIndex)); }
        if (imagenIndex != -1) { evento.setImagen(cursor.getString(imagenIndex)); }
        if (valoracionIndex != -1) { evento.setValoracion(cursor.getDouble(valoracionIndex)); }

        return evento;
    }

    @SuppressLint("Range")
    public List<Evento> buscarEventosPorValoracion(int cantidad) {
        List<Evento> listaEventos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("evento",
                new String[]{"id", "nombre", "descripcion", "tipo", "horario", "ubicacion", "imagen", "valoracion"},
                null, null, null, null, "valoracion DESC", String.valueOf(cantidad));
        if (cursor.moveToFirst()) {
            do {
                Evento evento = new Evento();
                evento.setId(cursor.getInt(cursor.getColumnIndex("id")));
                evento.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                evento.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                evento.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                evento.setHorario(cursor.getString(cursor.getColumnIndex("horario")));
                evento.setUbicacion(cursor.getString(cursor.getColumnIndex("ubicacion")));
                evento.setImagen(cursor.getString(cursor.getColumnIndex("imagen")));
                evento.setValoracion(cursor.getDouble(cursor.getColumnIndex("valoracion")));
                listaEventos.add(evento);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaEventos;
    }

}