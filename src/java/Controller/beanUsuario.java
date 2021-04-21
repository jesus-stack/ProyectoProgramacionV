/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDLL;
import Model.Cliente;
import Model.Logica;
import Model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jesus
 */
@Named(value = "beanUsuario")
@SessionScoped
public class beanUsuario implements Serializable {

    private LinkedList<Cliente> clientes;
    private String mensajerechazo;
    
    public beanUsuario() {
    }

    public LinkedList<Cliente> getClientes() {
        llenarLista();
        return clientes;
    }

    public void setClientes(LinkedList<Cliente> usuarios) {
        this.clientes = usuarios;
        
        
    }

    public String getMensajerechazo() {
        return mensajerechazo;
    }

    public void setMensajerechazo(String mensajerechazo) {
        this.mensajerechazo = mensajerechazo;
    }
    public void llenarLista(){
        try{
            clientes=UsuarioDLL.peticionesClientes();
        }catch(Exception e){
            FacesMessage m=new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, m);
        }
    }
    public void aceptarCliente(Cliente c){
        try{
        Logica.aceptar(c);
         FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito","Registro Guardado Correctamente"));
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ha ocurrido un error","Intentelo ms tarde"));
        }
    }
      public void rechazarCliente(Cliente c){
        try{
        Logica.rechazar(mensajerechazo, c);
        mensajerechazo="";
         FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito","Registro Guardado Correctamente"));
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ha ocurrido un error","Intentelo ms tarde"));
        }
    }
        
    
}
