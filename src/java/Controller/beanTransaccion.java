/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Conexion.SNMPExceptions;
import DAO.DireccionDLL;
import DAO.TransaccionDB;
import Model.Cliente;
import Model.Direccion;
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
import javax.faces.model.SelectItem;
import javax.naming.NamingException;

/**
 *
 * @author Usuario
 */
@Named(value = "beanTransaccion")
@SessionScoped
public class beanTransaccion implements Serializable {

 Pedido pedido=new Pedido();
  

  

    
 

    public Pedido getPedido() throws SNMPExceptions {
      //  cargarDirecciones();
        return pedido;
                
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
 
    
    public beanTransaccion() {
  
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
    
    public void AgregarProducto(Producto pro) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
        Map<String,Object> sesion=context.getSessionMap();
        Usuario user=(Usuario) sesion.get("user");
      int cantidad=pro.getCantidadMinimaVenta();
        
        if(pedido.getProductos().isEmpty()){
            TransaccionDB.crearTransaccion(user.getId());
         
            pedido=TransaccionDB.seleccionarTransaccion(user.getId(), 1);
        }
        TransaccionDB.AgregarProducto(user.getId(),pro.getId(),cantidad);
        
        
    }
    
    
    
    public LinkedList<Direccion> ListaDirecciones() throws SNMPExceptions{     
    return DireccionDLL.listaTodasDireccionCliente(pedido.getCliente().getId());
   
    }
     public LinkedList<SelectItem> cmbdirecciones() throws SNMPExceptions{    
         cargarDirecciones();
    LinkedList<Direccion> direc=pedido.getCliente().getDireccione();
    LinkedList<SelectItem> lista=new LinkedList<>();
         for (int i = 0; i < direc.size(); i++) {
             Direccion d=direc.get(i);
             lista.add(new SelectItem(d.getId(),d.getB().getP().getNombre()+
                     "' "+d.getB().getC().getNombre()+
                     ", "+d.getB().getD().getNombre()+
                             ", "+d.getB().getNombre()+
                     ", "+d.getSennas()));
         }
   return lista;
    }
    
    public void EliminarProducto(Producto pro) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        TransaccionDB.EliminarProducto(pedido.getId(), pro.getId());
    }
       public void cargarDirecciones() throws SNMPExceptions{
           ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
        Map<String,Object> sesion=context.getSessionMap();
        Usuario user=(Usuario) sesion.get("user");
        pedido.setCliente((Cliente) user);
        pedido.getCliente().setDireccione(DireccionDLL.listaTodasDireccionCliente(user.getId()));
          
        }
}
