package app.controller;

import app.model.Cliente;
import app.repository.DaoCliente;

public class ControllerCliente {
    DaoCliente dao = new DaoCliente();

    public boolean inserir(Cliente cliente){
        return dao.inserir(cliente);
    }
}
