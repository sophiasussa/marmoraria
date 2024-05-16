package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.model.TipoEndereco;

public class DaoTipoEndereco {

    public boolean inserir(TipoEndereco tipoEndereco){
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            String insert = "INSERT INTO tipoEndereco (nome) VALUES (?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(insert);
            preparedStatement1.setString(1, tipoEndereco.getNome());
            int resultado = preparedStatement1.executeUpdate();
            if(resultado > 0){
                return true;
            }else{
                return false;
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    } 

    public List<TipoEndereco> pesquisarTodos() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String consulta = "SELECT * from tipoEndereco";
            List<TipoEndereco> lista = new ArrayList<TipoEndereco>();
            TipoEndereco tipoEndereco;
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tipoEndereco = new TipoEndereco();
                tipoEndereco.setId(resultSet.getInt("id"));
                tipoEndereco.setNome(resultSet.getString("nome"));
                lista.add(tipoEndereco);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

}
