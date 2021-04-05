/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Usuario
 */
public class Canton {
    
    private int codigo;
    private Provincia p;
    private String nombre;
    
    public Canton(int codigo,Provincia p, String nombnre){
    this.codigo=codigo;
    this.p=p;
    this.nombre=nombre;
    }
    
    public Canton(){
     this.codigo=0;
    this.p=null;
    this.nombre="";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Provincia getP() {
        return p;
    }

    public void setP(Provincia p) {
        this.p = p;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
    
    
    
    
    
    
    
}
