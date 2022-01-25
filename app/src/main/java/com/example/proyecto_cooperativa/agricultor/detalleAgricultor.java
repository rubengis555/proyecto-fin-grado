package com.example.proyecto_cooperativa.agricultor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.bd.Controlador;
import com.example.proyecto_cooperativa.modelo.Personas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class detalleAgricultor extends AppCompatActivity {

    EditText txtDni, txtNombre, txtDireccion, txtTelefono, txtCorreo, txtZonas;
    TextView viewSpinnerZonas;
    Button btnGuarda;
    Spinner spZonas;


    FloatingActionButton fabEditar;
    FloatingActionButton fabEliminar;

    Personas persona;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_agricultor);

        txtDni = findViewById(R.id.txtDni);
        txtNombre = findViewById(R.id.txtNombre);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtZonas = findViewById(R.id.txtZonas);
        viewSpinnerZonas = findViewById(R.id.viewSpinnerZonas);
        spZonas = findViewById(R.id.spZonas);
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
        Controlador db = new Controlador(detalleAgricultor.this);
        persona = db.verPersonas(id);

        if(persona !=null){
            txtDni.setText(persona.getDni());
            txtNombre.setText(persona.getNombre());
            txtDireccion.setText(persona.getDireccion());
            txtTelefono.setText(persona.getTelefono());
            txtCorreo.setText(persona.getCorreo());
            txtZonas.setText(persona.getZonas());
            // hacer invisible el boton
            btnGuarda.setVisibility(View.INVISIBLE);
            viewSpinnerZonas.setVisibility(View.INVISIBLE);
            spZonas.setVisibility(View.INVISIBLE);
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
                Intent i = new Intent (detalleAgricultor.this, editarAgricultor.class);
                i.putExtra("ID", id);
                startActivity(i);
            }
        });
        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(detalleAgricultor.this);
                builder.setMessage("¿Desea eliminar este registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(db.eliminarAgricultor(id)){
                                    mostrarAgricultores();
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
    private void mostrarAgricultores(){
        Intent i = new Intent(detalleAgricultor.this, mostrarAgricultores.class);
        startActivity(i);
    }
}