package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class ProdutoCategoriaBean implements Serializable {

    private int id;
    private String nome;

    public ProdutoCategoriaBean(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public ProdutoCategoriaBean(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
