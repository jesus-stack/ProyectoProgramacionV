/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Conexion.SNMPExceptions;
import DAO.TransaccionDB;
import Model.Pedido;
import Model.Producto;
import Model.TransaccionProducto;
import Model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

/**
 *
 * @author Usuario
 */
@Named(value = "beanTransaccion")
@SessionScoped
public class beanTransaccion implements Serializable {

 Pedido pedido=new Pedido();
    TransaccionProducto transaccionProducto;

    public TransaccionProducto getTransaccionProducto() {
        return transaccionProducto;
    }

    public void setTransaccionProducto(TransaccionProducto transaccionProducto) {
        this.transaccionProducto = transaccionProducto;
    }
    

    
 

    public Pedido getPedido() {
        return pedido
                ;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
 
    
    public beanTransaccion() {
    transaccionProducto=new TransaccionProducto();
    }
    
    public int cantidadProducto() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
         ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
        Map<String,Object> sesion=context.getSessionMap();
        Usuario user=(Usuario) sesion.get("user");
      if(user!=null){
        pedido =TransaccionDB.seleccionarTransaccion(user.getId(), 1);
      }
     int cantidad=0;
       
    if(pedido.getProductos()!=null){
     cantidad=pedido.getProductos().size();
    }
    return cantidad;
    
    }
    
    public void AgregarProducto() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
        Map<String,Object> sesion=context.getSessionMap();
        Usuario user=(Usuario) sesion.get("user");
      
        
        if(pedido.getProductos().isEmpty()){
            TransaccionDB.crearTransaccion(user.getId());
            pedido=TransaccionDB.seleccionarTransaccion(user.getId(), 1);
        }
        TransaccionDB.AgregarProducto(user.getId(),transaccionProducto.getProducto().getId(),transaccionProducto.getCantidad());
        transaccionProducto.setProducto(null);
        transaccionProducto.setCantidad(0);
        
    }
    
    
}
