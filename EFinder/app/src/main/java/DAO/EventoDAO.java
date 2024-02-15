package DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DatabaseHelper;
import Model.Evento;

public class EventoDAO {

    private DatabaseHelper dbHelper;

    public EventoDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean anadirEvento(Evento evento) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", evento.getNombre());
        values.put("descripcion", evento.getDescripcion());
        values.put("tipo", evento.getTipo());
        values.put("horario", evento.getHorario());
        values.put("ubicacion", evento.getUbicacion());
        values.put("imagen", evento.getImagen());
        values.put("valoracion", evento.getValoracion());

        long id = db.insert("evento", null, values);
        db.close();
        return id != -1;
    }

    public boolean actualizarEvento(Evento evento) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", evento.getNombre());
        values.put("descripcion", evento.getDescripcion());
        values.put("tipo", evento.getTipo());
        values.put("horario", evento.getHorario());
        values.put("ubicacion", evento.getUbicacion());
        values.put("imagen", evento.getImagen());
        values.put("valoracion", evento.getValoracion());

        int rowsAffected = db.update("evento", values, "id = ?", new String[]{String.valueOf(evento.getId())});
        db.close();
        return rowsAffected > 0;
    }

    public boolean borrarEvento(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.delete("evento", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected > 0;
    }

    @SuppressLint("Range")
    public List<Evento> listarEventos() {
        List<Evento> listaEventos = new ArrayList<>();
        try {

            dbHelper.openDataBase();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM evento", null);

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
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("evento",
                new String[]{"id", "nombre", "descripcion", "tipo", "horario", "ubicacion", "imagen", "valoracion"},
                "ubicacion = ?", new String[]{ubicacion}, null, null, null);

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