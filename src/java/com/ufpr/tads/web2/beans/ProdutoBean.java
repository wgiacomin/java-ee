package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class ProdutoBean implements Serializable {

    private int id;
    private String nome;
    private String descricao;
    private double peso;
    private ProdutoCategoriaBean produtoCategoria;

    public ProdutoBean() {
    }

    public ProdutoBean(int id, String nome, String descricao, double peso, ProdutoCategoriaBean produtoCategoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.peso = peso;
        this.produtoCategoria = produtoCategoria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPeso() {
        return peso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public ProdutoCategoriaBean getProdutoCategoria() {
        return produtoCategoria;
    }

    public void setProdutoCategoria(ProdutoCategoriaBean produtoCategoria) {
        this.produtoCategoria = produtoCategoria;
    }

    public void setId(int id) {
        this.id = id;
    }

}
