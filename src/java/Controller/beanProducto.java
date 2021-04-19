/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Conexion.SNMPExceptions;
import DAO.ProductoDB;
import Model.Producto;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Jesus
 */
@Named(value = "beanProducto")
@SessionScoped
public class beanProducto implements Serializable {
private Producto producto=new Producto();

private LinkedList<Producto> lista;

private UploadedFile imagen;

  

    


    public beanProducto() {
    }

    public UploadedFile getImagen() {
        return imagen;
    }

    public void setImagen(UploadedFile imagen) {
        this.imagen = imagen;
    }
   

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    

 

    public LinkedList<Producto> getLista() throws SNMPExceptions {

     return ProductoDB.listaProductos();
     
    }

    public void setLista(LinkedList<Producto> lista) {
        this.lista = lista;
    }
    
    public void AgregarProducto() throws IOException, SNMPExceptions{
        try{
          producto.setFoto(imagen.getFileName());
  ProductoDB.AgregarProducto(producto);
      FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_INFO,"EXITO", "\nRegistro almacenado Correctamente ");
          FacesContext.getCurrentInstance().addMessage("msg", message);
          producto=new Producto();
          
      } catch(Exception e){
          FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR","Ha ocurrido un problema, intentelo mas tarde" );
          FacesContext.getCurrentInstance().addMessage("msg", message);
      }  
    }

       public void EliminarProducto(Producto pro) throws IOException, SNMPExceptions{
        try{
            
  ProductoDB.EliminarProducto(pro);
      FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_INFO,"EXITO", "\nProducto Eliminado ");
          FacesContext.getCurrentInstance().addMessage("msg", message);
          producto=new Producto();
          
      } catch(Exception e){
          FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR","Ha ocurrido un problema, intentelo mas tarde" );
          FacesContext.getCurrentInstance().addMessage("msg", message);
      }  
    }
       public void nuevo(){
           this.setProducto(new Producto());
       }
       public LinkedList<SelectItem> listacmbProductos() throws SNMPExceptions{
    LinkedList<Producto> listap=this.getLista();
    LinkedList<SelectItem> productos=new LinkedList<>();
           for (int i = 0; i < listap.size(); i++) {
               productos.add(new SelectItem(listap.get(i), listap.get(i).getNombre()));
           }
           return productos;
}
 
 public void editarProducto(Producto producto){
     this.setProducto(producto);
 }

}
