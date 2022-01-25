package com.example.proyecto_cooperativa.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.adaptador.ListaClientesAdapter;
import com.example.proyecto_cooperativa.bd.Controlador;
import com.example.proyecto_cooperativa.modelo.Personas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class mostrarClientes extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SearchView txtBuscar;
    RecyclerView listaClientes;
    ArrayList<Personas> listaArrayClientes;
    FloatingActionButton favNuevoCliente;
    ListaClientesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_clientes);
        listaClientes = findViewById(R.id.listaClientes);
        listaClientes.setLayoutManager(new LinearLayoutManager(this));

        Controlador db=new Controlador(mostrarClientes.this);
        listaArrayClientes =new ArrayList<>();

        adapter = new ListaClientesAdapter(db.mostrarClientes());
        listaClientes.setAdapter(adapter);

        favNuevoCliente = findViewById(R.id.favNuevoCliente);
        txtBuscar = findViewById(R.id.txtBuscar);
        favNuevoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mostrarClientes.this, nuevoCliente.class);
                startActivity(i);
            }
        });

        txtBuscar.setOnQueryTextListener(this);
        listaClientes.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(newState == listaClientes.SCROLL_STATE_IDLE){
                    favNuevoCliente.show();
                }
                super.onScrollStateChanged(listaClientes, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if ((dy > 0) || dy < 0 && favNuevoCliente.isShown())
                {
                    favNuevoCliente.hide();

                }
            }
        });
    }
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filtrarDatosCliente(newText);
        return false;
    }


    }
