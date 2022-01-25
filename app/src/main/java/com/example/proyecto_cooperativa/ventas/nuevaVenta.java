package com.example.proyecto_cooperativa.ventas;

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
import com.example.proyecto_cooperativa.cliente.nuevoCliente;
import com.example.proyecto_cooperativa.mercancias.mostrarMercancias;
import com.example.proyecto_cooperativa.mercancias.nueva_mercancia;
import com.example.proyecto_cooperativa.modelo.Personas;

import java.util.ArrayList;

public class nuevaVenta extends AppCompatActivity {

    EditText txtFactura, txtProducto, txtCantidad,txtPrecio;
    Spinner spinnerCliente;
    Button btnGuardar;
    ArrayList<String> listaClientes;
    ArrayList<Personas> personasList;
    Button btn_verVentas, btn_nuevoCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_venta);

        Controlador db = new Controlador(nuevaVenta.this);
        txtFactura = findViewById(R.id.txtFactura);
        txtProducto = findViewById(R.id.txtProducto);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtPrecio = findViewById(R.id.txtPrecio);
        spinnerCliente = findViewById(R.id.spinnerCliente);
        btnGuardar = findViewById(R.id.btnGuardar);
        btn_verVentas = findViewById(R.id.btn_verVenta);
        btn_nuevoCliente = findViewById(R.id.btn_nuevoCliente);

        personasList = db.ClienteSpinner();
        obtenerLista();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.style_spinner, listaClientes);
        spinnerCliente.setAdapter(adaptador);

        spinnerCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_nuevoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(nuevaVenta.this, nuevoCliente.class);
                startActivity(i);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String factura = txtFactura.getText().toString();
                String producto = txtProducto.getText().toString();

                int idCombo = (int) spinnerCliente.getSelectedItemId();


                    if (idCombo != 0 && !txtFactura.getText().toString().equals("") && !txtProducto.getText().toString().equals("")
                            && !txtCantidad.getText().toString().equals("") && !txtPrecio.getText().toString().equals("")  ) {

                        int idCliente = personasList.get(idCombo - 1).getId();
                        double cantidad = Double.parseDouble(txtCantidad.getText().toString());
                        double precio = Double.parseDouble(txtPrecio.getText().toString());

                        Controlador con = new Controlador(nuevaVenta.this);
                        long id = con.insertarVenta(factura, producto, cantidad, precio, idCliente);

                        if (id > 0) {
                            Toast.makeText(nuevaVenta.this, "Registro guardado", Toast.LENGTH_SHORT).show();
                            limpiarTextos();
                        } else {
                            Toast.makeText(nuevaVenta.this, "Error al guardar registro", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(nuevaVenta.this, "Debe rellenar todos los campos obligatorios", Toast.LENGTH_SHORT).show();
                    }


            }

        });
        btn_verVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(nuevaVenta.this, mostrarVentas.class);
                startActivity(i);

            }
        });
    }
    private void limpiarTextos() {
        txtFactura.setText("");
        txtProducto.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
        spinnerCliente.setSelection(0);
    }


    public void obtenerLista(){
        listaClientes =new ArrayList<String>();
        listaClientes.add("Seleccione");

        for(int i=0;i<personasList.size();i++){
            //listaAgricultores.add(personasList.get(i).getId() + " - " +personasList.get(i).getNombre());
            listaClientes.add(personasList.get(i).getDni() + " - " +    personasList.get(i).getNombre());

        }
    }

    public void iraMostrarMercancias (View view){
        Intent i= new Intent(this,mostrarMercancias.class);
        startActivity(i);
    }
}