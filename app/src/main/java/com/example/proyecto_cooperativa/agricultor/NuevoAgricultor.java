package com.example.proyecto_cooperativa.agricultor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.bd.Controlador;
import com.example.proyecto_cooperativa.mercancias.nueva_mercancia;

public class NuevoAgricultor extends AppCompatActivity {

    EditText txtDni, txtNombre, txtDireccion, txtTelefono, txtCorreo;
    Button btnGuardar, btn_registrarMercancia;
    Spinner spZonas;
    String[] zonasAgricolas = {"Huesca", "Somontano", "Sierra del Moncayo", "Zaragoza", "Belchite", "Bajo Aragón", "Teruel"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_agricultor);

        txtDni = findViewById(R.id.txtDni);
        txtNombre = findViewById(R.id.txtNombre);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnGuardar = findViewById(R.id.btnGuardar);
        btn_registrarMercancia = findViewById(R.id.btn_registrarMercancia);
        spZonas = (Spinner) findViewById(R.id.spZonas);

        btn_registrarMercancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NuevoAgricultor.this, nueva_mercancia.class);
                startActivity(i);
            }
        });


        spZonas.setAdapter(new ArrayAdapter<String>(this, R.layout.style_spinner, zonasAgricolas));


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = txtDni.getText().toString();
                String nombre = txtNombre.getText().toString();
                String direccion = txtDireccion.getText().toString();
                String telefono = txtTelefono.getText().toString();
                String correo = txtCorreo.getText().toString();
                String zonaElegida = spZonas.getSelectedItem().toString();
                Controlador con = new Controlador(NuevoAgricultor.this);
                if (!txtDni.getText().toString().equals("") && !txtNombre.getText().toString().equals("")
                        && !txtDireccion.getText().toString().equals("")
                        && !txtTelefono.getText().toString().equals("") && !txtCorreo.getText().toString().equals("")) {
                    long id = con.insertaAgricultor(dni, nombre, direccion, telefono, correo, zonaElegida);
                    if (id > 0) {
                        Toast.makeText(NuevoAgricultor.this, "Registro guardado", Toast.LENGTH_SHORT).show();
                        limpiarTextos();
                    } else {
                        Toast.makeText(NuevoAgricultor.this, "Error al guardar registro", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(NuevoAgricultor.this, "Debe rellenar los campos obligatorios", Toast.LENGTH_SHORT).show();
                }

            }


        });
    }

    private void limpiarTextos() {
        txtDni.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");

    }

    public void iraMostrarAgricultores(View view) {
        Intent i = new Intent(this, mostrarAgricultores.class);
        startActivity(i);
    }
}
