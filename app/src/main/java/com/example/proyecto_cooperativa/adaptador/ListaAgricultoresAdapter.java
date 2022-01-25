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
import com.example.proyecto_cooperativa.modelo.Personas;
import com.example.proyecto_cooperativa.agricultor.detalleAgricultor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaAgricultoresAdapter extends RecyclerView.Adapter<ListaAgricultoresAdapter.PersonaViewHolder> {

    ArrayList<Personas> listaAgricultores;
    ArrayList<Personas> listaOriginal;

    public ListaAgricultoresAdapter(ArrayList<Personas> listaAgricultores){
        this.listaAgricultores = listaAgricultores;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaAgricultores);
    }
    @NonNull
    @Override
    //diseño de la vista
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_agricultores,null,false);
        return new PersonaViewHolder(view);
    }
    //asignamos los elementos para cada opcion, dni, nombre, direccion, telefono, correo
    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        holder.viewDni.setText("DNI: "+listaAgricultores.get(position).getDni());
        holder.viewNombre.setText("Nombre: "+listaAgricultores.get(position).getNombre());
        holder.viewDireccion.setText("Dirección: "+listaAgricultores.get(position).getDireccion());
        holder.viewTelefono.setText("Teléfono: "+listaAgricultores.get(position).getTelefono());
        holder.viewCorreo.setText("Correo: "+listaAgricultores.get(position).getCorreo());
        holder.viewZonas.setText("Zona: "+listaAgricultores.get(position).getZonas());

    }


    public void filtrarDatos(String txtBuscar) {
        int longitud=txtBuscar.length();
        if (longitud == 0) {
            listaAgricultores.clear();
            listaAgricultores.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Personas> colecion =listaAgricultores.stream().filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaAgricultores.clear();
                listaAgricultores.addAll(colecion);
            }else{
                for(Personas p: listaOriginal){
                    if(p.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaAgricultores.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    //mide el tamaño de la lista
    @Override
    public int getItemCount() {
    return listaAgricultores.size();
    }

    public class PersonaViewHolder extends RecyclerView.ViewHolder {

        TextView viewDni, viewNombre, viewDireccion, viewTelefono, viewCorreo, viewZonas;

        //metodo que llena cada una de las vistas
        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            viewDni = itemView.findViewById(R.id.viewDni);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);
            viewZonas = itemView.findViewById(R.id.viewZonas);

            //metodo que sirve para que cuando presionemos sobre algun registro nos lleve al activity de modificar
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context =view.getContext();
                    Intent i = new Intent(context, detalleAgricultor.class);
                    i.putExtra("ID",listaAgricultores.get(getAdapterPosition()).getId());
                    context.startActivity(i);
                }
            });

        }
    }
}
