package com.example.proyecto_cooperativa.ventas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.cliente.mostrarClientes;
import com.example.proyecto_cooperativa.cliente.nuevoCliente;

public class principalClientesVentas extends AppCompatActivity {
Button btn_nuevoCliente, btn_listaClientes, btn_nuevaVenta, btn_listaVentas, btn_datosEconomicos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_clientes_ventas);

        btn_nuevoCliente = findViewById(R.id.btn_nuevoCliente);
        btn_listaClientes = findViewById(R.id.btn_listaClientes);
        btn_nuevaVenta = findViewById(R.id.btn_nuevaVenta);
        btn_listaVentas = findViewById(R.id.btn_listaVentas);
        btn_datosEconomicos = findViewById(R.id.btn_datosEconomicos);

        btn_nuevoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(principalClientesVentas.this, nuevoCliente.class);
                startActivity(i);
            }
        });

        btn_listaClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(principalClientesVentas.this, mostrarClientes.class);
                startActivity(i);
            }
        });

        btn_nuevaVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(principalClientesVentas.this, nuevaVenta.class);
                startActivity(i);
            }
        });

        btn_listaVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(principalClientesVentas.this, mostrarVentas.class);
                startActivity(i);
            }
        });

        btn_datosEconomicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(principalClientesVentas.this, datosEconomicosVentas.class);
                startActivity(i);
            }
        });

    }
}