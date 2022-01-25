package com.example.proyecto_cooperativa.ventas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.adaptador.ListaVentasAdapter;
import com.example.proyecto_cooperativa.bd.Controlador;
import com.example.proyecto_cooperativa.modelo.Ventas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class mostrarVentas extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    RecyclerView listaVentas;
    ArrayList<Ventas> listaArrayVentas;
    FloatingActionButton favNuevaVenta;
    ListaVentasAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_ventas);
        listaVentas = findViewById(R.id.listaVentas);
        listaVentas.setLayoutManager(new LinearLayoutManager(this));

        Controlador db=new Controlador(mostrarVentas.this);
        listaArrayVentas =new ArrayList<>();
        adapter = new ListaVentasAdapter(db.mostrarVentas());
        listaVentas.setAdapter(adapter);


        favNuevaVenta = findViewById(R.id.favNuevaVenta);
        txtBuscar = findViewById(R.id.txtBuscar);
        favNuevaVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mostrarVentas.this, nuevaVenta.class);
                startActivity(i);
            }
        });

        txtBuscar.setOnQueryTextListener(this);
        listaVentas.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(newState == listaVentas.SCROLL_STATE_IDLE){
                    favNuevaVenta.show();
                }
                super.onScrollStateChanged(listaVentas, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if ((dy > 0) || dy < 0 && favNuevaVenta.isShown())
                {
                    favNuevaVenta.hide();

                }
            }
        });
    }

    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    //@Override
    public boolean onQueryTextChange(String newText) {
          adapter.filtrarDatos(newText);
        return false;
    }
/*
    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaArrayMercancias.size();i++){
            listaInformacion.add(listaArrayMercancias.get(i).getIdMercancia()+" - "
                    +listaArrayMercancias.get(i).getFactura());
        }

    }

 */
}