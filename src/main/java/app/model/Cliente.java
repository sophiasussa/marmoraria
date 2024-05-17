package app.model;

import java.util.List;

public class Cliente extends Pessoa{
    private int idCliente;

    public Cliente(int id, String nome, int cpf, int rg, List<Telefone> telefones, List<Endereco> enderecos,
        int idCliente) {
        super(id, nome, cpf, rg, telefones, enderecos);
        this.idCliente = idCliente;
    }

    public Cliente(){
        
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
