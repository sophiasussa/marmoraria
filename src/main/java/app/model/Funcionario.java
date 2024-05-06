package app.model;

import java.util.List;

public class Funcionario extends Pessoa{
    private int idFuncionario;
    private int dataAdm;
    private float salario;
    private Cargo cargo;
    private Departamento departamento;

    public Funcionario(int id, int cpf, int rg, List<Telefone> telefones, List<Endereco> enderecos, int idFuncionario, int dataAdm, float salario, Cargo cargo, Departamento departamento) {
        super(id, cpf, rg, telefones, enderecos);
        this.idFuncionario = idFuncionario;
        this.dataAdm = dataAdm;
        this.salario = salario;
        this.cargo = cargo;
        this.departamento = departamento;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getDataAdm() {
        return dataAdm;
    }

    public void setDataAdm(int dataAdm) {
        this.dataAdm = dataAdm;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
