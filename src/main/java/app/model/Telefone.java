package app.model;

public class Telefone {
    private int id;
    private long numero;
    private TipoTelefone tipoTelefone;

    public Telefone(int numero, TipoTelefone tipoTelefone) {
        this.numero = numero;
        this.tipoTelefone = tipoTelefone;
    }

    public Telefone() {
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public long getNumero(){
        return numero;
    }

    public void setNumero(long numero){
        this.numero = numero;
    }
    
    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }
}
