package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import app.model.Cliente;
import app.model.Endereco;
import app.model.Telefone;

public class DaoCliente {

    public boolean inserir(Cliente cliente) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false); 
    
            String insertPessoa = "INSERT INTO pessoa (nome, cpf, rg) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertPessoa, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setInt(2, cliente.getCpf());
            preparedStatement.setInt(3, cliente.getRg());
            preparedStatement.executeUpdate();
    
            resultSet = preparedStatement.getGeneratedKeys();
            int idPessoa = 0;
            if (resultSet.next()) {
                idPessoa = resultSet.getInt(1);
            }
    
            String insertEndereco = "INSERT INTO endereco (idTipo, idCidade, logradouro, numero, bairro) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertEndereco, Statement.RETURN_GENERATED_KEYS);
    
            for (Endereco endereco : cliente.getEnderecos()) {
                preparedStatement.setInt(1, endereco.getTipoEndereco().getId());
                preparedStatement.setInt(2, endereco.getCidade().getId());
                preparedStatement.setString(3, endereco.getLogradouro());
                preparedStatement.setInt(4, endereco.getNumero());
                preparedStatement.setString(5, endereco.getBairro());
                preparedStatement.executeUpdate();

                resultSet = preparedStatement.getGeneratedKeys();
                int idEndereco = 0;
                if (resultSet.next()) {
                    idEndereco = resultSet.getInt(1);
                }
    
                String updateEndereco = "UPDATE pessoa SET idEndereco = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(updateEndereco);
                preparedStatement.setInt(1, idEndereco);
                preparedStatement.setInt(2, idPessoa);
                preparedStatement.executeUpdate();
            }
    
            String insertTelefone = "INSERT INTO telefone (numero, idTipo) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(insertTelefone, Statement.RETURN_GENERATED_KEYS);
    
            for (Telefone telefone : cliente.getTelefones()) {
                preparedStatement.setInt(1, telefone.getNumero());
                preparedStatement.setInt(2, telefone.getTipoTelefone().getId());
                preparedStatement.executeUpdate();
    
                resultSet = preparedStatement.getGeneratedKeys();
                int idTelefone = 0;
                if (resultSet.next()) {
                    idTelefone = resultSet.getInt(1);
                }
    
                String updateTelefone = "UPDATE pessoa SET idTelefone = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(updateTelefone);
                preparedStatement.setInt(1, idTelefone);
                preparedStatement.setInt(2, idPessoa);
                preparedStatement.executeUpdate();
            }
    
            String insertCliente = "INSERT INTO cliente (idPessoa) VALUES (?)";
            preparedStatement = connection.prepareStatement(insertCliente);
            preparedStatement.setInt(1, idPessoa);
            int resultado = preparedStatement.executeUpdate();
    
            connection.commit();
            
            return resultado > 0;
    
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fazer rollback: " + ex.getMessage());
            }
            return false;
        
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexões: " + e.getMessage());
            }
        }
    }


}
