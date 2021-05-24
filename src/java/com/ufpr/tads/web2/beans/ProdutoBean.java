package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class ProdutoBean implements Serializable {

    private int id;
    private String nome;
    private String descricao;
    private double peso;
    private int fk_categoria;

    public ProdutoBean(int id, String nome, String descricao, double peso, int fk_categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.peso = peso;
        this.fk_categoria = fk_categoria;
    }

    public ProdutoBean(String nome, String descricao, double peso, int fk_categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.peso = peso;
        this.fk_categoria = fk_categoria;
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

    public int getFk_categoria() {
        return fk_categoria;
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

    public void setFk_categoria(int fk_categoria) {
        this.fk_categoria = fk_categoria;
    }

}
