package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import app.model.Usuario;

public class DaoUsuario {
        public boolean inserir(Usuario usuario){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String insert = "INSERT INTO usuarios (nome, senha_hash, idPerfil, idFuncionarios) VALUE (?,?,?,?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(insert);
            preparedStatement1.setString(1, usuario.getNome());
            preparedStatement1.setString(2, usuario.getSenha());
            preparedStatement1.setInt(3, usuario.getPerfil().getId());
            preparedStatement1.setInt(4, usuario.getFuncionario().getId());

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
}
