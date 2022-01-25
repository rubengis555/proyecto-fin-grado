package com.example.proyecto_cooperativa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.proyecto_cooperativa.agricultor.NuevoAgricultor;
import com.example.proyecto_cooperativa.mercancias.nueva_mercancia;

import com.example.proyecto_cooperativa.mercancias.principalAgricultoresMercancias;
import com.example.proyecto_cooperativa.ventas.principalClientesVentas;
import com.example.proyecto_cooperativa.zonasAgricolas.zonas_agricolas;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void onClick(View view) {
        Intent miIntent = null;
        switch (view.getId()) {

            case R.id.btn_verZonas:
                miIntent = new Intent(MenuPrincipal.this, zonas_agricolas.class);
                break;
            case R.id.btn_verAgricultoresMercancias:
                miIntent = new Intent(MenuPrincipal.this, principalAgricultoresMercancias.class);
                break;
            case R.id.btn_verClientesVentas:
                miIntent = new Intent(MenuPrincipal.this, principalClientesVentas.class);
                break;


        }
        if (miIntent != null) {
            startActivity(miIntent);
        }

    }
        public void iraNoticias (View view){
        Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.europapress.es/temas/agricultura/"));
        startActivity(i);
    }

}