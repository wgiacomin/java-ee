package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class TipoAtendimentoBean implements Serializable {

    private int id;
    private String descricao;

    public TipoAtendimentoBean() {
    }

    public TipoAtendimentoBean(int id, String nome) {
        this.id = id;
        this.descricao = nome;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String nome) {
        this.descricao = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

}
