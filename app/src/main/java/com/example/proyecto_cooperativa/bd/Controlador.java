package com.example.proyecto_cooperativa.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyecto_cooperativa.modelo.Mercancias;
import com.example.proyecto_cooperativa.modelo.Personas;
import com.example.proyecto_cooperativa.modelo.Ventas;

import java.util.ArrayList;

public class Controlador extends BaseCooperativa {

    Context context;


    public Controlador(@Nullable Context context) {
        super(context);
        this.context = context;

    }

    //Registro de usuarios

    /**
     * función para insertar un usuario
     * @param usuario nombre de usuario
     * @param password contraseña de usuario
     * @return boolean
     */
    public Boolean insertaUsuario (String usuario, String password) {

        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario", usuario);
        values.put("password", password);

        long result = data.insert(TABLE_USUARIOS, null, values);

        if (result == -1) {
            return false;
        }else{
            return true;
    }


    }

    /**
     * función para resetear el password
     * @param usuario nombre de usuario
     * @param password contraseña de usuario
     * @return boolean
     */
    public Boolean updateUsuario (String usuario, String password) {

        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", password);
        long result = data.update(TABLE_USUARIOS, values, "usuario = ?", new String[] {usuario});

        if (result == -1) {
            return false;
        }else{
            return true;
        }


    }

    /**
     * función para comprobar si el usuario que deseamos insertar existe
     * @param usuario nombre de usuario
     * @return boolean
     */
    public Boolean checkUsuario (String usuario){
        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();
        Cursor cursor = data.rawQuery("select * from t_usuarios where usuario =?",new String[] {usuario});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }

    }

    /**
     * función para comprobar usuario y contraseña y poder logarnos
     * @param usuario nombre de usuario
     * @param password contraseña de usuario
     * @return boolean
     */
    public Boolean checkUsuarioContra (String usuario, String password){
        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();
        Cursor cursor = data.rawQuery("select * from t_usuarios where usuario =? and password = ?",new String[] {usuario, password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    //Funciones para insertar datos

    /**
     * función para insertar un agricultor
     * @param dni documento del agricultor
     * @param nombre nombre y apellidos del agricultor
     * @param direccion dirección postal del agricultor
     * @param telefono teléfono del agricultor
     * @param correo correo electrónico del agricultor
     * @param zonas zonas agricolas del agricultor
     * @return id de agricultor
     */
    public long insertaAgricultor(String dni, String nombre, String direccion, String telefono, String correo, String zonas) {
        long id = 0;

        try {
            BaseCooperativa db = new BaseCooperativa(context);
            SQLiteDatabase data = db.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("dni", dni);
            values.put("nombre", nombre);
            values.put("direccion", direccion);
            values.put("telefono", telefono);
            values.put("correo", correo);
            values.put("zonas", zonas);

            id = data.insert(TABLE_AGRICULTORES, null, values);
        } catch (Exception e) {
            e.toString();
        }

        return id;
    }

    /**
     * función para insertar una mercancia
     * @param factura número de factura de mercancia
     * @param producto tipo de producto
     * @param cantidad cantidad de producto
     * @param precio precio en euros de la factura
     * @param id_agricultor agricultor asociado a la mercancia
     * @return id de mercancia
     */
    public long insertarMercancia(String factura, String producto, double cantidad, double precio, int id_agricultor) {
        long id = 0;
        try {
            BaseCooperativa db = new BaseCooperativa(context);
            SQLiteDatabase data = db.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("factura", factura);
            values.put("producto", producto);
            values.put("cantidad", cantidad);
            values.put("precio", precio);
            values.put("id_agricultor", id_agricultor);
            id = data.insert("t_mercancias", null, values);


        } catch (Exception e) {
            e.toString();
        }

        return id;
    }

    /**
     * función para insertar una venta
     * @param factura número de factura de venta
     * @param producto producto vendido
     * @param cantidad cantidad vendida de producto
     * @param precio importe de la factura de ventas
     * @param id_cliente cliente asociado a la factura
     * @return id de venta
     */
    public long insertarVenta(String factura, String producto, double cantidad, double precio, int id_cliente) {
        long id = 0;
        try {
            BaseCooperativa db = new BaseCooperativa(context);
            SQLiteDatabase data = db.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("factura", factura);
            values.put("producto", producto);
            values.put("cantidad", cantidad);
            values.put("precio", precio);
            values.put("id_cliente", id_cliente);
            id = data.insert("t_ventas", null, values);


        } catch (Exception e) {
            e.toString();
        }

        return id;
    }

    /**
     * función para insertar un cliente
     * @param dni documento del cliente
     * @param nombre nombre y apellidos del cliente
     * @param direccion dirección postal del cliente
     * @param telefono teléfono del cliente
     * @param correo correo electrónico del cliente
     * @return id de cliente
     */
    public long insertarCliente(String dni, String nombre, String direccion, String telefono, String correo) {
        long id = 0;

        try {
            BaseCooperativa db = new BaseCooperativa(context);
            SQLiteDatabase data = db.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("dni", dni);
            values.put("nombre", nombre);
            values.put("direccion", direccion);
            values.put("telefono", telefono);
            values.put("correo", correo);

            id = data.insert(TABLE_CLIENTES, null, values);
        } catch (Exception e) {
            e.toString();
        }

        return id;
    }

    //Funciones para mostrar los datos


    /**
     * función para mostrar todos los agricultores
     * @return ArrayList de personas
     */
    public ArrayList<Personas> mostrarAgricultores() {
        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();

        ArrayList<Personas> listaAgricultores = new ArrayList<>();
        Personas persona = null;
        Cursor cursorPersonas = null;

        cursorPersonas = data.rawQuery("SELECT * FROM " + TABLE_AGRICULTORES, null);

        if (cursorPersonas.moveToFirst()) {
            do {
                persona = new Personas();
                persona.setId(cursorPersonas.getInt(0));
                persona.setDni(cursorPersonas.getString(1));
                persona.setNombre(cursorPersonas.getString(2));
                persona.setDireccion(cursorPersonas.getString(3));
                persona.setTelefono(cursorPersonas.getString(4));
                persona.setCorreo(cursorPersonas.getString(5));
                persona.setZonas(cursorPersonas.getString(6));
                listaAgricultores.add(persona);

            } while (cursorPersonas.moveToNext());
        }
        cursorPersonas.close();
        return listaAgricultores;
    }

    /**
     * función para mostrar todos los Clientes
     * @return ArrayList de personas
     */
    public ArrayList<Personas> mostrarClientes() {
        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();

        ArrayList<Personas> listaClientes = new ArrayList<>();
        Personas persona = null;
        Cursor CursorClientes = null;

        CursorClientes = data.rawQuery("SELECT * FROM " + TABLE_CLIENTES, null);

        if (CursorClientes.moveToFirst()) {
            do {
                persona = new Personas();
                persona.setId(CursorClientes.getInt(0));
                persona.setDni(CursorClientes.getString(1));
                persona.setNombre(CursorClientes.getString(2));
                persona.setDireccion(CursorClientes.getString(3));
                persona.setTelefono(CursorClientes.getString(4));
                persona.setCorreo(CursorClientes.getString(5));
                listaClientes.add(persona);

            } while (CursorClientes.moveToNext());
        }
        CursorClientes.close();
        return listaClientes;
    }

    /**
     * función para mostrar todas las Mercancias
     * @return ArrayList de mercancias
     */
    public ArrayList<Mercancias> mostrarMercancias() {
        BaseCooperativa con = new BaseCooperativa(context);
        SQLiteDatabase data = con.getReadableDatabase();

        Mercancias mercancia = null;
        ArrayList<Mercancias> listaMercancias = new ArrayList<>();

        String sql = "select t_mercancias.id_mercancia, t_mercancias.factura AS fact,t_mercancias.producto AS prod,t_mercancias.cantidad AS cant,t_mercancias.precio AS prec,t_agricultores.nombre AS nom FROM t_agricultores INNER JOIN t_mercancias \n" +
                "ON              t_mercancias.id_agricultor=t_agricultores.id ";

        Cursor cursor = data.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                mercancia = new Mercancias();
                mercancia.setId(cursor.getInt(0));
                mercancia.setFactura(cursor.getString(1));
                mercancia.setProducto(cursor.getString(2));
                mercancia.setCantidad(cursor.getDouble(3));
                mercancia.setPrecio(cursor.getDouble(4));
                mercancia.setNombreAgricultor(cursor.getString(5));


                listaMercancias.add(mercancia);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaMercancias;
    }

    /**
     * función para mostrar todas las Ventas
     * @return ArrayList de ventas
     */
    public ArrayList<Ventas> mostrarVentas() {
        BaseCooperativa con = new BaseCooperativa(context);
        SQLiteDatabase data = con.getReadableDatabase();

        Ventas venta = null;
        ArrayList<Ventas> listaVentas = new ArrayList<>();


        String sql = "select t_ventas.id_venta, t_ventas.factura AS fact,t_ventas.producto AS prod,t_ventas.cantidad AS cant,t_ventas.precio AS prec,t_clientes.nombre AS nom FROM t_clientes INNER JOIN t_ventas \n" +
                "ON              t_ventas.id_cliente=t_clientes.id WHERE id_cliente=id_cliente";

        Cursor cursor = data.rawQuery(sql, null);


        if (cursor.moveToFirst()) {
            do {
                venta = new Ventas();
                venta.setId(cursor.getInt(0));
                venta.setFactura(cursor.getString(1));
                venta.setProducto(cursor.getString(2));
                venta.setCantidad(cursor.getDouble(3));
                venta.setPrecio(cursor.getDouble(4));
                venta.setNombreCliente(cursor.getString(5));


                listaVentas.add(venta);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaVentas;
    }

    //Funciones para seleccionar un registro por id

    /**
     * función para seleccionar un agricultor
     * @param id id de agricultor
     * @return objeto persona
     */
    public Personas verPersonas(int id) {
        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();


        Personas persona = null;
        Cursor cursorPersonas = null;

        cursorPersonas = data.rawQuery("SELECT * FROM " + TABLE_AGRICULTORES + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorPersonas.moveToFirst()) {

            persona = new Personas();
            persona.setId(cursorPersonas.getInt(0));
            persona.setDni(cursorPersonas.getString(1));
            persona.setNombre(cursorPersonas.getString(2));
            persona.setDireccion(cursorPersonas.getString(3));
            persona.setTelefono(cursorPersonas.getString(4));
            persona.setCorreo(cursorPersonas.getString(5));
            persona.setZonas(cursorPersonas.getString(6));


        }
        cursorPersonas.close();
        return persona;
    }

    /**
     * función para seleccionar un cliente
     * @param id de cliente
     * @return objeto persona
     */
    public Personas verClientes(int id) {
        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();


        Personas persona = null;
        Cursor cursorPersonas = null;

        cursorPersonas = data.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorPersonas.moveToFirst()) {

            persona = new Personas();
            persona.setId(cursorPersonas.getInt(0));
            persona.setDni(cursorPersonas.getString(1));
            persona.setNombre(cursorPersonas.getString(2));
            persona.setDireccion(cursorPersonas.getString(3));
            persona.setTelefono(cursorPersonas.getString(4));
            persona.setCorreo(cursorPersonas.getString(5));


        }
        cursorPersonas.close();
        return persona;
    }

    /**
     * función para seleccionar una mercancia
     * @param id de mercancia
     * @return objeto mercancia
     */
    public Mercancias verMercancias(int id) {
        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();


        Mercancias mercancia = null;
        Cursor cursorPersonas = null;

        String sql = "select t_mercancias.id_mercancia, t_mercancias.factura AS fact,t_mercancias.producto AS prod,t_mercancias.cantidad AS cant,t_mercancias.precio AS prec,t_agricultores.nombre AS nom FROM t_agricultores INNER JOIN t_mercancias \n" +
                "ON              t_mercancias.id_agricultor=t_agricultores.id WHERE id_mercancia='" + id + "'";

        cursorPersonas = data.rawQuery(sql, null);

        if (cursorPersonas.moveToFirst()) {

            mercancia = new Mercancias();
            mercancia.setId(cursorPersonas.getInt(0));
            mercancia.setFactura(cursorPersonas.getString(1));
            mercancia.setProducto(cursorPersonas.getString(2));
            mercancia.setCantidad(cursorPersonas.getDouble(3));
            mercancia.setPrecio(cursorPersonas.getDouble(4));
            mercancia.setNombreAgricultor(cursorPersonas.getString(5));


        }
        cursorPersonas.close();
        return mercancia;
    }

    /**
     * función para seleccionar una venta
     * @param id de venta
     * @return objeto venta
     */
    public Ventas verVentas(int id) {
        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();


        Ventas venta = null;
        Cursor cursorVentas = null;
        String sql = "select t_ventas.id_venta, t_ventas.factura AS fact,t_ventas.producto AS prod,t_ventas.cantidad AS cant,t_ventas.precio AS prec,t_clientes.nombre AS nom FROM t_clientes INNER JOIN t_ventas \n" +
                "ON              t_ventas.id_cliente=t_clientes.id WHERE id_venta='" + id + "'";

        cursorVentas = data.rawQuery(sql, null);


        if (cursorVentas.moveToFirst()) {

            venta = new Ventas();
            venta.setId(cursorVentas.getInt(0));
            venta.setFactura(cursorVentas.getString(1));
            venta.setProducto(cursorVentas.getString(2));
            venta.setCantidad(cursorVentas.getDouble(3));
            venta.setPrecio(cursorVentas.getDouble(4));
            venta.setNombreCliente(cursorVentas.getString(5));


        }
        cursorVentas.close();
        return venta;
    }

    //Funciones para editar los datos


    /**
     * función para editar los datos de un agricultor
     * @param id código de agricultor
     * @param dni documento del agricultor
     * @param nombre nombre y apellidos del agricultor
     * @param direccion direccion del agricultor
     * @param telefono teléfono del agricultor
     * @param correo correo electrónico del agricultor
     * @param zonas zona de agricultura del agricultor
     * @return boolean
     */
    public boolean editarAgricultor(int id, String dni, String nombre, String direccion, String telefono, String correo, String zonas) {
        boolean correcto = false;

        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();
        try {

            data.execSQL(" UPDATE " + TABLE_AGRICULTORES + " SET dni = '" + dni + "', nombre = '" + nombre + "',direccion = '" + direccion + "',telefono = '" + telefono + "',correo = '" + correo + "', zonas = '" + zonas + "'WHERE id='" + id + "'");
            correcto = true;

        } catch (Exception e) {
            e.toString();
            correcto = false;
        } finally {
            data.close();
        }

        return correcto;
    }

    /**
     * función para editar los datos de una mercancia
     * @param id id de factura
     * @param factura numero de factura
     * @param producto tipo de producto
     * @param cantidad cantidad de producto
     * @param precio precio del producto en euros
     * @param idAgricultor código del agricultor de la mercancia
     * @return boolean
     */
    public boolean editarMercancia(int id, String factura, String producto, String cantidad, String precio, int idAgricultor) {
        boolean correcto = false;

        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();
        try {

            data.execSQL(" UPDATE " + TABLE_MERCANCIAS + " SET factura = '" + factura + "', producto = '" + producto + "',cantidad = '" + cantidad + "',precio = '" + precio + "',id_agricultor = '" + idAgricultor + "'WHERE id_mercancia='" + id + "'");
            correcto = true;

        } catch (Exception e) {
            e.toString();
            correcto = false;
        } finally {
            data.close();
        }

        return correcto;
    }

    /**
     * función para editar los datos de una venta
     * @param id código de venta
     * @param factura número o código de la factura
     * @param producto nombre del producto vendido
     * @param cantidad cantidad vendida
     * @param precio importe de la factura de ventas
     * @param idCliente Cliene asociado a la venta
     * @return
     */

    public boolean editarVenta(int id, String factura, String producto, String cantidad, String precio, int idCliente) {
        boolean correcto = false;

        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();
        try {

            data.execSQL(" UPDATE " + TABLE_VENTAS + " SET factura = '" + factura + "', producto = '" + producto + "',cantidad = '" + cantidad + "',precio = '" + precio + "', id_cliente = '" + idCliente + "'WHERE id_venta='" + id + "'");
            correcto = true;

        } catch (Exception e) {
            e.toString();
            correcto = false;
        } finally {
            data.close();
        }

        return correcto;
    }

    /**
     * función para editar los datos de un cliente
     * @param id código de cliente
     * @param dni documento identificativo de identidad del cliente
     * @param nombre nombre y apellidos del cliente
     * @param direccion dirección postal del cliente
     * @param telefono teléfono del cliente
     * @param correo correo electrónico del cliente
     * @return boolean
     */
    public boolean editarCliente(int id, String dni, String nombre, String direccion, String telefono, String correo) {
        boolean correcto = false;

        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();
        try {

            data.execSQL(" UPDATE " + TABLE_CLIENTES + " SET dni = '" + dni + "', nombre = '" + nombre + "',direccion = '" + direccion + "',telefono = '" + telefono + "',correo = '" + correo + "'WHERE id='" + id + "'");
            correcto = true;

        } catch (Exception e) {
            e.toString();
            correcto = false;
        } finally {
            data.close();
        }

        return correcto;
    }

    //Funciones para eliminar datos

    /**
     * función para eliminar un agricultor
     * @param id código de agricultor
     * @return boolean
     */
    public boolean eliminarAgricultor(int id) {
        boolean correcto = false;

        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();
        try {

            data.execSQL("DELETE FROM " + TABLE_AGRICULTORES + " WHERE id ='" + id + "'");
            correcto = true;

        } catch (Exception e) {
            e.toString();
            correcto = false;
        } finally {
            data.close();
        }

        return correcto;
    }

    /**
     * función para eliminar una mercancia
     * @param id código de mercancia
     * @return boolean
     */
    public boolean eliminarMercancia(int id) {
        boolean correcto = false;

        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();
        try {

            data.execSQL("DELETE FROM " + TABLE_MERCANCIAS + " WHERE id_mercancia ='" + id + "'");
            correcto = true;

        } catch (Exception e) {
            e.toString();
            correcto = false;
        } finally {
            data.close();
        }

        return correcto;
    }

    /**
     * función para eliminar una venta
     * @param id código de venta
     * @return boolean
     */
    public boolean eliminarVenta(int id) {
        boolean correcto = false;

        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();
        try {

            data.execSQL("DELETE FROM " + TABLE_VENTAS + " WHERE id_venta ='" + id + "'");
            correcto = true;

        } catch (Exception e) {
            e.toString();
            correcto = false;
        } finally {
            data.close();
        }

        return correcto;
    }

    /**
     * función para eliminar un cliente
     * @param id código de cliente
     * @return boolean
     */
    public boolean eliminarCliente(int id) {
        boolean correcto = false;

        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();
        try {

            data.execSQL("DELETE FROM " + TABLE_CLIENTES + " WHERE id ='" + id + "'");
            correcto = true;

        } catch (Exception e) {
            e.toString();
            correcto = false;
        } finally {
            data.close();
        }

        return correcto;
    }

    //Funciones para mostrar los datos en spinner

    /**
     * función para mostrar los datos de los agricultores en un spinner
     * @return arraylist de personas
     */
    public ArrayList<Personas> AgricultoresSpinner() {
        BaseCooperativa con = new BaseCooperativa(context);
        SQLiteDatabase data = con.getReadableDatabase();

        ArrayList<Personas> personasList = new ArrayList<Personas>();
        Personas persona = null;
        Cursor cursorPersonas = null;

        cursorPersonas = data.rawQuery("SELECT * FROM " + TABLE_AGRICULTORES + " ORDER BY " + "nombre", null);

        while (cursorPersonas.moveToNext()) {

            persona = new Personas();
            persona.setId(cursorPersonas.getInt(0));
            persona.setDni(cursorPersonas.getString(1));
            persona.setNombre(cursorPersonas.getString(2));
            persona.setDireccion(cursorPersonas.getString(3));
            persona.setTelefono(cursorPersonas.getString(4));
            persona.setCorreo(cursorPersonas.getString(5));
            personasList.add(persona);

        }
        cursorPersonas.close();
        return personasList;

    }

    /**
     * función para mostrar los datos de los clientes en un spinner
     * @return arraylist de personas
     */
    public ArrayList<Personas> ClienteSpinner() {
        BaseCooperativa con = new BaseCooperativa(context);
        SQLiteDatabase data = con.getReadableDatabase();

        ArrayList<Personas> personasList = new ArrayList<Personas>();
        Personas persona = null;
        Cursor cursorPersonas = null;

        cursorPersonas = data.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " ORDER BY " + "nombre", null);

        while (cursorPersonas.moveToNext()) {

            persona = new Personas();
            persona.setId(cursorPersonas.getInt(0));
            persona.setDni(cursorPersonas.getString(1));
            persona.setNombre(cursorPersonas.getString(2));
            persona.setDireccion(cursorPersonas.getString(3));
            persona.setTelefono(cursorPersonas.getString(4));
            persona.setCorreo(cursorPersonas.getString(5));
            personasList.add(persona);

        }
        cursorPersonas.close();
        return personasList;

    }

    // listview para mostrar las facturas de mercancias por agricultor

    /**
     * función para mostrar las facturas de mercancias por agricultor seleccionado
     * @param id código de agricultor seleccionado
     * @return arraylist de mercancias
     */
    public ArrayList<Mercancias> mostarMercanciasAgricultores(int id) {
        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();

        ArrayList<Mercancias> listaResultados = new ArrayList<>();
        Mercancias mercancia = null;
        Cursor cursorMercancias = null;

        String sql = "select *  FROM t_mercancias  WHERE id_agricultor='" + id + "'";

        cursorMercancias = data.rawQuery(sql, null);

        while (cursorMercancias.moveToNext()) {

            mercancia = new Mercancias();
            mercancia.setId(cursorMercancias.getInt(0));
            mercancia.setFactura(cursorMercancias.getString(1));
            mercancia.setProducto(cursorMercancias.getString(2));
            mercancia.setCantidad(cursorMercancias.getDouble(3));
            mercancia.setPrecio(cursorMercancias.getDouble(4));

            listaResultados.add(mercancia);


        }
        cursorMercancias.close();
        return listaResultados;
    }

    // función para ver el importe total de las facturas de mercancias

    /**
     * función para mostrar la suma del importe de las facturas de mercancias
     * @return suma importe total de facturas de mercancias
     */
    public Double mostrarTotal() {
        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();
        Double resultado = null;


        Mercancias mercancia;
        Cursor cursorMercancias = data.rawQuery("SELECT SUM(precio) FROM " + TABLE_MERCANCIAS, null);

        while (cursorMercancias.moveToNext()) {
             resultado =cursorMercancias.getDouble(0);



        }
        cursorMercancias.close();

        return resultado;

    }
    // listview para mostrar las facturas de ventas por cliente

    /**
     * función para mostrar las facturas de ventas por cliente seleccionado
     * @param id código de cliente seleccionado
     * @return arraylist de ventas
     */
    public ArrayList<Ventas> mostarVentasClientes(int id) {
        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();

        ArrayList<Ventas> listaResultados = new ArrayList<>();
        Ventas venta = null;
        Cursor cursorVentas = null;

        String sql = "select *  FROM t_ventas  WHERE id_cliente='" + id + "'";

        cursorVentas = data.rawQuery(sql, null);

        while (cursorVentas.moveToNext()) {

            venta = new Ventas();
            venta.setId(cursorVentas.getInt(0));
            venta.setFactura(cursorVentas.getString(1));
            venta.setProducto(cursorVentas.getString(2));
            venta.setCantidad(cursorVentas.getDouble(3));
            venta.setPrecio(cursorVentas.getDouble(4));

            listaResultados.add(venta);


        }
        cursorVentas.close();
        return listaResultados;
    }

    // función para ver el importe total de las facturas de ventas

    /**
     * función para mostrar la suma del importe de las facturas de ventas
     * @return suma importe total de facturas de ventas
     */
    public Double mostrarTotalVentas() {
        BaseCooperativa db = new BaseCooperativa(context);
        SQLiteDatabase data = db.getWritableDatabase();
        Double resultado = null;


        Ventas ventas;
        Cursor cursorVentas = data.rawQuery("SELECT SUM(precio) FROM " + TABLE_VENTAS, null);

        while (cursorVentas.moveToNext()) {
            resultado =cursorVentas.getDouble(0);



        }
        cursorVentas.close();

        return resultado;

    }
}

