package app.controller;

import java.util.List;

import app.model.TipoTelefone;
import app.repository.DaoTipoTelefone;

public class ControllerTipoTelefone {
    DaoTipoTelefone dao = new DaoTipoTelefone();

    public boolean inserir(TipoTelefone tipoTelefone){
        return dao.inserir(tipoTelefone);
    }

    public List<TipoTelefone> pesquisarTodos(){
        return dao.pesquisarTodos();
    }
}
