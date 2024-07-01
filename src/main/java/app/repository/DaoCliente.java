package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.model.Cidade;
import app.model.Cliente;
import app.model.Endereco;
import app.model.Telefone;
import app.model.TipoEndereco;
import app.model.TipoTelefone;

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
            preparedStatement.setLong(2, cliente.getCpf());
            preparedStatement.setLong(3, cliente.getRg());
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
                preparedStatement.setLong(1, telefone.getNumero());
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
 
    public Cliente visualizar(int idCliente) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Cliente cliente = null;

        try {
            connection = DBConnection.getInstance().getConnection();

            String selectCliente = "SELECT p.nome, p.cpf, p.rg, " +
                                "t.numero AS telefone_numero, tt.nome AS telefone_tipo, " +
                                "e.logradouro, e.numero AS endereco_numero, e.bairro, " +
                                "c.nome AS cidade_nome, te.nome AS endereco_tipo " +
                                "FROM cliente c " +
                                "JOIN pessoa p ON c.idPessoa = p.id " +
                                "LEFT JOIN telefone t ON p.idTelefone = t.id " +
                                "LEFT JOIN tipo_telefone tt ON t.idTipo = tt.id " +
                                "LEFT JOIN endereco e ON p.idEndereco = e.id " +
                                "LEFT JOIN cidade c ON e.idCidade = c.id " +
                                "LEFT JOIN tipo_endereco te ON e.idTipo = te.id " +
                                "WHERE c.id = ?";
            preparedStatement = connection.prepareStatement(selectCliente);
            preparedStatement.setInt(1, idCliente);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setNome(resultSet.getString("nome"));
                cliente.setCpf(resultSet.getLong("cpf"));
                cliente.setRg(resultSet.getLong("rg"));

                Telefone telefone = new Telefone();
                telefone.setNumero(resultSet.getLong("telefone_numero"));
                TipoTelefone tipoTelefone = new TipoTelefone();
                tipoTelefone.setNome(resultSet.getString("telefone_tipo"));
                telefone.setTipoTelefone(tipoTelefone);

                cliente.setTelefones(Arrays.asList(telefone));

                Endereco endereco = new Endereco();
                endereco.setLogradouro(resultSet.getString("logradouro"));
                endereco.setNumero(resultSet.getInt("endereco_numero"));
                endereco.setBairro(resultSet.getString("bairro"));

                Cidade cidade = new Cidade();
                cidade.setNome(resultSet.getString("cidade_nome"));
                endereco.setCidade(cidade);

                TipoEndereco tipoEndereco = new TipoEndereco();
                tipoEndereco.setNome(resultSet.getString("endereco_tipo"));
                endereco.setTipoEndereco(tipoEndereco);

                cliente.setEnderecos(Arrays.asList(endereco));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao visualizar cliente: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexões: " + e.getMessage());
            }
        }

        return cliente;
    }

    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getInstance().getConnection();

            String selectClientes = "SELECT p.nome, p.cpf, p.rg, " +
                                    "t.numero AS telefone_numero, tt.nome AS telefone_tipo, " +
                                    "e.logradouro, e.numero AS endereco_numero, e.bairro, " +
                                    "c.nome AS cidade_nome, te.nome AS endereco_tipo " +
                                    "FROM cliente cl " +
                                    "JOIN pessoa p ON cl.idPessoa = p.id " +
                                    "LEFT JOIN telefone t ON p.idTelefone = t.id " +
                                    "LEFT JOIN tipoTelefone tt ON t.idTipo = tt.id " +
                                    "LEFT JOIN endereco e ON p.idEndereco = e.id " +
                                    "LEFT JOIN cidade c ON e.idCidade = c.id " +
                                    "LEFT JOIN tipoEndereco te ON e.idTipo = te.id";
            preparedStatement = connection.prepareStatement(selectClientes);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(resultSet.getString("nome"));
                cliente.setCpf(resultSet.getLong("cpf"));
                cliente.setRg(resultSet.getLong("rg"));

                Telefone telefone = new Telefone();
                telefone.setNumero(resultSet.getLong("telefone_numero"));
                TipoTelefone tipoTelefone = new TipoTelefone();
                tipoTelefone.setNome(resultSet.getString("telefone_tipo"));
                telefone.setTipoTelefone(tipoTelefone);

                cliente.setTelefones(Arrays.asList(telefone));

                Endereco endereco = new Endereco();
                endereco.setLogradouro(resultSet.getString("logradouro"));
                endereco.setNumero(resultSet.getInt("endereco_numero"));
                endereco.setBairro(resultSet.getString("bairro"));

                Cidade cidade = new Cidade();
                cidade.setNome(resultSet.getString("cidade_nome"));
                endereco.setCidade(cidade);

                TipoEndereco tipoEndereco = new TipoEndereco();
                tipoEndereco.setNome(resultSet.getString("endereco_tipo"));
                endereco.setTipoEndereco(tipoEndereco);

                cliente.setEnderecos(Arrays.asList(endereco));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexões: " + e.getMessage());
            }
        }

        return clientes;
    }


}
