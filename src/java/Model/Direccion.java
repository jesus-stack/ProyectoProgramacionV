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
    private Provincia p;
    private Canton c;
    private Distrito d;
    private Barrio b;
    private String sennas;
    private Cliente cliente;
    private Dia diaInicio;
    private Dia diaFinal;
    private Time horaInicio,horaFinal;

    public Direccion(int id, Provincia p, Canton c, Distrito d, Barrio b, Cliente cliente, String sennas) {
        this.id = id;
        this.p = p;
        this.c = c;
        this.d = d;
        this.b = b;
        this.cliente = cliente;
        this.sennas=sennas;
    }

    public Direccion() {
     this.id = 0;
        this.p = null;
        this.c = null;
        this.d = null;
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

    public Dia getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(Dia diaInicio) {
        this.diaInicio = diaInicio;
    }

    public Dia getDiaFinal() {
        return diaFinal;
    }

    public void setDiaFinal(Dia diaFinal) {
        this.diaFinal = diaFinal;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }
 
    

    
    
    
}
