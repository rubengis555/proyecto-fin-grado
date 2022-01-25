package com.example.proyecto_cooperativa.ventas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.bd.Controlador;
import com.example.proyecto_cooperativa.modelo.Personas;
import com.example.proyecto_cooperativa.modelo.Ventas;

import java.util.ArrayList;

public class datosEconomicosVentas extends AppCompatActivity {


    Spinner spinnerCliente;
    TextView txtImporteTotal;
    ListView lv;
    ArrayList<String> listaClientes;
    ArrayList<String> lista;
    ArrayList<Personas> personasList;
    ArrayList<Ventas> listaResultados;
    ArrayAdapter adapter;
    Double resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_economicos_ventas);

        Controlador db = new Controlador(datosEconomicosVentas.this);


        spinnerCliente = findViewById(R.id.spinnerCliente);
        lv = (ListView) findViewById(R.id.lv);
        txtImporteTotal = findViewById(R.id.txtImporteTotal);

        resultado = db.mostrarTotalVentas();
        txtImporteTotal.setText(resultado +" euros");

        personasList = db.ClienteSpinner();
        obtenerListaClientes();

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



    }

    private void obtenerListaClientes(){
        listaClientes =new ArrayList<String>();
        listaClientes.add("Seleccione");

        for(int i=0;i<personasList.size();i++){
            listaClientes.add(personasList.get(i).getDni() + " - " +    personasList.get(i).getNombre());

        }
    }
    private void mostrarRegistrosVentas(){
        lista =new ArrayList<String>();

        for(int i=0; i<listaResultados.size();i++){
            lista.add( "Número de factura: "+ listaResultados.get(i).getFactura()+"\n"+"Producto: "+listaResultados.get(i).getProducto() +
                    "\n"+ "Cantidad: "+listaResultados.get(i).getCantidad()+" euros"+ "\n"
                    + "Importe: "+listaResultados.get(i).getPrecio() +" euros");
        }

    }
    public void realizados(View view) {
        Controlador db = new Controlador(datosEconomicosVentas.this);
        int idCombo = (int) spinnerCliente.getSelectedItemId();
        if (idCombo != 0) {
            int idCliente = personasList.get(idCombo - 1).getId();
            listaResultados = db.mostarVentasClientes(idCliente);
            mostrarRegistrosVentas();

            adapter = new ArrayAdapter(this, R.layout.style_spinner, lista);
            lv.setAdapter(adapter);

        } else {
            Toast.makeText(this, "Debe seleccionar un cliente", Toast.LENGTH_SHORT).show();
        }
    }
}