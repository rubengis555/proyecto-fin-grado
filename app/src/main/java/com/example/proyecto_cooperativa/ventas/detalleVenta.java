package com.example.proyecto_cooperativa.ventas;

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
import com.example.proyecto_cooperativa.modelo.Ventas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class detalleVenta extends AppCompatActivity {
    EditText txtFactura, txtProducto, txtCantidad, txtPrecio, txtNombreCliente;
    TextView viewNombreClienteSpinner;
    Button btnGuarda;
    FloatingActionButton fabEditar;
    FloatingActionButton fabEliminar;
    Spinner spinnerCliente;


    Ventas venta;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_venta);

        txtFactura = findViewById(R.id.txtFactura);
        txtProducto = findViewById(R.id.txtProducto);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtNombreCliente = findViewById(R.id.txtNombreCliente);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        viewNombreClienteSpinner = findViewById(R.id.viewNombreClienteSpinner);
        spinnerCliente = findViewById(R.id.spinnerCliente);

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
        Controlador db = new Controlador(detalleVenta.this);
        venta = db.verVentas(id);

        if(venta !=null){
            txtFactura.setText(venta.getFactura());
            txtProducto.setText(venta.getProducto());
            String cantidad =String.valueOf(venta.getCantidad());
            txtCantidad.setText(cantidad);
            String precio = String.valueOf(venta.getPrecio());
            txtPrecio.setText(precio);
            txtNombreCliente.setText(venta.getNombreCliente());


            // hacer invisible el boton
            btnGuarda.setVisibility(View.INVISIBLE);
            viewNombreClienteSpinner.setVisibility(View.INVISIBLE);
            spinnerCliente.setVisibility(View.INVISIBLE);


            //para que cuando presionemos la caja de texto no se abra el teclado
            txtFactura.setInputType(InputType.TYPE_NULL);
            txtProducto.setInputType(InputType.TYPE_NULL);
            txtCantidad.setInputType(InputType.TYPE_NULL);
            txtPrecio.setInputType(InputType.TYPE_NULL);
            txtNombreCliente.setInputType(InputType.TYPE_NULL);


        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (detalleVenta.this, editarVenta.class);
                i.putExtra("ID", id);
                startActivity(i);
            }
        });


        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(detalleVenta.this);
                builder.setMessage("¿Desea eliminar este registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(db.eliminarVenta(id)){
                                    mostrarListaVentas();
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
    private void mostrarListaVentas(){
        Intent i = new Intent(detalleVenta.this, mostrarVentas.class);
        startActivity(i);
    }
}