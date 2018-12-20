package br.senai.sp.info.ianespatrimonio.model;

public class Ambiente {

    private Long id;
    private String nome;

    public Ambiente(String nome) {
        this.nome = nome;
    }

    public Ambiente(Long id, String nome) {
        this.id = id;
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

    @Override
    public String toString() {
        return nome;
    }
}
