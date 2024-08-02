package app.model;

import java.util.List;

public class Fornecedor extends Pessoa{
    private int idFornecedor;
    private String descriProdu;


    public Fornecedor(int id, String nome, int cpf, int rg, List<Telefone> telefones, List<Endereco> enderecos,
            int idFornecedor, String descriProdu) {
        super(id, nome, cpf, rg, telefones, enderecos);
        this.idFornecedor = idFornecedor;
        this.descriProdu = descriProdu;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getDescriProdu() {
        return descriProdu;
    }

    public void setDescriProdu(String descriProdu) {
        this.descriProdu = descriProdu;
    }
}
