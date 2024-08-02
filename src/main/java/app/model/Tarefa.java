package app.model;

public class Tarefa {
    private int id;
    private String titulo;
    private String decricao;
    private int dataHora;
    private String status;
    private Usuario usuario;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDecricao() {
        return decricao;
    }
    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }
    public int getDataHora() {
        return dataHora;
    }
    public void setDataHora(int dataHora) {
        this.dataHora = dataHora;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }   
}
