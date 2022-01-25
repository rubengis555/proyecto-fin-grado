package com.example.proyecto_cooperativa.cliente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.bd.Controlador;
import com.example.proyecto_cooperativa.modelo.Personas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class detalleCliente extends AppCompatActivity {
    EditText txtDni, txtNombre, txtDireccion, txtTelefono, txtCorreo;
    Button btnGuarda;
    FloatingActionButton fabEditar;
    FloatingActionButton fabEliminar;

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
        fabEliminar = findViewById(R.id.fabEliminar);

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
        Controlador db = new Controlador(detalleCliente.this);
        persona = db.verClientes(id);

        if(persona !=null){
            txtDni.setText(persona.getDni());
            txtNombre.setText(persona.getNombre());
            txtDireccion.setText(persona.getDireccion());
            txtTelefono.setText(persona.getTelefono());
            txtCorreo.setText(persona.getCorreo());
            // hacer invisible el boton
            btnGuarda.setVisibility(View.INVISIBLE);
            //para que cuando presionemos la caja de texto no se abra el teclado
            txtDni.setInputType(InputType.TYPE_NULL);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtDireccion.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtCorreo.setInputType(InputType.TYPE_NULL);


        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (detalleCliente.this, editarCliente.class);
                i.putExtra("ID", id);
                startActivity(i);
            }
        });
        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(detalleCliente.this);
                builder.setMessage("¿Desea eliminar este registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(db.eliminarCliente(id)){
                                    mostrarClientes();
                                }


                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

            }
        });
    }
    private void mostrarClientes(){
        Intent i = new Intent(detalleCliente.this, mostrarClientes.class);
        startActivity(i);
    }

}