package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class ProdutoBean implements Serializable {

    private int id;
    private String nome;
    private String descricao;
    private double peso;
    private ProdutoCategoriaBean produto_categoria;

	public ProdutoBean() {
	}

    public ProdutoBean(int id, String nome, String descricao, double peso, ProdutoCategoriaBean produto_categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.peso = peso;
        this.produto_categoria = produto_categoria;
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

	public ProdutoCategoriaBean getProduto_categoria() {
		return produto_categoria;
	}

	public void setProduto_categoria(ProdutoCategoriaBean produto_categoria) {
		this.produto_categoria = produto_categoria;
	}
	
	

}
