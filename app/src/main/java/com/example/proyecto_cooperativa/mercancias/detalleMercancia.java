package com.example.proyecto_cooperativa.mercancias;

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
import com.example.proyecto_cooperativa.modelo.Mercancias;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class detalleMercancia extends AppCompatActivity {
    EditText txtFactura, txtProducto, txtCantidad, txtPrecio, txtAgricultor;
    TextView viewNombreAgricultorSpinner;
    Button btnGuarda;
    FloatingActionButton fabEditar;
    FloatingActionButton fabEliminar;
    Spinner spinnerAgricultor;

    Mercancias mercancia;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mercancia);

        txtFactura = findViewById(R.id.txtFactura);
        txtProducto = findViewById(R.id.txtProducto);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtAgricultor = findViewById(R.id.txtAgricultor);
        viewNombreAgricultorSpinner = findViewById(R.id.viewNombreAgricultorSpinner);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        spinnerAgricultor = findViewById(R.id.spinnerAgricultor);

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
        Controlador db = new Controlador(detalleMercancia.this);
        mercancia = db.verMercancias(id);

        if(mercancia !=null){
            txtFactura.setText(mercancia.getFactura());
            txtProducto.setText(mercancia.getProducto());
            String cantidad = String.valueOf(mercancia.getCantidad());
            txtCantidad.setText(cantidad);
            String precio = String.valueOf(mercancia.getPrecio());
            txtPrecio.setText(precio);
            txtAgricultor.setText(mercancia.getNombreAgricultor());



            // hacer invisible el boton
            btnGuarda.setVisibility(View.INVISIBLE);
            spinnerAgricultor.setVisibility(View.INVISIBLE);
            viewNombreAgricultorSpinner.setVisibility(View.INVISIBLE);

            //para que cuando presionemos la caja de texto no se abra el teclado
            txtFactura.setInputType(InputType.TYPE_NULL);
            txtProducto.setInputType(InputType.TYPE_NULL);
            txtCantidad.setInputType(InputType.TYPE_NULL);
            txtPrecio.setInputType(InputType.TYPE_NULL);
            txtAgricultor.setInputType(InputType.TYPE_NULL);


        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (detalleMercancia.this, editarMercancia.class);
                i.putExtra("ID", id);
                startActivity(i);
            }
        });


        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(detalleMercancia.this);
                builder.setMessage("¿Desea eliminar este registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(db.eliminarMercancia(id)){
                                    mostrarListaMercancias();
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
    private void mostrarListaMercancias(){
        Intent i = new Intent(detalleMercancia.this, mostrarMercancias.class);
        startActivity(i);
    }
}