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
public class Distrito {
    private int codigo;
    private Provincia p;
    private Canton c;
    private String nombre;
    
    
    public Distrito(int distrito,Provincia p,Canton c,String nombre){
    this.codigo=distrito;
    this.p=p;
    this.c=c;
    this.nombre=nombre;
    
    }
    public Distrito(){
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

    public Canton getC() {
        return c;
    }

    public void setC(Canton c) {
        this.c = c;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
    
    
    
}
