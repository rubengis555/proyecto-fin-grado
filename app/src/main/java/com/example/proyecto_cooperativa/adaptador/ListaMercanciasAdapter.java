package com.example.proyecto_cooperativa.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_cooperativa.R;
import com.example.proyecto_cooperativa.mercancias.detalleMercancia;
import com.example.proyecto_cooperativa.modelo.Mercancias;
import com.example.proyecto_cooperativa.modelo.Ventas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//definimos la lista
public class ListaMercanciasAdapter extends RecyclerView.Adapter<ListaMercanciasAdapter.MercanciaViewHolder> {
    ArrayList<Mercancias> listaMercancias;
    ArrayList<Mercancias> listaOriginal;


    public ListaMercanciasAdapter(ArrayList<Mercancias> listaMercancias){
        this.listaMercancias = listaMercancias;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaMercancias);
    }
    @NonNull
    @Override
    public MercanciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_mercancias,null,false);
        return new MercanciaViewHolder(view);
    }

    @Override
    //asignamos los elementos para cada opcion
    public void onBindViewHolder(@NonNull MercanciaViewHolder holder, int position) {
        holder.viewFactura.setText("Factura: "+listaMercancias.get(position).getFactura());
        holder.viewProducto.setText("Producto: "+listaMercancias.get(position).getProducto());
        String cantidad = String.valueOf(listaMercancias.get(position).getCantidad());
        holder.viewCantidad.setText("Cantidad: "+cantidad );
        String cambio= String.valueOf(listaMercancias.get(position).getPrecio());
        holder.viewPrecio.setText("Importe factura: "+cambio + " euros");
        holder.viewIdAgricultor.setText("Agricultor: "+listaMercancias.get(position).getNombreAgricultor());
    }
    public void filtrarDatos(String txtBuscar) {
        int longitud=txtBuscar.length();
        if (longitud == 0) {
            listaMercancias.clear();
            listaMercancias.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Mercancias> colecion =listaMercancias.stream().filter(i -> i.getFactura().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaMercancias.clear();
                listaMercancias.addAll(colecion);
            }else{
                for(Mercancias p: listaOriginal){
                    if(p.getFactura().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaMercancias.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
    return listaMercancias.size();
    }

    public class MercanciaViewHolder extends RecyclerView.ViewHolder {
        TextView viewFactura, viewProducto, viewCantidad, viewPrecio, viewIdAgricultor;

        public MercanciaViewHolder(@NonNull View itemView) {
            super(itemView);
            viewFactura = itemView.findViewById(R.id.viewFactura);
            viewProducto = itemView.findViewById(R.id.viewProducto);
            viewCantidad = itemView.findViewById(R.id.viewCantidad);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);
            viewIdAgricultor = itemView.findViewById(R.id.viewIdAgricultor);
        // al momento de seleccionar un contacto nos enviara al detalle de la mercancia
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context =view.getContext();
                    Intent i =new Intent(context, detalleMercancia.class);
                    i.putExtra("ID",listaMercancias.get(getAdapterPosition()).getId());
                    context.startActivity(i);
                }
            });
        }
    }
}
