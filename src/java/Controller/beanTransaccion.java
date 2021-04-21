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
import Model.Tipo.TipoDespacho;
import Model.TransaccionProducto;
import Model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    LinkedList<Pedido> pedidosDespachar;
  int tipoDespacho;
  int direccion;
      boolean consultarFactura=true,confirmarpedido=false;

    public boolean isConsultarFactura() {
        return consultarFactura;
    }

    public void setConsultarFactura(boolean consultarFactura) {
        this.consultarFactura = consultarFactura;
    }

    public boolean isConfirmarpedido() {
        return confirmarpedido;
    }

    public void setConfirmarpedido(boolean confirmarpedido) {
        this.confirmarpedido = confirmarpedido;
    }
  
public void consultar(){
    consultarFactura=false;
    confirmarpedido=true;
}
  public void confirmar(){
    consultarFactura=true;
    confirmarpedido=false;
}  
 

    public Pedido getPedido() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
 
        agregarPedidoCliente();
        agregarTipoDespacho();
        return pedido;
                
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }
    
    

    public int getTipoDespacho() {
        return tipoDespacho;
    }

    public void setTipoDespacho(int tipoDespacho) {
        this.tipoDespacho = tipoDespacho;
    }

    
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public LinkedList<Pedido> getPedidosDespachar() {
        return pedidosDespachar;
    }

    public void setPedidosDespachar(LinkedList<Pedido> pedidosDespachar) {
        this.pedidosDespachar = pedidosDespachar;
    }
 
    
    public beanTransaccion() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
  tipoDespacho=-1;
  pedidosDespachar=TransaccionDB.seleccionarPendienteDespachar();
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
    
         for (Direccion d : pedido.getCliente().getDireccione()) {
              lista.add(new SelectItem(d.getId(),d.getB().getP().getNombre()+
                     ", "+d.getB().getC().getNombre()+
                     ", "+d.getB().getD().getNombre()+
                             ", "+d.getB().getNombre()+
                     ", "+d.getSennas()));   
         } 
   return lista;
    }
     public LinkedList<SelectItem> cmbTipoEnvio(){
     LinkedList<SelectItem> lista=new LinkedList<>();

         for (TipoDespacho tipo : TipoDespacho.values()) {
             lista.add(new SelectItem(tipo.getNumero(),tipo.getDes()));
             
       
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
       
       
       
       
       public void agregarTipoDespacho(){
       
          
        switch(tipoDespacho){
            case 0:
                this.pedido.setTipoDspacho(TipoDespacho.EnvioDirecto);
                break;
            case 1:
                this.pedido.setTipoDspacho(TipoDespacho.EntregaSinEnvio);
                break;
            case 2:
                this.pedido.setTipoDspacho(TipoDespacho.EnioTercero);
                break;
        
        
        }
           
       }
       
       
       public void agregarPedidoCliente(){
     try {
         ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
         Map<String,Object> sesion=context.getSessionMap();
         Usuario user=(Usuario) sesion.get("user");
         this.setPedido(TransaccionDB.seleccionarTransaccion(user.getId(), 1));
     } catch (SNMPExceptions ex) {
         Logger.getLogger(beanTransaccion.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(beanTransaccion.class.getName()).log(Level.SEVERE, null, ex);
     } catch (NamingException ex) {
         Logger.getLogger(beanTransaccion.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(beanTransaccion.class.getName()).log(Level.SEVERE, null, ex);
     }
       
       
       }
       
       
       
       public void agregarDireccion(){
     // this.pedido.setDireccion(DireccionDLL.);
       
       }
       
       public void despachar(int id) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
           TransaccionDB.despachar(id);
           pedidosDespachar=TransaccionDB.seleccionarPendienteDespachar();
       }
       
       
}
