package app.model;

import java.util.List;

public class Pessoa {
    private int id;
    private String nome;
    private long cpf;
    private long rg;
    private List<Telefone> telefones;
    private List<Endereco> enderecos;
    
    public Pessoa(int id, String nome, long cpf, long rg, List<Telefone> telefones, List<Endereco> enderecos) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.telefones = telefones;
        this.enderecos = enderecos;
    }

    public Pessoa() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
    public long getRg() {
        return rg;
    }
    public void setRg(long rg) {
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
