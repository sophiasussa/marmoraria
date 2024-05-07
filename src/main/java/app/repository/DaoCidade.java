package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import app.model.Cidade;

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
}
