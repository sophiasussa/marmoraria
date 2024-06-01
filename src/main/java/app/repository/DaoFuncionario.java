package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.model.Funcionario;

public class DaoFuncionario {
/* 
    public List<Cidade> pesquisarTodos() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String consulta = "SELECT * from cidade";
            List<Cidade> lista = new ArrayList<Cidade>();
            Cidade cidade;
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cidade = new Cidade();
                cidade.setId(resultSet.getInt("id"));
                cidade.setNome(resultSet.getString("nome"));
                lista.add(cidade);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }*/
}
