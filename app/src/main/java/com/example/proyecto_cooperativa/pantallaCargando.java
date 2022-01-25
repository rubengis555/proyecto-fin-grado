package com.example.proyecto_cooperativa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class pantallaCargando extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_cargando);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(pantallaCargando.this, MenuPrincipal.class);
                startActivity(i);
                finish();
            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 5000);
    }
}