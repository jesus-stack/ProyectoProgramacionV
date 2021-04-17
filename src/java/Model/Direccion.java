/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Time;

/**
 *
 * @author Usuario
 */
public class Direccion {
    private int id;
    private Barrio b;
    private String sennas;
    private Cliente cliente;

    public Direccion(int id,  Barrio b, Cliente cliente, String sennas) {
        this.id = id;
     
        this.b = b;
        this.cliente = cliente;
        this.sennas=sennas;
    }

    public Direccion() {
     this.id = 0;
    
        this.b = null;
        this.cliente = null;
        this.sennas="";
    }

    public String getSennas() {
        return sennas;
    }

    public void setSennas(String sennas) {
        this.sennas = sennas;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

      public Barrio getB() {
        return b;
    }

    public void setB(Barrio b) {
        this.b = b;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    

    
    
    
}
