package app.model;

public class PerfilPermissao {
    private int id;
    private Perfil perfil;
    private Permissao permissao;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Perfil getPerfil() {
        return perfil;
    }
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    public Permissao getPermissao() {
        return permissao;
    }
    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }
}
