/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Jesus
 */
public class Barrio {
   private int codigo;
   private Provincia p;
   private Canton c;
   private Distrito d;
   private String nombre;

    public Barrio(int codigo, Provincia p, Canton c, Distrito d, String nombre) {
        this.codigo = codigo;
        this.p = p;
        this.c = c;
        this.d = d;
        this.nombre = nombre;
 
    }
    public Barrio(){
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

    public Distrito getD() {
        return d;
    }

    public void setD(Distrito d) {
        this.d = d;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  

















}
