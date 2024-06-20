package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.model.TipoEndereco;
import app.model.TipoTelefone;

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

    public boolean alterar(TipoEndereco tipoEndereco){
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            String update = "UPDATE tipoEndereco set nome = ? where id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(update);
            preparedStatement1.setInt(2, tipoEndereco.getId());
            preparedStatement1.setString(1, tipoEndereco.getNome());
            int resultado = preparedStatement1.executeUpdate();
            if(resultado > 0){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }

    public boolean excluir(TipoEndereco tipoEndereco){
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            String delete = "DELETE from tipoEndereco where id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(delete);
            preparedStatement1.setInt(1, tipoEndereco.getId());
            int resultado = preparedStatement1.executeUpdate();
            if(resultado > 0){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
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
