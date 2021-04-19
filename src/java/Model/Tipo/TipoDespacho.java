
package Model.Tipo;


public enum TipoDespacho {
    
 EntregaSinEnvio(1,"Entrega sin envio"),
 EntregaPorUnTercero(2,"Envio por un tercero");
    
    
    
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
