package com.example.proyecto_cooperativa.zonasAgricolas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.cliente.nuevoCliente;

public class zonas_agricolas extends AppCompatActivity {

    Button btn_Zaragoza, btn_Huesca, btn_Belchite, btn_Teruel, btn_SierraMoncayo, btn_BajoAragon, btn_Somontano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas_agricolas);

        btn_Zaragoza = findViewById(R.id.btn_Zaragoza);
        btn_Huesca =findViewById(R.id.btn_Huesca);
        btn_Belchite = findViewById(R.id.btn_Belchite);
        btn_Teruel = findViewById(R.id.btn_Teruel);
        btn_SierraMoncayo = findViewById(R.id.btn_SierraMoncayo);
        btn_BajoAragon = findViewById(R.id.btn_BajoAragon);
        btn_Somontano = findViewById(R.id.btn_Somontano);

        btn_Zaragoza.getBackground().setAlpha(0);
        btn_Huesca.getBackground().setAlpha(0);
        btn_Belchite.getBackground().setAlpha(0);
        btn_Teruel.getBackground().setAlpha(0);
        btn_SierraMoncayo.getBackground().setAlpha(0);
        btn_BajoAragon.getBackground().setAlpha(0);
        btn_Somontano.getBackground().setAlpha(0);



        btn_Zaragoza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.zaragoza.es/sede/"));
                startActivity(i);
            }
        });

        btn_Huesca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.huesca.es/inicio"));
                startActivity(i);
            }
        });

        btn_Belchite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://belchite.es/"));
                startActivity(i);
            }
        });

        btn_Teruel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://sede.teruel.es/portal/"));
                startActivity(i);
            }
        });

        btn_SierraMoncayo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.aragon.es/-/parque-natural-del-moncayo"));
                startActivity(i);
            }
        });

        btn_BajoAragon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://bajoaragon.es/"));
                startActivity(i);
            }
        });

        btn_Somontano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.somontano.org/"));
                startActivity(i);
            }
        });
    }
}