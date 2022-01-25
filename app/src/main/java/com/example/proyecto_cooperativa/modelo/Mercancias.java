package com.example.proyecto_cooperativa.modelo;

public class Mercancias {

    private int id;
    private String factura;
    private String producto;
    private double cantidad;
    private double precio;
    private int idAgricultor;
    private String nombreAgricultor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdAgricultor() {
        return idAgricultor;
    }

    public void setIdAgricultor(int idAgricultor) {
        this.idAgricultor = idAgricultor;
    }

    public String getNombreAgricultor() {
        return nombreAgricultor;
    }

    public void setNombreAgricultor(String nombreAgricultor) {this.nombreAgricultor = nombreAgricultor;}

}
