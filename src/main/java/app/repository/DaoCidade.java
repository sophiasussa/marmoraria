package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.model.Cidade;
import app.model.TipoEndereco;

public class DaoCidade {

    public boolean inserir(Cidade cidade){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String insert = "INSERT INTO cidade (nome) VALUE (?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(insert);
            preparedStatement1.setString(1, cidade.getNome());

            int resultado = preparedStatement1.executeUpdate();
            if(resultado > 0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

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
    }

}
