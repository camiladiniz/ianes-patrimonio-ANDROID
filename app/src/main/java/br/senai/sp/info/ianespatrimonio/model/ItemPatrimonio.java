package br.senai.sp.info.ianespatrimonio.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemPatrimonio {

    private Long id;
    private Patrimonio patrimonio;
    private Usuario usuario;

    private Ambiente ambiente;


    private Date dataUltimaMovimentacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public Date getDataUltimaMovimentacao() {
        return dataUltimaMovimentacao;
    }

    public void setDataUltimaMovimentacao(Date dataUltimaMovimentacao) {
        this.dataUltimaMovimentacao = dataUltimaMovimentacao;
    }


    @Override
    public String toString() {
        return patrimonio.toString() +" - " +id;
    }
}
