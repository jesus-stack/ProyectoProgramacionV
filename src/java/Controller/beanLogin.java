/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Conexion.AccesoDatos;
import DAO.Conexion.SNMPExceptions;
import DAO.UsuarioDLL;
import Model.Cliente;
import Model.Funcionario;
import Model.Tipo.TipoUsuario;
import Model.Usuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jesus
 */
@Named(value = "beanLogin")
@SessionScoped
public class beanLogin implements Serializable {
private  Usuario usuario=new Usuario();
private boolean mantenimiento,facturacion,pedidos,reportes,despachar;
private String nombre;


    public beanLogin() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   

    public boolean isMantenimiento() {
          boolean resultado=false;
        if(usuario.getTipo()!=null){
           if(usuario.getTipo().getId()==1||usuario.getTipo().getId()==4){
               resultado=true;
           }
        }
            
        
        return resultado;
    }

    public void setMantenimiento(boolean mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public boolean isDespachar() {
       boolean resultado=false;
        if(usuario.getTipo()!=null){
          if(usuario.getTipo().getId()==1||usuario.getTipo().getId()==4){
               resultado=true;
           }
        }
            
        
        return resultado;
    }

    public void setDespachar(boolean despachar) {
        this.despachar = despachar;
    }

    public boolean isFacturacion() {
   
        boolean resultado=false;
        if(usuario.getTipo()!=null){
           if(usuario.getTipo().getId()==1||usuario.getTipo().getId()==3){
               resultado=true;
           }
        }
            
        
        return resultado;
    }

    public void setFacturacion(boolean facturacion) {
        this.facturacion = facturacion;
    }

    public boolean isPedidos() {
      boolean resultado=false;
        if(usuario.getTipo()!=null){
         if(usuario.getTipo().getId()==2){
               resultado=true;}
           
        }
            
        
        return resultado;
    }

    public void setPedidos(boolean pedidos) {
        this.pedidos = pedidos;
    }

    public boolean isReportes() {
          boolean resultado=false;
        if(usuario.getTipo()!=null){
           if(usuario.getTipo().getId()==1){
               resultado=true;
           }
        }
            
        
        return resultado;
    }

    public void setReportes(boolean reportes) {
        this.reportes = reportes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    public void validarUsuario() throws SNMPExceptions, IOException{
       
       
        FacesMessage mensajeM=new FacesMessage(FacesMessage.SEVERITY_ERROR,"!DATOS INVALIDOS","!DATOS INVALIDOS");
        FacesMessage mensajeB=new FacesMessage(FacesMessage.SEVERITY_INFO,"!DATOS VALIDOS","!DATOS VALIDOS");
        FacesContext context=FacesContext.getCurrentInstance();
     
        try {
             Usuario usuario1=UsuarioDLL.UsuarioXidentificacion(this.getUsuario().getId());
           if(usuario1.getTipo().getId()==2){
               Cliente c=(Cliente) usuario1;
               this.setNombre(c.getNombre()+"\n"+c.getApellido());
           }
           else{
                Funcionario c=(Funcionario) usuario1;
               this.setNombre(c.getNombre()+"\n"+c.getApellido());
           }
            if(usuario1.isEstado()==true&&usuario1.getContrasenna().equals(this.getUsuario().getContrasenna())){
                this.setUsuario(usuario1);
              
               context.addMessage("msg", mensajeB);
                FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR","Ha ocurrido un problema, intentelo mas tarde" );
          FacesContext.getCurrentInstance().addMessage("msg", message);
                 context.getExternalContext().getSessionMap().put("user", this.getUsuario());
             
              
            }
            else{
                  this.setUsuario(new Usuario());
                context.addMessage("msg", mensajeM);
            }
            
        } catch (Exception e) {
            context.addMessage("msg", mensajeM);
        }
        finally{
          
        context.getExternalContext().redirect("index.xhtml");
            
        }
  }
    
   public void cerrarSesion() throws IOException{
      FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
       FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
   }
   public boolean mostrarCarrito(){
       boolean res=false;
       if(usuario.getTipo()!=null){
       res=usuario.getTipo().getId()==2;
   }
       return res;
   }
   
  
}


