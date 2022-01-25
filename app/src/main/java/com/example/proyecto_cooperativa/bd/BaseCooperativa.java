package com.example.proyecto_cooperativa.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseCooperativa extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NOMBRE = "cooperativa.db";

    //tablas
    public static final String TABLE_AGRICULTORES = "t_agricultores";
    public static final String TABLE_CLIENTES = "t_clientes";
    public static final String TABLE_VENTAS = "t_ventas";
    public static final String TABLE_MERCANCIAS = "t_mercancias";
    public static final String TABLE_USUARIOS = "t_usuarios";


    public BaseCooperativa(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(" +
                "usuario TEXT PRIMARY KEY ," +
                "password TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_AGRICULTORES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dni TEXT NOT NULL," +
                "nombre TEXT NOT NULL," +
                "direccion TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "correo TEXT NOT NULL," +
                "zonas TEXT NOT NULL)");
        db.execSQL("CREATE TABLE " + TABLE_MERCANCIAS + "(" +
                "id_mercancia INTEGER PRIMARY KEY AUTOINCREMENT," +
                "factura TEXT NOT NULL," +
                "producto TEXT NOT NULL," +
                "cantidad TEXT NOT NULL," +
                "precio TEXT NOT NULL," +
                "id_agricultor INTEGER," +
                "FOREIGN KEY (id_agricultor)"+
                "REFERENCES  t_agricultores (id))");

        db.execSQL("CREATE TABLE " + TABLE_CLIENTES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dni TEXT NOT NULL," +
                "nombre TEXT NOT NULL," +
                "direccion TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "correo TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_VENTAS + "(" +
                "id_venta INTEGER PRIMARY KEY AUTOINCREMENT," +
                "factura TEXT NOT NULL," +
                "producto TEXT NOT NULL," +
                "cantidad TEXT NOT NULL," +
                "precio TEXT NOT NULL," +
                "id_cliente INTEGER," +
                 "FOREIGN KEY (id_cliente)"+
                 "REFERENCES  t_clientes (id))");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE  " + TABLE_USUARIOS);
        db.execSQL("DROP TABLE  " + TABLE_AGRICULTORES);
        db.execSQL("DROP TABLE  " + TABLE_MERCANCIAS);
        db.execSQL("DROP TABLE  " + TABLE_CLIENTES);
        db.execSQL("DROP TABLE  " + TABLE_VENTAS);
        onCreate(db);
    }

}
