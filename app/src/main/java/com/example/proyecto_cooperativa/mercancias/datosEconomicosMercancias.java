package com.example.proyecto_cooperativa.mercancias;

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
import com.example.proyecto_cooperativa.modelo.Mercancias;
import com.example.proyecto_cooperativa.modelo.Personas;

import java.util.ArrayList;

public class datosEconomicosMercancias extends AppCompatActivity {

    Spinner spinnerAgricultor;
    TextView txtImporteTotal;
    ListView lv;
    ArrayList<String> listaAgricultores;
    ArrayList<String> lista;
    ArrayList<Personas> personasList;
    ArrayList<Mercancias> listaResultados;
    ArrayAdapter adapter;
    Double resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_economicos_mercancias);

        Controlador db = new Controlador(datosEconomicosMercancias.this);

        spinnerAgricultor = findViewById(R.id.spinnerAgricultor);
        lv = (ListView) findViewById(R.id.lv);
        txtImporteTotal = findViewById(R.id.txtImporteTotal);

        resultado = db.mostrarTotal();
        txtImporteTotal.setText(resultado + " euros");


        personasList = db.AgricultoresSpinner();
        obtenerListaAgricultores();

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






    }
    private void obtenerListaAgricultores(){
        listaAgricultores =new ArrayList<String>();
        listaAgricultores.add("Seleccione");

        for(int i=0;i<personasList.size();i++){
            //listaAgricultores.add(personasList.get(i).getId() + " - " +personasList.get(i).getNombre());
            listaAgricultores.add(personasList.get(i).getDni() + " - " +    personasList.get(i).getNombre());

        }
    }

    private void mostrarRegistrosMercancias(){
        lista =new ArrayList<String>();

        for(int i=0; i<listaResultados.size();i++){
            lista.add( "Número de factura: "+ listaResultados.get(i).getFactura()+"\n"+"Producto: "+listaResultados.get(i).getProducto() +
                    "\n"+ "Cantidad: "+listaResultados.get(i).getCantidad()+" euros"+ "\n"
                    + "Importe: "+listaResultados.get(i).getPrecio() +" euros");
        }

    }
    public void realizado(View view) {
        Controlador db = new Controlador(datosEconomicosMercancias.this);
        int idCombo = (int) spinnerAgricultor.getSelectedItemId();
        if (idCombo != 0) {
            int idAgricultor = personasList.get(idCombo - 1).getId();
            listaResultados = db.mostarMercanciasAgricultores(idAgricultor);
            mostrarRegistrosMercancias();

            adapter = new ArrayAdapter(this, R.layout.style_spinner, lista);
            lv.setAdapter(adapter);

        } else {
            Toast.makeText(this, "Debe seleccionar un agricultor", Toast.LENGTH_SHORT).show();
        }
    }
}