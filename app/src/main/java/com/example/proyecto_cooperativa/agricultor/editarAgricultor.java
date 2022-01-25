package com.example.proyecto_cooperativa.agricultor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.bd.Controlador;
import com.example.proyecto_cooperativa.modelo.Personas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class editarAgricultor extends AppCompatActivity {

    EditText txtDni, txtNombre, txtDireccion, txtTelefono, txtCorreo, txtZonas;
    Button btnGuarda;
    TextView viewZonas;
    FloatingActionButton fabEditar;
    FloatingActionButton fabEliminar;
    boolean correcto=false;
    Personas persona;
    int id = 0;
    Spinner spZonas;
    String[] zonasAgricolas = {"Huesca", "Somontano","Sierra del Moncayo", "Zaragoza", "Belchite", "Bajo Aragón","Teruel"};

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
        viewZonas = findViewById(R.id.viewZonas);
        spZonas =findViewById(R.id.spZonas);

        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);
        viewZonas.setVisibility(View.INVISIBLE);
        txtZonas.setVisibility(View.INVISIBLE);

        spZonas.setAdapter(new ArrayAdapter<String>(this,R.layout.style_spinner,zonasAgricolas));

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
        Controlador db = new Controlador(editarAgricultor.this);
        persona = db.verPersonas(id);

        if(persona !=null){
            txtDni.setText(persona.getDni());
            txtNombre.setText(persona.getNombre());
            txtDireccion.setText(persona.getDireccion());
            txtTelefono.setText(persona.getTelefono());
            txtCorreo.setText(persona.getCorreo());
            txtZonas.setText(persona.getZonas());



        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zonaElegida = spZonas.getSelectedItem().toString();
                if(!txtDni.getText().toString().equals("") && !txtNombre.getText().toString().equals("")  && !txtDireccion.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtCorreo.getText().toString().equals("") ){
                   correcto= db.editarAgricultor(id, txtDni.getText().toString(), txtNombre.getText().toString(),txtDireccion.getText().toString(), txtTelefono.getText().toString(),txtCorreo.getText().toString(), zonaElegida);
                   
                   if(correcto){
                       Toast.makeText(editarAgricultor.this, "REGISTRO MODIFICADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                       verDetalleRegistro ();
                   }else{
                       Toast.makeText(editarAgricultor.this, "ERROR AL MODIFICAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                   }
                }else{
                    Toast.makeText(editarAgricultor.this, "DEBE RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verDetalleRegistro (){
        Intent i =new Intent(this, detalleAgricultor.class);
        i.putExtra("ID", id);
        startActivity(i);
    }
}

