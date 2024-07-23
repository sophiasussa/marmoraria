package app.controller;

import java.util.List;

import app.model.Cliente;
import app.repository.DaoCliente;

public class ControllerCliente {
    DaoCliente dao = new DaoCliente();

    public boolean inserir(Cliente cliente){
        return dao.inserir(cliente);
    }

    public boolean excluirCliente(int id) {
        return dao.excluirCliente(id);
    }

    public boolean atualizarCliente(Cliente cliente){
        return dao.atualizarCliente(cliente);
    }

    public int encontrarIdClientePorNome(String nomeCliente){
        return dao.encontrarIdClientePorNome(nomeCliente);
    } 
 
    public Cliente visualizar(String nomePessoa){
        return dao.visualizar(nomePessoa);
    }
    
    public Cliente visualizarcpf(String numero){
        return dao.visualizarcpf(numero);
    }

    public List<Cliente> listarTodos(){
        return dao.listarTodos();
    }
}
