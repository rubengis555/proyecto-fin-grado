package com.example.proyecto_cooperativa.cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.agricultor.NuevoAgricultor;
import com.example.proyecto_cooperativa.bd.Controlador;
import com.example.proyecto_cooperativa.mercancias.nueva_mercancia;
import com.example.proyecto_cooperativa.ventas.nuevaVenta;

public class nuevoCliente extends AppCompatActivity {
    EditText txtDni, txtNombre, txtDireccion, txtTelefono, txtCorreo;
    Button btnGuardar, btn_registrarVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_cliente);

        txtDni = findViewById(R.id.txtDni);
        txtNombre = findViewById(R.id.txtNombre);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnGuardar =findViewById(R.id.btnGuardar);
        btn_registrarVenta = findViewById(R.id.btn_registrarVenta);



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni=txtDni.getText().toString();
                String nombre=txtNombre.getText().toString();
                String direccion=txtDireccion.getText().toString();
                String telefono=txtTelefono.getText().toString();
                String correo=txtCorreo.getText().toString();
                Controlador con = new Controlador(nuevoCliente.this);
                if (!txtDni.getText().toString().equals("") && !txtNombre.getText().toString().equals("") && !txtDireccion.getText().toString().equals("")
                        && !txtTelefono.getText().toString().equals("")  && !txtCorreo.getText().toString().equals("")) {
                long id = con.insertarCliente(dni, nombre, direccion, telefono, correo);
                if (id > 0) {
                    Toast.makeText(nuevoCliente.this, "Registro guardado", Toast.LENGTH_SHORT).show();
                    limpiarTextos();
                } else {
                    Toast.makeText(nuevoCliente.this, "Error al guardar registro", Toast.LENGTH_SHORT).show();

                }
                }else{
                    Toast.makeText(nuevoCliente.this, "Debe rellenar los campos obligatorios", Toast.LENGTH_SHORT).show();
                }

            }


        });


        btn_registrarVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(nuevoCliente.this, nuevaVenta.class);
                startActivity(i);
            }
        });
    }
    private void limpiarTextos (){
        txtDni.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");

    }

    public void iraMostrarClientes (View view){
        Intent i= new Intent(this, mostrarClientes.class);
        startActivity(i);
    }
}