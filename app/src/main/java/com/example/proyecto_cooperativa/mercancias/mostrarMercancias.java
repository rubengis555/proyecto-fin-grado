package com.example.proyecto_cooperativa.mercancias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.adaptador.ListaMercanciasAdapter;
import com.example.proyecto_cooperativa.bd.Controlador;
import com.example.proyecto_cooperativa.modelo.Mercancias;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class mostrarMercancias extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    RecyclerView listaMercancias;
    ArrayList<Mercancias> listaArrayMercancias;
    FloatingActionButton favNuevaMercancia;
    ListaMercanciasAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_mercancias);
        listaMercancias = findViewById(R.id.listaMercancias);
        listaMercancias.setLayoutManager(new LinearLayoutManager(this));

        Controlador db=new Controlador(mostrarMercancias.this);
        listaArrayMercancias =new ArrayList<>();
        listaArrayMercancias =new ArrayList<>();
        adapter = new ListaMercanciasAdapter(db.mostrarMercancias());
        listaMercancias.setAdapter(adapter);

        favNuevaMercancia = findViewById(R.id.favNuevaMercancia);
        txtBuscar = findViewById(R.id.txtBuscar);
        favNuevaMercancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mostrarMercancias.this, nueva_mercancia.class);
                startActivity(i);
            }
        });

       txtBuscar.setOnQueryTextListener(this);
        listaMercancias.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(newState == listaMercancias.SCROLL_STATE_IDLE){
                    favNuevaMercancia.show();
                }
                super.onScrollStateChanged(listaMercancias, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if ((dy > 0) || dy < 0 && favNuevaMercancia.isShown())
                {
                    favNuevaMercancia.hide();

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

}