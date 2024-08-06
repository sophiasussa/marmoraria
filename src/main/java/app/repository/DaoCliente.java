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
        PreparedStatement preparedStatementPessoa = null;
        PreparedStatement preparedStatementEndereco = null;
        PreparedStatement preparedStatementTelefone = null;
        PreparedStatement preparedStatementUpdate = null;
        ResultSet resultSet = null;
        
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false); 
    
            String insertPessoa = "INSERT INTO pessoa (nome, cpf, rg) VALUES (?, ?, ?)";
            preparedStatementPessoa = connection.prepareStatement(insertPessoa, Statement.RETURN_GENERATED_KEYS);
            preparedStatementPessoa.setString(1, cliente.getNome());
            preparedStatementPessoa.setLong(2, cliente.getCpf());
            preparedStatementPessoa.setLong(3, cliente.getRg());
            preparedStatementPessoa.executeUpdate();
    
            resultSet = preparedStatementPessoa.getGeneratedKeys();
            int idPessoa = 0;
            if (resultSet.next()) {
                idPessoa = resultSet.getInt(1);
            }
    
            String insertEndereco = "INSERT INTO endereco (idTipo, idCidade, logradouro, numero, bairro) VALUES (?, ?, ?, ?, ?)";
            preparedStatementEndereco = connection.prepareStatement(insertEndereco, Statement.RETURN_GENERATED_KEYS);
    
            for (Endereco endereco : cliente.getEnderecos()) {
                preparedStatementEndereco.setInt(1, endereco.getTipoEndereco().getId());
                preparedStatementEndereco.setInt(2, endereco.getCidade().getId());
                preparedStatementEndereco.setString(3, endereco.getLogradouro());
                preparedStatementEndereco.setInt(4, endereco.getNumero());
                preparedStatementEndereco.setString(5, endereco.getBairro());
                preparedStatementEndereco.executeUpdate();
    
                resultSet = preparedStatementEndereco.getGeneratedKeys();
                int idEndereco = 0;
                if (resultSet.next()) {
                    idEndereco = resultSet.getInt(1);
                }
    
                String updateEndereco = "UPDATE pessoa SET idEndereco = ? WHERE id = ?";
                preparedStatementUpdate = connection.prepareStatement(updateEndereco);
                preparedStatementUpdate.setInt(1, idEndereco);
                preparedStatementUpdate.setInt(2, idPessoa);
                preparedStatementUpdate.executeUpdate();
            }
    
            String insertTelefone = "INSERT INTO telefone (numero, idTipo) VALUES (?, ?)";
            preparedStatementTelefone = connection.prepareStatement(insertTelefone, Statement.RETURN_GENERATED_KEYS);
    
            for (Telefone telefone : cliente.getTelefones()) {
                preparedStatementTelefone.setLong(1, telefone.getNumero());
                preparedStatementTelefone.setInt(2, telefone.getTipoTelefone().getId());
                preparedStatementTelefone.executeUpdate();
    
                resultSet = preparedStatementTelefone.getGeneratedKeys();
                int idTelefone = 0;
                if (resultSet.next()) {
                    idTelefone = resultSet.getInt(1);
                }
    
                String updateTelefone = "UPDATE pessoa SET idTelefone = ? WHERE id = ?";
                preparedStatementUpdate = connection.prepareStatement(updateTelefone);
                preparedStatementUpdate.setInt(1, idTelefone);
                preparedStatementUpdate.setInt(2, idPessoa);
                preparedStatementUpdate.executeUpdate();
            }
    
            String insertCliente = "INSERT INTO cliente (idPessoa) VALUES (?)";
            preparedStatementUpdate = connection.prepareStatement(insertCliente);
            preparedStatementUpdate.setInt(1, idPessoa);
            int resultado = preparedStatementUpdate.executeUpdate();
    
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
                if (preparedStatementPessoa != null) {
                    preparedStatementPessoa.close();
                }
                if (preparedStatementEndereco != null) {
                    preparedStatementEndereco.close();
                }
                if (preparedStatementTelefone != null) {
                    preparedStatementTelefone.close();
                }
                if (preparedStatementUpdate != null) {
                    preparedStatementUpdate.close();
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

    public boolean excluirCliente(int idCliente) {
        Connection connection = null;
        PreparedStatement psDeleteTelefone = null;
        PreparedStatement psDeleteEndereco = null;
        PreparedStatement psDeleteCliente = null;
        PreparedStatement psDeletePessoa = null;
        boolean sucesso = false;
    
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            String selectIdPessoa = "SELECT idPessoa FROM cliente WHERE id = ?";
            PreparedStatement psSelectIdPessoa = connection.prepareStatement(selectIdPessoa);
            psSelectIdPessoa.setInt(1, idCliente);
            ResultSet rs = psSelectIdPessoa.executeQuery();
            int idPessoa = -1;
            if (rs.next()) {
                idPessoa = rs.getInt("idPessoa");
            }
            rs.close();
            psSelectIdPessoa.close();
    
            if (idPessoa != -1) {
                String deleteCliente = "DELETE FROM cliente WHERE id = ?";
                psDeleteCliente = connection.prepareStatement(deleteCliente);
                psDeleteCliente.setInt(1, idCliente);
                psDeleteCliente.executeUpdate();

                String deleteTelefones = "DELETE FROM telefone WHERE id IN (SELECT idTelefone FROM pessoa WHERE id = ?)";
                psDeleteTelefone = connection.prepareStatement(deleteTelefones);
                psDeleteTelefone.setInt(1, idPessoa);
                psDeleteTelefone.executeUpdate();

                String deleteEnderecos = "DELETE FROM endereco WHERE id IN (SELECT idEndereco FROM pessoa WHERE id = ?)";
                psDeleteEndereco = connection.prepareStatement(deleteEnderecos);
                psDeleteEndereco.setInt(1, idPessoa);
                psDeleteEndereco.executeUpdate();

                String deletePessoa = "DELETE FROM pessoa WHERE id = ?";
                psDeletePessoa = connection.prepareStatement(deletePessoa);
                psDeletePessoa.setInt(1, idPessoa);
                psDeletePessoa.executeUpdate();
            }
    
            connection.commit();
            sucesso = true;
    
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.println("Erro ao fazer rollback: " + rollbackEx.getMessage());
                }
            }
        } finally {
            try {
                if (psDeleteTelefone != null) {
                    psDeleteTelefone.close();
                }
                if (psDeleteEndereco != null) {
                    psDeleteEndereco.close();
                }
                if (psDeleteCliente != null) {
                    psDeleteCliente.close();
                }
                if (psDeletePessoa != null) {
                    psDeletePessoa.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexões: " + e.getMessage());
            }
        }
    
        return sucesso;
    }
    

    public boolean atualizarCliente(Cliente cliente) {
        Connection connection = null;
        PreparedStatement psUpdatePessoa = null;
        PreparedStatement psUpdateTelefone = null;
        PreparedStatement psInsertTelefone = null;
        PreparedStatement psUpdateEndereco = null;
        PreparedStatement psInsertEndereco = null;
        boolean sucesso = false;
    
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
    
            String updatePessoa = "UPDATE pessoa SET nome = ?, cpf = ?, rg = ? WHERE id = ?";
            psUpdatePessoa = connection.prepareStatement(updatePessoa);
            psUpdatePessoa.setString(1, cliente.getNome());
            psUpdatePessoa.setLong(2, cliente.getCpf());
            psUpdatePessoa.setLong(3, cliente.getRg());
            psUpdatePessoa.setInt(4, cliente.getId());
            psUpdatePessoa.executeUpdate();
    
            String updateTelefone = "UPDATE telefone SET numero = ?, idTipo = ? WHERE id = ?";
            String insertTelefone = "INSERT INTO telefone (numero, idTipo) VALUES (?, ?)";
            psUpdateTelefone = connection.prepareStatement(updateTelefone);
            psInsertTelefone = connection.prepareStatement(insertTelefone, Statement.RETURN_GENERATED_KEYS);
    
            for (Telefone telefone : cliente.getTelefones()) {
                if (!verificarTipoTelefoneExiste(connection, telefone.getTipoTelefone().getId())) {
                    System.out.println("Tipo de telefone não existe: " + telefone.getTipoTelefone().getId());
                    throw new SQLException("Tipo de telefone não existe: " + telefone.getTipoTelefone().getId());
                }
    
                if (telefone.getId() != 0) {
                    psUpdateTelefone.setLong(1, telefone.getNumero());
                    psUpdateTelefone.setInt(2, telefone.getTipoTelefone().getId());
                    psUpdateTelefone.setInt(3, telefone.getId());
                    psUpdateTelefone.executeUpdate();
                } else {
                    boolean telefoneExiste = verificarTelefoneExiste(connection, telefone.getNumero());
                    if (!telefoneExiste) {
                        psInsertTelefone.setLong(1, telefone.getNumero());
                        psInsertTelefone.setInt(2, telefone.getTipoTelefone().getId());
                        psInsertTelefone.executeUpdate();
    
                        ResultSet generatedKeys = psInsertTelefone.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int telefoneId = generatedKeys.getInt(1);
                            telefone.setId(telefoneId);
                        }
                    } else {
                        System.out.println("Telefone já existe: " + telefone.getNumero());
                    }
                }
            }
    
            String updateEndereco = "UPDATE endereco SET logradouro = ?, numero = ?, bairro = ?, idCidade = ?, idTipo = ? WHERE id = ?";
            String insertEndereco = "INSERT INTO endereco (logradouro, numero, bairro, idCidade, idTipo) VALUES (?, ?, ?, ?, ?)";
            psUpdateEndereco = connection.prepareStatement(updateEndereco);
            psInsertEndereco = connection.prepareStatement(insertEndereco, Statement.RETURN_GENERATED_KEYS);
    
            for (Endereco endereco : cliente.getEnderecos()) {
                if (endereco.getId() != 0) {
                    psUpdateEndereco.setString(1, endereco.getLogradouro());
                    psUpdateEndereco.setInt(2, endereco.getNumero());
                    psUpdateEndereco.setString(3, endereco.getBairro());
                    psUpdateEndereco.setInt(4, endereco.getCidade().getId());
                    psUpdateEndereco.setInt(5, endereco.getTipoEndereco().getId());
                    psUpdateEndereco.setInt(6, endereco.getId());
                    psUpdateEndereco.executeUpdate();
                } else {
                    psInsertEndereco.setString(1, endereco.getLogradouro());
                    psInsertEndereco.setInt(2, endereco.getNumero());
                    psInsertEndereco.setString(3, endereco.getBairro());
                    psInsertEndereco.setInt(4, endereco.getCidade().getId());
                    psInsertEndereco.setInt(5, endereco.getTipoEndereco().getId());
                    psInsertEndereco.executeUpdate();
    
                    ResultSet generatedKeys = psInsertEndereco.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int enderecoId = generatedKeys.getInt(1);
                        endereco.setId(enderecoId);
                    }
                }
            }
    
            connection.commit();
            sucesso = true;
    
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.println("Erro ao fazer rollback: " + rollbackEx.getMessage());
                }
            }
        } finally {
            try {
                if (psUpdatePessoa != null) {
                    psUpdatePessoa.close();
                }
                if (psUpdateTelefone != null) {
                    psUpdateTelefone.close();
                }
                if (psInsertTelefone != null) {
                    psInsertTelefone.close();
                }
                if (psUpdateEndereco != null) {
                    psUpdateEndereco.close();
                }
                if (psInsertEndereco != null) {
                    psInsertEndereco.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexões: " + e.getMessage());
            }
        }
    
        return sucesso;
    }
    
    private boolean verificarTelefoneExiste(Connection connection, long numeroTelefone) throws SQLException {
        String query = "SELECT COUNT(*) FROM telefone WHERE numero = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, numeroTelefone);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    private boolean verificarTipoTelefoneExiste(Connection connection, int idTipo) throws SQLException {
        String query = "SELECT COUNT(*) FROM tipotelefone WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idTipo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    

    public int encontrarIdClientePorNome(String nomeCliente) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int idCliente = -1; 
    
        try {
            connection = DBConnection.getInstance().getConnection();
    
            String selectCliente = "SELECT c.id AS idCliente " +
                                   "FROM cliente c " +
                                   "JOIN pessoa p ON c.idPessoa = p.id " +
                                   "WHERE p.nome = ?";
            preparedStatement = connection.prepareStatement(selectCliente);
            preparedStatement.setString(1, nomeCliente);
    
            resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                idCliente = resultSet.getInt("idCliente");
            } else {
                System.out.println("Nenhum cliente encontrado para o nome: " + nomeCliente);
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao encontrar ID do cliente: " + e.getMessage());
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    
        return idCliente;
    }
    
 
    public Cliente visualizar(String nomePessoa) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Cliente cliente = null;

        try {
            connection = DBConnection.getInstance().getConnection();
    
            String selectCliente = "SELECT c.id, p.nome, p.cpf, p.rg, " +
                                   "t.numero AS telefone_numero, tt.nome AS telefone_tipo, " +
                                   "e.logradouro, e.numero AS endereco_numero, e.bairro, " +
                                   "ci.nome AS cidade_nome, te.nome AS endereco_tipo " +
                                   "FROM cliente c " +
                                   "JOIN pessoa p ON c.idPessoa = p.id " +
                                   "LEFT JOIN telefone t ON p.idTelefone = t.id " +
                                   "LEFT JOIN tipoTelefone tt ON t.idTipo = tt.id " +
                                   "LEFT JOIN endereco e ON p.idEndereco = e.id " +
                                   "LEFT JOIN cidade ci ON e.idCidade = ci.id " +
                                   "LEFT JOIN tipoEndereco te ON e.idTipo = te.id " +
                                   "WHERE p.nome = ?";
            preparedStatement = connection.prepareStatement(selectCliente);
            preparedStatement.setString(1, nomePessoa);
    
            resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setId(resultSet.getInt("id"));
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
    
            } else {
                System.out.println("Nenhum cliente encontrado para o nome: " + nomePessoa);
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao visualizar cliente: " + e.getMessage());
            e.printStackTrace(); 
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
                e.printStackTrace(); 
            }
        }
    
        return cliente;
    }
    

    public Cliente visualizarcpf(String numero) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Cliente cliente = null;
    
        try {
            connection = DBConnection.getInstance().getConnection();
    
            String selectCliente = "SELECT p.id, p.nome, p.cpf, p.rg, " +
                                   "t.numero AS telefone_numero, tt.nome AS telefone_tipo, " +
                                   "e.logradouro, e.numero AS endereco_numero, e.bairro, " +
                                   "c.nome AS cidade_nome, te.nome AS endereco_tipo " +
                                   "FROM cliente cl " +
                                   "JOIN pessoa p ON cl.idPessoa = p.id " +
                                   "LEFT JOIN telefone t ON p.idTelefone = t.id " +
                                   "LEFT JOIN tipoTelefone tt ON t.idTipo = tt.id " +
                                   "LEFT JOIN endereco e ON p.idEndereco = e.id " +
                                   "LEFT JOIN cidade c ON e.idCidade = c.id " +
                                   "LEFT JOIN tipoEndereco te ON e.idTipo = te.id " +
                                   "WHERE p.cpf = ? OR p.rg = ?";
            preparedStatement = connection.prepareStatement(selectCliente);
            preparedStatement.setString(1, numero);
            preparedStatement.setString(2, numero);
    
            resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                if (cliente == null) {
                    cliente = new Cliente();
                    cliente.setId(resultSet.getInt("id"));
                    cliente.setNome(resultSet.getString("nome"));
                    cliente.setCpf(resultSet.getLong("cpf"));
                    cliente.setRg(resultSet.getLong("rg"));
                }
    
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
    
            String selectClientes = "SELECT p.id AS pessoa_id, p.nome, p.cpf, p.rg, " +
                                    "t.id AS telefone_id, t.numero AS telefone_numero, tt.nome AS telefone_tipo, " +
                                    "e.id AS endereco_id, e.logradouro, e.numero AS endereco_numero, e.bairro, " +
                                    "c.id AS cidade_id, c.nome AS cidade_nome, " +
                                    "te.id AS tipo_endereco_id, te.nome AS endereco_tipo " +
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
                cliente.setId(resultSet.getInt("pessoa_id"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setCpf(resultSet.getLong("cpf"));
                cliente.setRg(resultSet.getLong("rg"));
    
                Telefone telefone = new Telefone();
                telefone.setId(resultSet.getInt("telefone_id"));
                telefone.setNumero(resultSet.getLong("telefone_numero"));
                TipoTelefone tipoTelefone = new TipoTelefone();
                tipoTelefone.setNome(resultSet.getString("telefone_tipo"));
                telefone.setTipoTelefone(tipoTelefone);
    
                cliente.setTelefones(Arrays.asList(telefone));
    
                Endereco endereco = new Endereco();
                endereco.setId(resultSet.getInt("endereco_id"));
                endereco.setLogradouro(resultSet.getString("logradouro"));
                endereco.setNumero(resultSet.getInt("endereco_numero"));
                endereco.setBairro(resultSet.getString("bairro"));
    
                Cidade cidade = new Cidade();
                cidade.setId(resultSet.getInt("cidade_id"));
                cidade.setNome(resultSet.getString("cidade_nome"));
                endereco.setCidade(cidade);
    
                TipoEndereco tipoEndereco = new TipoEndereco();
                tipoEndereco.setId(resultSet.getInt("tipo_endereco_id"));
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
