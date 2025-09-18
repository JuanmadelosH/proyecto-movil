package com.example.proyecto_movil;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQL extends SQLiteOpenHelper {

    private static final String DB_NAME = "Adminbd";
    private static final int DB_VERSION = 1;

    public AdminSQL(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name != null ? name : DB_NAME, factory, version > 0 ? version : DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE Login(" +
                        "nombre TEXT PRIMARY KEY," +
                        "contrasena TEXT" +
                ")"
        );
    }

    // Se ejecuta cuando subes la versión de la DB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Login");
        onCreate(db);
    }
}
