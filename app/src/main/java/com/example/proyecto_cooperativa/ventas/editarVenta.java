package com.example.proyecto_cooperativa.ventas;

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
import com.example.proyecto_cooperativa.modelo.Ventas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class editarVenta extends AppCompatActivity {

    EditText txtFactura, txtProducto, txtCantidad, txtPrecio, txtNombreCliente;
    Button btnGuarda;
    FloatingActionButton fabEditar;
    FloatingActionButton fabEliminar;
    TextView viewNombreCliente;
    Spinner spinnerCliente;
    ArrayList<Personas> personasList;
    ArrayList<String> listaClientes;


    boolean correcto=false;
    Ventas venta;
    int id = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_venta);

        txtFactura = findViewById(R.id.txtFactura);
        txtProducto = findViewById(R.id.txtProducto);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtPrecio = findViewById(R.id.txtPrecio);
        spinnerCliente = findViewById(R.id.spinnerCliente);
        txtNombreCliente =findViewById(R.id.txtNombreCliente);
        viewNombreCliente = findViewById(R.id.viewNombreCliente);
        txtNombreCliente.setVisibility(View.INVISIBLE);
        viewNombreCliente.setVisibility(View.INVISIBLE);

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
        Controlador db = new Controlador(editarVenta.this);
        personasList = db.ClienteSpinner();
        obtenerListaClientes();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.style_spinner, listaClientes);
        spinnerCliente.setAdapter(adaptador);
        venta = db.verVentas(id);

        if(venta !=null){
            txtFactura.setText(venta.getFactura());
            txtProducto.setText(venta.getProducto());
            String cantidad = String.valueOf(venta.getCantidad());
            txtCantidad.setText(cantidad);
            String precio = String.valueOf(venta.getPrecio());
            txtPrecio.setText(precio);


        }




            //idAgricultor = String.valueOf(personasList.get(idCombo - 1).getId());
            //String idAgricultor = spinnerCliente.getItemAtPosition(spinnerCliente.getSelectedItemPosition()).toString();




        btnGuarda.setOnClickListener(new View.OnClickListener() {

           //int ids = spinnerCliente.getSelectedItemPosition();
            public void onClick(View v) {
            int idCombo= (int) spinnerCliente.getSelectedItemId();
                if(idCombo!=0 && !txtFactura.getText().toString().equals("") && !txtProducto.getText().toString().equals("")
                        && !txtCantidad.getText().toString().equals("")  && !txtPrecio.getText().toString().equals("") ) {
                    int idCliente =personasList.get(idCombo - 1).getId();

                //if(&& && !txtProducto.getText().toString().equals("")  && !txtCantidad.getText().toString().equals("") && !txtPrecio.getText().toString().equals("") ){
                    correcto= db.editarVenta(id, txtFactura.getText().toString(), txtProducto.getText().toString(),txtCantidad.getText().toString(), txtPrecio.getText().toString(), idCliente);

                    if(correcto){
                        Toast.makeText(editarVenta.this, "REGISTRO MODIFICADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                        verDetalleVentas ();
                    }else{
                        Toast.makeText(editarVenta.this, "ERROR AL MODIFICAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(editarVenta.this, "DEBE RELLENAR CORRECTAMENTE LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verDetalleVentas (){
        Intent i =new Intent(this, mostrarVentas.class);
        i.putExtra("ID", id);
        startActivity(i);
    }
    public void obtenerListaClientes(){
        listaClientes =new ArrayList<String>();
        listaClientes.add("Seleccione");

        for(int i=0;i<personasList.size();i++){
            //listaAgricultores.add(personasList.get(i).getId() + " - " +personasList.get(i).getNombre());
            listaClientes.add(personasList.get(i).getDni() + " - " +    personasList.get(i).getNombre());

        }
    }
}



