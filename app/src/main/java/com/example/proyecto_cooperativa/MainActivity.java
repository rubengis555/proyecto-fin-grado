package com.example.proyecto_cooperativa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.proyecto_cooperativa.registro.registroUsuario;

public class MainActivity extends AppCompatActivity {
SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void irAplicacion(View view){
        Intent i = new Intent(this, registroUsuario.class);
        startActivity(i);
    }
}