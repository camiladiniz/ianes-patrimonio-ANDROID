package br.senai.sp.info.ianespatrimonio.model;

import java.util.Date;

public class Movimentacao {

    private Long id;
    private Date dataMovimentacao;
    private Ambiente ambienteOrigem;
    private Ambiente ambienteDestino;
    private Usuario usuario;
    private ItemPatrimonio item;

    public Movimentacao(Ambiente ambienteDestino, ItemPatrimonio item) {
        this.ambienteDestino = ambienteDestino;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Ambiente getAmbienteOrigem() {
        return ambienteOrigem;
    }

    public void setAmbienteOrigem(Ambiente ambienteOrigem) {
        this.ambienteOrigem = ambienteOrigem;
    }

    public Ambiente getAmbienteDestino() {
        return ambienteDestino;
    }

    public void setAmbienteDestino(Ambiente ambienteDestino) {
        this.ambienteDestino = ambienteDestino;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ItemPatrimonio getItem() {
        return item;
    }

    public void setItem(ItemPatrimonio item) {
        this.item = item;
    }
}
