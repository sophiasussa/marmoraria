package app.controller;

import java.util.List;

import app.model.TipoTelefone;
import app.repository.DaoTipoTelefone;

public class ControllerTipoTelefone {
    DaoTipoTelefone dao = new DaoTipoTelefone();

    public boolean inserir(TipoTelefone tipoTelefone){
        return dao.inserir(tipoTelefone);
    }

    public boolean alterar(TipoTelefone tipoTelefone){
        return dao.alterar(tipoTelefone);
    }

    public boolean excluir(TipoTelefone tipoTelefone){
        return dao.excluir(tipoTelefone);
    }

    public TipoTelefone pesquisar(int id){
        return dao.pesquisar(id);
    }

    public List<TipoTelefone> pesquisarTodos(){
        return dao.pesquisarTodos();
    }
}
