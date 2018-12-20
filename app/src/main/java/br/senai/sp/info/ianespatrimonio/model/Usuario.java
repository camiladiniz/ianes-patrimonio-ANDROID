package br.senai.sp.info.ianespatrimonio.model;

public class Usuario {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private TipoUsuario tipo;

    private Usuario(){

    }

    public Usuario(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

    public Usuario(Long id, String email, String senha, String nome){
        this.id = id;
        this.email = email;
        this.senha  = senha;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}
