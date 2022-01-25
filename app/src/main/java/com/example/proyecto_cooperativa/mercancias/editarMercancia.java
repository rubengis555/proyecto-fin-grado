package com.example.proyecto_cooperativa.mercancias;

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
import com.example.proyecto_cooperativa.modelo.Mercancias;
import com.example.proyecto_cooperativa.modelo.Personas;
import com.example.proyecto_cooperativa.ventas.editarVenta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class editarMercancia extends AppCompatActivity {

    EditText txtFactura, txtProducto, txtCantidad, txtPrecio, txtAgricultor;
    Button btnGuarda;
    FloatingActionButton fabEditar;
    FloatingActionButton fabEliminar;
    TextView viewNombreAgricultorSpinner;
    TextView viewAgricultor;
    Spinner spinnerAgricultor;
    boolean correcto=false;
    Mercancias mercancia;
    ArrayList<Personas> personasList;
    ArrayList<String> listaAgricultores;

    int id = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mercancia);

        txtFactura = findViewById(R.id.txtFactura);
        txtProducto = findViewById(R.id.txtProducto);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtAgricultor = findViewById(R.id.txtAgricultor);
        txtAgricultor.setVisibility(View.INVISIBLE);
        viewAgricultor = findViewById(R.id.viewAgricultor);
        viewAgricultor.setVisibility(View.INVISIBLE);
        viewNombreAgricultorSpinner =findViewById(R.id.viewNombreAgricultorSpinner);
        spinnerAgricultor = findViewById(R.id.spinnerAgricultor);

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

        Controlador db = new Controlador(editarMercancia.this);
        personasList = db.AgricultoresSpinner();
        obtenerListaAgricultores();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.style_spinner, listaAgricultores);
        spinnerAgricultor.setAdapter(adaptador);
        mercancia = db.verMercancias(id);

        if(mercancia !=null){
            txtFactura.setText(mercancia.getFactura());
            txtProducto.setText(mercancia.getProducto());
            String cantidad = String.valueOf(mercancia.getCantidad());
            txtCantidad.setText(cantidad);
            String precio = String.valueOf(mercancia.getPrecio());
            txtPrecio.setText(precio);

        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idCombo= (int) spinnerAgricultor.getSelectedItemId();
                if(idCombo!=0 && !txtFactura.getText().toString().equals("") && !txtProducto.getText().toString().equals("")
                        && !txtCantidad.getText().toString().equals("")  && !txtPrecio.getText().toString().equals("")) {
                    int idAgricultor =personasList.get(idCombo - 1).getId();


                    correcto= db.editarMercancia(id, txtFactura.getText().toString(), txtProducto.getText().toString(),txtCantidad.getText().toString(), txtPrecio.getText().toString(), idAgricultor);

                    if(correcto){
                        Toast.makeText(editarMercancia.this, "REGISTRO MODIFICADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                        verDetalleMercancias ();
                    }else{
                        Toast.makeText(editarMercancia.this, "ERROR AL MODIFICAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(editarMercancia.this, "DEBE RELLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verDetalleMercancias (){
        Intent i =new Intent(this, mostrarMercancias.class);
        i.putExtra("ID", id);
        startActivity(i);
    }
    public void obtenerListaAgricultores(){
        listaAgricultores =new ArrayList<String>();
        listaAgricultores.add("Seleccione");

        for(int i=0;i<personasList.size();i++){
            //listaAgricultores.add(personasList.get(i).getId() + " - " +personasList.get(i).getNombre());
            listaAgricultores.add(personasList.get(i).getDni() + " - " +    personasList.get(i).getNombre());

        }
    }
}



