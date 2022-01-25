package com.example.proyecto_cooperativa.modelo;

public class Personas {
    private int id;
    private String dni;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String zonas;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public void setZonas(String zonas) {
        this.zonas = zonas;
    }

    public String getZonas() {
        return zonas;
    }
}
