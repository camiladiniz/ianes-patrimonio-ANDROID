package br.senai.sp.info.ianespatrimonio.model;

import java.util.Date;

public class Patrimonio {

    private Long id;
    private String nome;
    private Date dataCadastro;

    //private String categoria;

    private Usuario usuarioCadastrou;

    private String caminhoFoto;

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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

   /* public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }*/

    public Usuario getUsuarioCadastrou() {
        return usuarioCadastrou;
    }

    public void setUsuarioCadastrou(Usuario usuarioCadastrou) {
        this.usuarioCadastrou = usuarioCadastrou;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    @Override
    public String toString() {
        return nome;
    }
}
