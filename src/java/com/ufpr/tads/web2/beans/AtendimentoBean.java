package com.ufpr.tads.web2.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class AtendimentoBean implements Serializable {

    private int id;
    private Timestamp dataHora;
    private String descricao;
    private String solucao;
    private LoginBean login;
    private StatusBean status;
    private TipoAtendimentoBean tipoAtendimento;
    private ProdutoBean produto;
    private int clr;

    public AtendimentoBean() {
    }

    public AtendimentoBean(int id, Timestamp dataHora, String descricao, String solucao, LoginBean login, StatusBean status, TipoAtendimentoBean tipoAtendimento, ProdutoBean produto) {
        this.id = id;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.solucao = solucao;
        this.login = login;
        this.status = status;
        this.tipoAtendimento = tipoAtendimento;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public TipoAtendimentoBean getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(TipoAtendimentoBean tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public ProdutoBean getProduto() {
        return produto;
    }

    public void setProduto(ProdutoBean produto) {
        this.produto = produto;
    }

    public int getClr() {
        return clr;
    }

    public void setClr(int clr) {
        this.clr = clr;
    }

    
}
