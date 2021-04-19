
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Tipo.TipoPago;
import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class Pedido {
    
    private int id;
    private Cliente cliente;
    private Date FechaEntrega;
    private Time horaSolicitada;
    private float costoEnvio;
    private TipoPago tipoPago;
    private float Descuento;
    private String tipoDspacho;
    private float subTotal;
    private float total;
    private LinkedList<TransaccionProducto> productos=new LinkedList<>();
    private String Estado;

    public Pedido() {
        
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

    public Date getFechaSolicitada() {
        return FechaEntrega;
    }

    public void setFechaSolicitada(Date fechaSolicitada) {
        this.FechaEntrega = fechaSolicitada;
    }

    public Date getFechaEntrega() {
        return FechaEntrega;
    }

    public void setFechaEntrega(Date FechaEntrega) {
        this.FechaEntrega = FechaEntrega;
    }

    public Time getHoraSolicitada() {
        return horaSolicitada;
    }

    public void setHoraSolicitada(Time horaSolicitada) {
        this.horaSolicitada = horaSolicitada;
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

    public String getTipoDspacho() {
        return tipoDspacho;
    }

    public void setTipoDspacho(String tipoDspacho) {
        this.tipoDspacho = tipoDspacho;
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

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    
    
    
    
    
    
}
