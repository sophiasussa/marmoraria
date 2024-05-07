package app.model;

public class Telefone {
    private int id;
    private int numero;
    private TipoTelefone tipoTelefone;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getNumero(){
        return numero;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }
}
