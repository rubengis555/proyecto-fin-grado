package com.example.proyecto_cooperativa.adaptador;

import static java.lang.Integer.*;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.modelo.Personas;
import com.example.proyecto_cooperativa.ventas.detalleVenta;

import com.example.proyecto_cooperativa.modelo.Ventas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaVentasAdapter extends RecyclerView.Adapter<ListaVentasAdapter.VentasViewHolder> {
    ArrayList<Ventas> listaVentas;
    ArrayList<Ventas> listaOriginal;


    public ListaVentasAdapter(ArrayList<Ventas> listaVentas){

        this.listaVentas = listaVentas;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaVentas);


    }
    public VentasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_ventas,null,false);
        return new VentasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VentasViewHolder holder, int position) {

        holder.viewFactura.setText("Factura: "+listaVentas.get(position).getFactura());
        holder.viewProducto.setText("Producto: "+listaVentas.get(position).getProducto());
        String cantidad =String.valueOf(listaVentas.get(position).getCantidad());
        holder.viewCantidad.setText("Cantidad: "+cantidad);
        String cambio= String.valueOf(listaVentas.get(position).getPrecio());
        holder.viewImporte.setText("Importe factura: "+cambio+ " euros");
        holder.viewIdCliente.setText("Cliente: "+listaVentas.get(position).getNombreCliente());


    }
    public void filtrarDatos(String txtBuscar) {
        int longitud=txtBuscar.length();
        if (longitud == 0) {
            listaVentas.clear();
            listaVentas.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Ventas> colecion =listaVentas.stream().filter(i -> i.getFactura().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaVentas.clear();
                listaVentas.addAll(colecion);
            }else{
                for(Ventas p: listaOriginal){
                    if(p.getFactura().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaVentas.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return listaVentas.size();
    }

    public class VentasViewHolder extends RecyclerView.ViewHolder {

        TextView viewFactura, viewProducto, viewCantidad, viewImporte, viewIdCliente;

        public VentasViewHolder(@NonNull View itemView) {
            super(itemView);
            viewFactura = itemView.findViewById(R.id.viewFactura);
            viewProducto = itemView.findViewById(R.id.viewProducto);
            viewCantidad = itemView.findViewById(R.id.viewCantidad);
            viewImporte = itemView.findViewById(R.id.viewImporte);
            viewIdCliente = itemView.findViewById(R.id.viewIdCliente);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context =view.getContext();
                    Intent i =new Intent(context, detalleVenta.class);
                   i.putExtra("ID",listaVentas.get(getAdapterPosition()).getId());
                    context.startActivity(i);
                }
            });
        }
    }
}
