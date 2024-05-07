package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.model.TipoTelefone;

public class DaoTipoTelefone {

    public boolean inserir(TipoTelefone tipoTelefone) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String insert = "INSERT INTO tipoTelefone (nome) values" + "(?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(insert);
            preparedStatement1.setString(1, tipoTelefone.getNome());
            int resultado = preparedStatement1.executeUpdate();
            if (resultado > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<TipoTelefone> pesquisarTodos() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String consulta = "SELECT * from tipoTelefone";
            List<TipoTelefone> lista = new ArrayList<TipoTelefone>();
            TipoTelefone tipoTelefone;
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tipoTelefone = new TipoTelefone();
                tipoTelefone.setId(resultSet.getInt("id"));
                tipoTelefone.setNome(resultSet.getString("nome"));
                lista.add(tipoTelefone);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
}
