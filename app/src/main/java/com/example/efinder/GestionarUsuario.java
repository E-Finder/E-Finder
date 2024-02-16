package com.example.efinder;
import android.Manifest;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GestionarUsuario extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "usuario.db";
    public static final String TABLE_USUARIOS = "t_usuario";
    public GestionarUsuario(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)  {
String req = "CREATE TABLE IF NOT EXISTS "+TABLE_USUARIOS + " (email text primary key, password text)";
sqLiteDatabase.execSQL(req);

  }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String req = "DROP TABLE IF EXISTS " + TABLE_USUARIOS;
        db.execSQL(req);
        onCreate(db);
    }


}
