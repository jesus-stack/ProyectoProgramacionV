
package Model.Tipo;


public enum TipoDespacho {
    
 EnvioDirecto(1,"Envio directo por personal de la empresa"),   
 EntregaSinEnvio(2,"Entrega sin envio"),
 EntregaPorUnTercero(3,"Envio por encomienda un tercero");

    
    
    
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
