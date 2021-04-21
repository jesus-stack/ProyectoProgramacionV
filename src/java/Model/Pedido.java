
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Tipo.TipoDespacho;
import Model.Tipo.TipoPago;
import java.util.Date;

import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class Pedido {
    
    private int id;
    private Cliente cliente;
    private Date FechaEntrega;
    private String horaEntrega;
    private float costoEnvio;
    private TipoPago tipoPago;
    private float Descuento;
    private TipoDespacho tipoDspacho;
    private float iva;
    private float subTotal;
    private float total;
    private Direccion direccion;
    private LinkedList<TransaccionProducto> productos=new LinkedList<>();
    private int Estado;
    String listaProductos;

    public Pedido() {
        
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaEntrega() {
        return FechaEntrega;
    }

    public void setFechaEntrega(Date FechaEntrega) {
        this.FechaEntrega = FechaEntrega;
    }



    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

  

    public float getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(float costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public float getDescuento() {
        return Descuento;
    }

    public void setDescuento(float Descuento) {
        this.Descuento = Descuento;
    }

    public TipoDespacho getTipoDspacho() {
        return tipoDspacho;
    }

    public void setTipoDspacho(TipoDespacho tipoDspacho) {
        this.tipoDspacho = tipoDspacho;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

 

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public LinkedList<TransaccionProducto> getProductos() {
        return productos;
    }

    public void setProductos(LinkedList<TransaccionProducto> productos) {
        this.productos = productos;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
    
    
    
    
    
    public double calcularSubTotal(){
        double suma=0;
        for (TransaccionProducto producto : productos) {
            suma+=producto.getProducto().getPrecio()*producto.getCantidad();
        }
    
    return suma;
    }


    public void setListaProductos(String listaProductos) {
        this.listaProductos = listaProductos;
    }
    
   
    public String getListaProductos(){
        String lista="";
        for (int i = 0; i < productos.size(); i++) {
            lista+=" "+productos.get(i).getProducto().getNombre()+",";
        }
        return lista;
    }
    public double calcularIva(){
    
    return calcularSubTotal()*0.13;
    }
    
    public double calcularTotal(){
    return calcularSubTotal()+calcularIva()+costoEnvio();
    }
    
     public double descuento(int porcentaje){
    
    return (calcularTotal()*porcentaje)/100;
    }
     
    public double costoEnvio() {
        if (tipoDspacho != null) {
            if (tipoDspacho.getNumero() == 2) {

                switch (direccion.getB().getP().getCodigo()) {
                    case 1:
                        return 1700;

                    case 2:
                        return 1200;

                    case 3:
                        return 1300;
                    case 4:
                        return 1850;
                    case 5:
                        return 3000;
                    case 6:
                    case 7:
                        return 2100;

                }
            }
        }
        return 0;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
