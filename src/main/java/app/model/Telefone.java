package app.model;

public class Telefone {
    private int id;
    private int numero;
    private TipoEndereco tipoEndereco;


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getNumero(){
        return id;
    }

    public void setNumero(int id){
        this.numero = numero;
    }

    public TipoEndereco getTipoEndereco(){
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco){
        this.tipoEndereco = tipoEndereco;
    }
}
