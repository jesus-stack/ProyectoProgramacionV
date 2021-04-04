/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pedido;
import Model.Producto;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
@Named(value = "beanTransaccion")
@SessionScoped
public class beanTransaccion implements Serializable {

 Pedido pedido=new Pedido();

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
 
    
    public beanTransaccion() {
    
    }
    
    public int cantidadProducto(){
     int cantidad;
       
    
    return cantidad=pedido.getProductos().size();
    
    }
    
    
    
    
}
