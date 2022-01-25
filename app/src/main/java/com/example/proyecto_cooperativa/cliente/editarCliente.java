package com.example.proyecto_cooperativa.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.bd.Controlador;
import com.example.proyecto_cooperativa.modelo.Personas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class editarCliente extends AppCompatActivity {
    EditText txtDni, txtNombre, txtDireccion, txtTelefono, txtCorreo;
    Button btnGuarda;
    FloatingActionButton fabEditar;
    FloatingActionButton fabEliminar;
    boolean correcto=false;
    Personas persona;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cliente);

        txtDni = findViewById(R.id.txtDni);
        txtNombre = findViewById(R.id.txtNombre);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras =getIntent().getExtras();
            if(extras == null){
                id= Integer.parseInt(null);

            }else{
                id=extras.getInt("ID");
            }

        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }
        Controlador db = new Controlador(editarCliente.this);
        persona = db.verClientes(id);

        if(persona !=null){
            txtDni.setText(persona.getDni());
            txtNombre.setText(persona.getNombre());
            txtDireccion.setText(persona.getDireccion());
            txtTelefono.setText(persona.getTelefono());
            txtCorreo.setText(persona.getCorreo());



        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtDni.getText().toString().equals("") && !txtNombre.getText().toString().equals("")  && !txtDireccion.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtCorreo.getText().toString().equals("")){
                    correcto= db.editarCliente(id, txtDni.getText().toString(), txtNombre.getText().toString(),txtDireccion.getText().toString(), txtTelefono.getText().toString(),txtCorreo.getText().toString());

                    if(correcto){
                        Toast.makeText(editarCliente.this, "REGISTRO MODIFICADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                        verDetalleClientes();
                    }else{
                        Toast.makeText(editarCliente.this, "ERROR AL MODIFICAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(editarCliente.this, "DEBE RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void verDetalleClientes (){
        Intent i =new Intent(this, mostrarClientes.class);
        i.putExtra("ID", id);
        startActivity(i);
    }
}


