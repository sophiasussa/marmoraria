package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import app.model.Tarefa;

public class DaoTarefa {

    public boolean inserir(Tarefa tarefa){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String insert = "INSERT INTO tarefasGerais (titulo, descricao, dataHora, estado, idUsuario) VALUE (?,?,?,?,?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(insert);
            preparedStatement1.setString(1, tarefa.getTitulo());
            preparedStatement1.setString(2, tarefa.getDecricao());
            preparedStatement1.setInt(3, tarefa.getDataHora());
            preparedStatement1.setString(4, tarefa.getStatus());
            preparedStatement1.setInt(5, tarefa.getUsuario().getId());

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
