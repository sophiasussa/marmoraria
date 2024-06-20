package app.controller;

import java.util.List;

import app.model.Cidade;
import app.repository.DaoCidade;

public class ControllerCidade {
    DaoCidade dao = new DaoCidade();

    public boolean inserir(Cidade cidade){
        return dao.inserir(cidade);
    }

    public boolean alterar(Cidade cidade){
        return dao.alterar(cidade);
    }

    public boolean excluir(Cidade cidade){
        return dao.excluir(cidade);
    }

    public List<Cidade> pesquisarTodos(){
        return dao.pesquisarTodos();
    }
}
