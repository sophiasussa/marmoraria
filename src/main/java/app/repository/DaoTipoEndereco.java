package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

}
