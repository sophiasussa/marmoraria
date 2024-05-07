package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
