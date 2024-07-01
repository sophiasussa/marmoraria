package app.controller;

import java.util.List;

import app.model.Cliente;
import app.repository.DaoCliente;

public class ControllerCliente {
    DaoCliente dao = new DaoCliente();

    public boolean inserir(Cliente cliente){
        return dao.inserir(cliente);
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
