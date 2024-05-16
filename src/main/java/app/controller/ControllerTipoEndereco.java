package app.controller;

import java.util.List;

import app.model.TipoEndereco;
import app.repository.DaoTipoEndereco;

public class ControllerTipoEndereco {

    DaoTipoEndereco dao = new DaoTipoEndereco();

    public boolean inserir(TipoEndereco tipoEndereco){
        return dao.inserir(tipoEndereco);
    }

    public List<TipoEndereco> pesquisarTodos(){
        return dao.pesquisarTodos();
    }

}
