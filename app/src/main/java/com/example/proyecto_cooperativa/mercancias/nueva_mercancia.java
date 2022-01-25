package com.example.proyecto_cooperativa.mercancias;

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
import com.example.proyecto_cooperativa.agricultor.NuevoAgricultor;
import com.example.proyecto_cooperativa.bd.Controlador;
import com.example.proyecto_cooperativa.modelo.Personas;
import com.example.proyecto_cooperativa.ventas.nuevaVenta;

import java.util.ArrayList;

public class nueva_mercancia extends AppCompatActivity {

    EditText txtFactura, txtProducto, txtCantidad,txtPrecio;
    Spinner spinnerAgricultor;
    Button btnGuardar, btn_nuevoAgricultor;
    ArrayList<String> listaAgricultores;
    ArrayList<Personas> personasList;
    Button btn_verMercancias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_mercancia);


        Controlador db = new Controlador(nueva_mercancia.this);
        txtFactura = findViewById(R.id.txtFactura);
        txtProducto = findViewById(R.id.txtProducto);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtPrecio = findViewById(R.id.txtPrecio);
        spinnerAgricultor = findViewById(R.id.spinnerAgricultor);
        btnGuardar = findViewById(R.id.btnGuardar);
        btn_verMercancias = findViewById(R.id.btn_verMercancias);
        btn_nuevoAgricultor = findViewById(R.id.btn_nuevoAgricultor);

        personasList = db.AgricultoresSpinner();
        obtenerLista();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.style_spinner, listaAgricultores);
        spinnerAgricultor.setAdapter(adaptador);

        spinnerAgricultor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_nuevoAgricultor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(nueva_mercancia.this, NuevoAgricultor.class);
                startActivity(i);

            }
        });
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String factura=txtFactura.getText().toString();
                String producto=txtProducto.getText().toString();

                int idCombo= (int) spinnerAgricultor.getSelectedItemId();


                if(idCombo!=0  &&!txtFactura.getText().toString().equals("") && !txtProducto.getText().toString().equals("")
                        && !txtCantidad.getText().toString().equals("") && !txtPrecio.getText().toString().equals("")) {
                    int idAgricultor =personasList.get(idCombo - 1).getId();
                    double cantidad = Double.parseDouble(txtCantidad.getText().toString());
                    double precio = Double.parseDouble(txtPrecio.getText().toString());
                    Controlador con = new Controlador(nueva_mercancia.this);

                    long id = con.insertarMercancia(factura, producto, cantidad, precio, idAgricultor);

                if (id > 0) {
                    Toast.makeText(nueva_mercancia.this, "Registro guardado", Toast.LENGTH_SHORT).show();
                    limpiarTextos();
                } else {
                    Toast.makeText(nueva_mercancia.this, "Error al guardar registro", Toast.LENGTH_SHORT).show();
                }
               }else {
                    Toast.makeText(nueva_mercancia.this, "Debe rellenar todos los campos obligatorios", Toast.LENGTH_SHORT).show();
            }
            }

        });
        btn_verMercancias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(nueva_mercancia.this, mostrarMercancias.class);
                startActivity(i);

            }
        });

    }



    private void limpiarTextos() {
        txtFactura.setText("");
        txtProducto.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
        spinnerAgricultor.setSelection(0);
    }


    public void obtenerLista(){
        listaAgricultores =new ArrayList<String>();
        listaAgricultores.add("Seleccione");

        for(int i=0;i<personasList.size();i++){
            //listaAgricultores.add(personasList.get(i).getId() + " - " +personasList.get(i).getNombre());
            listaAgricultores.add(personasList.get(i).getDni() + " - " +    personasList.get(i).getNombre());

        }
    }

    public void iraMostrarMercancias (View view){
        Intent i= new Intent(this,mostrarMercancias.class);
        startActivity(i);
    }



}