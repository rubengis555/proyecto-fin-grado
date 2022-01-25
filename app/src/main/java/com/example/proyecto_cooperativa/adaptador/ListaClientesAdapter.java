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
import com.example.proyecto_cooperativa.cliente.detalleCliente;
import com.example.proyecto_cooperativa.modelo.Personas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ClienteViewHolder> {
    ArrayList<Personas> listaClientes;
    ArrayList<Personas> listaOriginal;


    public ListaClientesAdapter(ArrayList<Personas> listaClientes){
        this.listaClientes = listaClientes;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaClientes);
    }
    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_clientes,null,false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        holder.viewDni.setText("DNI: "+listaClientes.get(position).getDni());
        holder.viewNombre.setText("Nombre: "+listaClientes.get(position).getNombre());
        holder.viewDireccion.setText("Dirección: "+listaClientes.get(position).getDireccion());
        holder.viewTelefono.setText("Teléfono: "+listaClientes.get(position).getTelefono());
        holder.viewCorreo.setText("Correo: "+listaClientes.get(position).getCorreo());

    }
    public void filtrarDatosCliente(String txtBuscar) {
        int longitud=txtBuscar.length();
        if (longitud == 0) {
            listaClientes.clear();
            listaClientes.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Personas> colecion =listaClientes.stream().filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaClientes.clear();
                listaClientes.addAll(colecion);
            }else{
                for(Personas p: listaOriginal){
                    if(p.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaClientes.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView viewDni, viewNombre, viewDireccion, viewTelefono, viewCorreo;

        public ClienteViewHolder(@NonNull View itemView) {

            super(itemView);
            viewDni = itemView.findViewById(R.id.viewDni);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context =v.getContext();
                    Intent i = new Intent(context, detalleCliente.class);
                    i.putExtra("ID",listaClientes.get(getAdapterPosition()).getId());
                    context.startActivity(i);
                }
            });
        }
    }
}
