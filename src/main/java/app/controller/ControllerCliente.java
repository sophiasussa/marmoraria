package app.controller;

import java.util.List;

import app.model.Cliente;
import app.repository.DaoCliente;

public class ControllerCliente {
    DaoCliente dao = new DaoCliente();

    public boolean inserir(Cliente cliente){
        return dao.inserir(cliente);
    }
 
    public boolean visualizar(Cliente cliente){
        return dao.inserir(cliente);
    }

    public List<Cliente> listarTodos(){
        return dao.listarTodos();
    }
}
