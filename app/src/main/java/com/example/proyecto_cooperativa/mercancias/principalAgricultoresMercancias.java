package com.example.proyecto_cooperativa.mercancias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.agricultor.NuevoAgricultor;
import com.example.proyecto_cooperativa.agricultor.mostrarAgricultores;

public class principalAgricultoresMercancias extends AppCompatActivity {
    Button btn_datosEconomicos, btn_nuevoAgricultor, btn_listaAgricultores, btn_nuevaMercancia, btn_listaMercancias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_agricultores_mercancias);

        btn_datosEconomicos = findViewById(R.id.btn_datosEconomicos);
        btn_nuevoAgricultor = findViewById(R.id.btn_nuevoAgricultor);
        btn_listaAgricultores = findViewById(R.id.btn_listaAgricultores);
        btn_nuevaMercancia = findViewById(R.id.btn_nuevaMercancia);
        btn_listaMercancias = findViewById(R.id.btn_listaMercancias);


        btn_datosEconomicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(principalAgricultoresMercancias.this, datosEconomicosMercancias.class);
                startActivity(i);
            }
        });

        btn_nuevoAgricultor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(principalAgricultoresMercancias.this, NuevoAgricultor.class);
                startActivity(i);
            }
        });

        btn_listaAgricultores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(principalAgricultoresMercancias.this, mostrarAgricultores.class);
                startActivity(i);
            }
        });

        btn_nuevaMercancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(principalAgricultoresMercancias.this, nueva_mercancia.class);
                startActivity(i);
            }
        });

        btn_listaMercancias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(principalAgricultoresMercancias.this, mostrarMercancias.class);
                startActivity(i);
            }
        });
    }
}