/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Conexion.SNMPExceptions;
import DAO.DireccionDLL;
import Model.Barrio;
import Model.Canton;
import Model.Distrito;
import Model.Provincia;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;

/**
 *
 * @author Jesus
 */
@Named(value = "beanDireccion")
@SessionScoped
public class beanDireccion implements Serializable {

    LinkedList<SelectItem> provincias,cantones,distritos,barrios;
    int provincia,canton,distrito,barrio;
    public beanDireccion() {
        provincias=new LinkedList<>();
        cantones=new LinkedList<>();
    }

    public LinkedList<SelectItem> getProvincias() throws SNMPExceptions {
        llenarprovincias();
        llenarBarrios();
        return provincias;
    }

    public void setProvincias(LinkedList<SelectItem> provincias) {
        this.provincias = provincias;
    }

    public LinkedList<SelectItem> getCantones() throws SNMPExceptions {

        return cantones;
    }

    public void setCantones(LinkedList<SelectItem> cantones) {
        this.cantones = cantones;
    }

    public LinkedList<SelectItem> getDistritos() {
        return distritos;
    }

    public void setDistritos(LinkedList<SelectItem> distritos) {
        this.distritos = distritos;
    }

    public LinkedList<SelectItem> getBarrios() {
        
        return barrios;
    }

    public void setBarrios(LinkedList<SelectItem> barrios) {
        this.barrios = barrios;
    }
    
    

    public int getProvincia() {
        return provincia;
    }

    public void setProvincia(int provincia) {
        this.provincia = provincia;
    }

    public int getCanton() {
        
        return canton;
    }

    public void setCanton(int canton) {
        this.canton = canton;
    }

    public int getDistrito() {
        return distrito;
    }

    public void setDistrito(int distrito) {
        this.distrito = distrito;
    }

    public int getBarrio() {
        return barrio;
    }

    public void setBarrio(int barrio) {
        this.barrio = barrio;
    }
    
    
    
    public void llenarprovincias() throws SNMPExceptions{
        LinkedList<Provincia> tempprovincias=DireccionDLL.Provincias();
        LinkedList<SelectItem> lista=new LinkedList<>();
        
        for (int i = 0; i < tempprovincias.size(); i++) {
            
            lista.add(new SelectItem(tempprovincias.get(i).getCodigo(), tempprovincias.get(i).getNombre()+""));
        }
        provincias= lista;
    }
      public void llenarCantones() throws SNMPExceptions{
          LinkedList<Canton> tempCantones;
        
       
      
        tempCantones=DireccionDLL.cantones(provincia);
       
        LinkedList<SelectItem> lista=new LinkedList<>();
        
        for (int i = 0; i < tempCantones.size(); i++) {
            
            lista.add(new SelectItem(tempCantones.get(i).getCodigo(), tempCantones.get(i).getNombre()));
        }
        cantones= lista;
    }
    
        public void llenarDistritos() throws SNMPExceptions{
          LinkedList<Distrito> tempDistritos;
      
          
        tempDistritos=DireccionDLL.Distritos(provincia,canton);
        
     
          
        LinkedList<SelectItem> lista=new LinkedList<>();
        
        for (int i = 0; i < tempDistritos.size(); i++) {
            
            lista.add(new SelectItem(tempDistritos.get(i).getCodigo(), tempDistritos.get(i).getNombre()));
        }
        distritos= lista;
    }
        
            public void llenarBarrios() throws SNMPExceptions{
          LinkedList<Barrio> tempBarrios;
        
        
        tempBarrios=DireccionDLL.Barrios(provincia,canton,distrito);
      
        LinkedList<SelectItem> lista=new LinkedList<>();
        
        for (int i = 0; i < tempBarrios.size(); i++) {
            
            lista.add(new SelectItem(tempBarrios.get(i).getCodigo(), tempBarrios.get(i).getNombre()));
        }
        barrios= lista;
    }
            public void InsertarProvincia() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
                DireccionDLL.InsertarDireccion(provincia, canton, distrito, barrio, "funciona");
                cantones=new LinkedList<>();
                distritos=new LinkedList<>();
                barrios=new LinkedList<>();
            }
}

