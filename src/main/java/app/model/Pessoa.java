package app.model;

import java.util.List;

public class Pessoa {
    private int id;
    private int cpf;
    private int rg;
    private List<Telefone> telefones;
    private List<Endereco> enderecos;
    
    public Pessoa(int id, int cpf, int rg, List<Telefone> telefones, List<Endereco> enderecos) {
        this.id = id;
        this.cpf = cpf;
        this.rg = rg;
        this.telefones = telefones;
        this.enderecos = enderecos;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCpf() {
        return cpf;
    }
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
    public int getRg() {
        return rg;
    }
    public void setRg(int rg) {
        this.rg = rg;
    }
    public List<Telefone> getTelefones() {
        return telefones;
    }
    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }
    public List<Endereco> getEnderecos() {
        return enderecos;
    }
    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
