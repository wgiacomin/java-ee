package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class ProdutoCategoriaBean implements Serializable {

    private int id;
    private String descricao;

    public ProdutoCategoriaBean(int id, String nome) {
        this.id = id;
        this.descricao = nome;
    }

    public ProdutoCategoriaBean(String nome) {
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

}
