package com.example.proyecto_cooperativa.agricultor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.adaptador.ListaAgricultoresAdapter;
import com.example.proyecto_cooperativa.bd.Controlador;
import com.example.proyecto_cooperativa.modelo.Personas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class mostrarAgricultores extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    RecyclerView listaAgricultores;
    ArrayList<Personas> listaArrayAgricultores;
    FloatingActionButton favNuevoAgricultor;
    ListaAgricultoresAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_agricultores);
        listaAgricultores = findViewById(R.id.listaAgricultores);
        listaAgricultores.setLayoutManager(new LinearLayoutManager(this));

        Controlador db=new Controlador(mostrarAgricultores.this);
        listaArrayAgricultores =new ArrayList<>();

        adapter = new ListaAgricultoresAdapter(db.mostrarAgricultores());
        listaAgricultores.setAdapter(adapter);

        favNuevoAgricultor = findViewById(R.id.favNuevoAgricultor);
        txtBuscar = findViewById(R.id.txtBuscar);
        favNuevoAgricultor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mostrarAgricultores.this, NuevoAgricultor.class);
                startActivity(i);
            }
        });

        txtBuscar.setOnQueryTextListener(this);
        listaAgricultores.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(newState == listaAgricultores.SCROLL_STATE_IDLE){
                    favNuevoAgricultor.show();
                }
                super.onScrollStateChanged(listaAgricultores, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if ((dy > 0) || dy < 0 && favNuevoAgricultor.isShown())
                {
                    favNuevoAgricultor.hide();

                }
            }
        });
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filtrarDatos(newText);
        return false;
    }
}