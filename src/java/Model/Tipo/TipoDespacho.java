
package Model.Tipo;


public enum TipoDespacho {
 nada(-1,"Seleccionar Tipo de Envio"),
 EnvioDirecto(0,"Envio directo por personal de la empresa"),
 EntregaSinEnvio(1,"Entrega sin envio"),
  EnioTercero(2,"Envio por encomienda por Tercero");

    
    
    
    String des;
    int numero;
    
    private TipoDespacho(int n, String d){
    numero=n;
    this.des=d;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    
    
}
