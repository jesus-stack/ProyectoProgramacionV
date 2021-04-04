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
public class TransaccionProducto {
private  Producto producto;
  private int Cantidad;

    public TransaccionProducto() {
    }

    public TransaccionProducto(Producto producto, int Cantidad) {
        this.producto = producto;
        this.Cantidad = Cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }
  
}
