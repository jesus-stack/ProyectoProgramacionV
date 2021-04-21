/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Jesus
 */


public class Reporte {
    private int pedidoPendiente,pedidoFacturado,pedidoDespachado,total;
    float ventaEfectivo,ventaCredito;

    public Reporte() {
    }

    public int getPedidoPendiente() {
        return pedidoPendiente;
    }

    public void setPedidoPendiente(int pedidoPendiente) {
        this.pedidoPendiente = pedidoPendiente;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPedidoFacturado() {
        return pedidoFacturado;
    }

    public void setPedidoFacturado(int pedidoFacturado) {
        this.pedidoFacturado = pedidoFacturado;
    }

    public int getPedidoDespachado() {
        return pedidoDespachado;
    }

    public void setPedidoDespachado(int pedidoDespachado) {
        this.pedidoDespachado = pedidoDespachado;
    }

    public float getVentaEfectivo() {
        return ventaEfectivo;
    }

    public void setVentaEfectivo(float ventaEfectivo) {
        this.ventaEfectivo = ventaEfectivo;
    }

    public float getVentaCredito() {
        return ventaCredito;
    }

    public void setVentaCredito(float ventaCredito) {
        this.ventaCredito = ventaCredito;
    }
    
    
    
}
